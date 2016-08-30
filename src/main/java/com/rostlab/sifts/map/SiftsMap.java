package com.rostlab.sifts.map;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import com.rostlab.generationModule.PDB.RostLabSSH;
import com.rostlab.sifts.io.FReader;
import com.rostlab.sifts.io.FWriter;
import com.rostlab.sifts.io.HttpDownloader;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.rostlab.sifts.util.AminoAcids;

/**
 * Created by Longes
 */
public class SiftsMap {
	
	
	private String pdbId = null;
	private String chainId = null;
	private String uniprotAc = null;
	private String siftsXmlFile = null;
	private String siftsFlatFile = null;
	private boolean successful = false;
	
	private int firstPdb = 0;
	private int lastPdb = 0;
	private int firstUniprot = 0;
	private int lastUniprot = 0;
	
	private HashMap<Integer, Integer> 	pdbToUniprotPos = null;
	private HashMap<Integer, Integer> 	uniprotToPdbPos = null;
	private HashMap<Integer, Character> pdbToUniprotRes = null;
	private HashMap<Integer, Character> pdbToPdbRes 	= null;

	private SiftsMap(String pdbId)
	{
		this.pdbId = pdbId.substring(0, 4);
		this.chainId = pdbId.substring(4);
		this.pdbToUniprotPos = new HashMap<Integer, Integer>();
		this.uniprotToPdbPos = new HashMap<Integer, Integer>();
		this.pdbToUniprotRes = new HashMap<Integer, Character>();
		this.pdbToPdbRes = new HashMap<Integer, Character>();
		this.siftsXmlFile = "./data/00_raw/map/maps/"+this.pdbId+".xml";
		
		if (Character.isUpperCase(this.chainId.charAt(0)))
		{
            ChannelSftp sftpChannel = RostLabSSH.getPDBFile(pdbId);
            try {
                this.siftsFlatFile 	= sftpChannel.get("./data/01_parsed/map/maps/"+this.pdbId+"_"+this.chainId+".txt").toString();
            } catch (SftpException e) {
                e.printStackTrace();
            }
        }
		else
		{
            ChannelSftp sftpChannel = RostLabSSH.getPDBFile(pdbId);
            try {
                this.siftsFlatFile 	= sftpChannel.get("./data/01_parsed/map/maps/"+this.pdbId+"-"+this.chainId+".txt").toString();
            } catch (SftpException e) {
                e.printStackTrace();
            }
        }
		
		this.downloadSiftsMap();
		this.buildMap();
		this.setFirstLast();
		if (this.wasSuccessful() && !(new File(this.siftsFlatFile)).exists())
		{
			this.printToFile(this.siftsFlatFile);
		}
	}
	
	public static SiftsMap newMap(String pdbId)
	{
		SiftsMap map = new SiftsMap(pdbId);
		
		if (map.wasSuccessful())
		{
			return map;
		}
		else
		{
			return null;
		}
	}
	
	private void downloadSiftsMap()
	{
		if (!(new File(this.siftsXmlFile)).exists() && !(new File(this.siftsFlatFile)).exists())
		{
			String url = "ftp://ftp.ebi.ac.uk/pub/databases/msd/map/xml/"+this.pdbId+".xml.gz";
			
			HttpDownloader.downloadAndDecompressToFile(url, this.siftsXmlFile);
		}
	}
	
	private void setFirstLast()
	{
		if (this.pdbToUniprotPos.keySet() != null && this.pdbToUniprotPos.keySet().size() > 0)
		{
			this.firstPdb 		= Collections.min(this.pdbToUniprotPos.keySet());
			this.lastPdb 		= Collections.max(this.pdbToUniprotPos.keySet());
		}
		
		if (this.uniprotToPdbPos.keySet() != null && this.uniprotToPdbPos.keySet().size() > 0)
		{
			this.firstUniprot 	= Collections.min(this.uniprotToPdbPos.keySet());
			this.lastUniprot 	= Collections.max(this.uniprotToPdbPos.keySet());
		}
	}
	
	private void buildMap()
	{
		if (!(new File(this.siftsXmlFile)).exists() && !(new File(this.siftsFlatFile)).exists())
		{
			System.err.println("Error: No SIFTS entry for "+this.pdbId);
			return;
		}
		else if ((new File(this.siftsFlatFile)).exists())
		{
			this.readFromFile(siftsFlatFile);
			return;
		}
		
		DocumentBuilderFactory dbf = null;
		DocumentBuilder db 	= null;
		Document doc = null;
		try
		{
			dbf = DocumentBuilderFactory.newInstance();
			db 	= dbf.newDocumentBuilder();
			doc = db.parse(new File(this.siftsXmlFile));
			
			doc.getDocumentElement().normalize();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		NodeList entities = doc.getElementsByTagName("entity");
		for (int i = 0; i < entities.getLength(); ++i)
		{
			Node 		entity 		= entities.item(i);
			NodeList 	segments 	= entity.getChildNodes();
			
			for (int j = 0; j < segments.getLength(); ++j)
			{
				Node segment = segments.item(j);
				
				if(!this.processSegment(segment)){return;}
			}
		}
	}

	private boolean processSegment(Node segment)
	{
		NodeList segmentData = segment.getChildNodes();
		for (int i = 0; i < segmentData.getLength(); ++i)
		{
			Node dataBlock = segmentData.item(i);
			if (dataBlock.getNodeName().equals("listResidue"))
			{
				NodeList residues = dataBlock.getChildNodes();
				for (int j = 0; j < residues.getLength(); ++j)
				{
					Node residue = residues.item(j);
					if (residue.getNodeName().equals("residue"))
					{
						if(!this.processResidue(residue)){return false;}
					}
				}
			}
		}
		
		return true;
	}

	private boolean processResidue(Node residue)
	{
		NodeList residueData = residue.getChildNodes();
		String 	uniprotAc = null;
		Integer pdbPos = null;
		Integer uniprotPos = null;
		Character pdbRes = null;
		Character uniprotRes = null;
		
		for (int i = 0; i < residueData.getLength(); ++i)
		{
			Node dataBlock = residueData.item(i);
			if (dataBlock.getNodeName().equals("crossRefDb"))
			{
				NamedNodeMap attributes = dataBlock.getAttributes();
				try
				{
					if (attributes.getNamedItem("dbSource").getNodeValue().equals("PDB") &&
						attributes.getNamedItem("dbAccessionId").getNodeValue().equals(this.pdbId) &&
						attributes.getNamedItem("dbChainId").getNodeValue().equals(this.chainId))
						{
							pdbRes = AminoAcids.parseThreeToOneCode(attributes.getNamedItem("dbResName").getNodeValue(), true);
							pdbPos = Integer.valueOf(attributes.getNamedItem("dbResNum").getNodeValue().replaceAll("[a-zA-Z]", ""));
							if (pdbRes == AminoAcids.NULL_AA || pdbRes == AminoAcids.UNKNOWN)
							{
								pdbRes = null;
								break;
							}
						}
						else if (attributes.getNamedItem("dbSource").getNodeValue().equals("UniProt"))
						{
							uniprotAc 	= attributes.getNamedItem("dbAccessionId").getNodeValue();
							uniprotRes 	= attributes.getNamedItem("dbResName").getNodeValue().charAt(0);
							uniprotPos 	= Integer.valueOf(attributes.getNamedItem("dbResNum").getNodeValue());
						}
				}
				catch (NumberFormatException e)
				{
					System.err.println("Warning: Illegal residue number ("+attributes.getNamedItem("dbResNum").getNodeValue()+", "+this.pdbId+this.chainId+").");
				}
			}
			else if (dataBlock.getNodeName().equals("residueDetail"))
			{
				NamedNodeMap attributes = dataBlock.getAttributes();
				if (attributes.getNamedItem("property").getNodeValue().equals("Annotation"))
				{
					String annotation = dataBlock.getTextContent();
					if (annotation.equalsIgnoreCase("Not_Observed"))
					{
						pdbRes = null;
						break;
					}
				}
			}
		}
		
		if (pdbPos != null && pdbRes != null && uniprotPos != null && uniprotAc != null && uniprotRes != null)
		{
			if (uniprotPos.intValue() < 1)
			{
				System.err.println("Warning: Skipping negative uniprot position mapping ("+this.pdbId+this.chainId+").");
				return true;
			}
			if (this.uniprotAc == null)
			{
				this.uniprotAc 	= uniprotAc;
				this.successful = true;
			}
			else if (!this.uniprotAc.equals(uniprotAc))
			{
				System.err.println("Error: Multiple Uniprot ACs for single PDB chain ("+this.pdbId+this.chainId+").");
				this.successful = false;
				return false;
			}
			if (this.pdbToUniprotPos.get(pdbPos) == null)
			{
				this.pdbToUniprotPos.put(pdbPos, uniprotPos);
				this.pdbToUniprotRes.put(pdbPos, uniprotRes);
				this.pdbToPdbRes.put(pdbPos, pdbRes);
				if (this.uniprotToPdbPos.get(uniprotPos) == null)
				{
					this.uniprotToPdbPos.put(uniprotPos, pdbPos);
				}
				else if (!(this.uniprotToPdbPos.get(uniprotPos) == pdbPos))
				{
					System.err.println("Error: Uniprot residue position already mapped ("+uniprotPos+", "+this.pdbId+this.chainId+").");
					this.successful = false;
					return false;
				}
			}
			else if (!(this.pdbToUniprotPos.get(pdbPos) == uniprotPos))
			{
				System.err.println("Error: PDB residue position already mapped ("+pdbPos+", "+this.pdbId+this.chainId+").");
				this.successful = false;
				return false;
			}
		}
		else
		{
			System.err.println("Warning: Missing data for PDB residue ("+pdbPos+", "+this.pdbId+this.chainId+").");
		}
		
		return true;
	}

	private void printToFile(String filename)
	{
		if (firstPdb == 0 && lastPdb == 0) {return;}
		FWriter.openFile(filename);
		FWriter.writeLine(this.uniprotAc);
		for (int i = firstPdb; i <= lastPdb; ++i)
		{
			Integer uniprotPos = this.pdbToUniprotPos(i);
			if (uniprotPos == null) {continue;}
			Character pdbAa 	= this.pdbToPdbRes(i);
			Character uniprotAa = this.pdbToUniprotRes(i);
			FWriter.writeLine(i+"\t"+pdbAa+"\t"+uniprotPos+"\t"+uniprotAa);
		}
		
		FWriter.closeFile();
	}
	
	
	private void readFromFile(String filename)
	{
		FReader.openFile(filename);
		String line = FReader.readLine();
		this.uniprotAc = line.trim();
		while (line != null)
		{
			line = line.trim();
			String[] content = line.split("\t");
			if (content.length == 4)
			{
				try
				{				
					Integer pdbPos = Integer.valueOf(content[0]);
					Integer uniprotPos = Integer.valueOf(content[2]);
					Character pdbRes = content[1].charAt(0);
					Character uniprotRes = content[3].charAt(0);
					this.pdbToUniprotPos.put(pdbPos, uniprotPos);
					this.uniprotToPdbPos.put(uniprotPos, pdbPos);
					this.pdbToPdbRes.put(pdbPos, pdbRes);
					this.pdbToUniprotRes.put(pdbPos, uniprotRes);
				}
				catch (Exception e)
				{
					System.err.println("Error: Corrupted SIFTS map file: "+filename);
					e.printStackTrace();
					
					this.successful = false;
					
					return;
				}
			}
			
			line = FReader.readLine();
		}
		
		FReader.closeFile();
		
		this.successful = true;
	}
	
	
	public Integer pdbToUniprotPos(int pdbPos)
	{
		return this.pdbToUniprotPos.get(pdbPos);
	}

	public Integer uniprotToPdbPos(int uniprotPos)
	{
		return this.uniprotToPdbPos.get(uniprotPos);
	}
	
	public Character pdbToUniprotRes(int pdbPos)
	{
		return this.pdbToUniprotRes.get(pdbPos);
	}
	
	public Character pdbToPdbRes(int pdbPos)
	{
		return this.pdbToPdbRes.get(pdbPos);
	}
	
	public String getUniprotAc()
	{
		return this.uniprotAc;
	}

	public int getAlignmentLength()
	{
		return this.pdbToUniprotPos.size();
	}
	
	public boolean wasSuccessful()
	{
		return this.successful;
	}

	public int getFirstPdb()
	{
		return this.firstPdb;
	}
	
	public int getLastPdb()
	{
		return this.lastPdb;
	}
	
	public int getFirstUniprot()
	{
		return this.firstUniprot;
	}
	
	public int getLastUniprot()
	{
		return this.lastUniprot;
	}

}
