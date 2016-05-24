package com.rostlab.util;

import java.util.HashMap;

/**
 * Created by Longes
 */
public class AminoAcids {
	
	
	public static final char NULL_AA = '.';
	public static final char UNKNOWN = 'X';
	
	private static HashMap<String, Character> ThreeToOne = new HashMap<String, Character>();
	
	static
	{
		ThreeToOne.put("ALA", 'A');
		ThreeToOne.put("ARG", 'R');
		ThreeToOne.put("ASN", 'N');
		ThreeToOne.put("ASP", 'D');
		ThreeToOne.put("CYS", 'C');
		ThreeToOne.put("GLU", 'E');
		ThreeToOne.put("GLN", 'Q');
		ThreeToOne.put("GLY", 'G');
		ThreeToOne.put("HIS", 'H');
		ThreeToOne.put("ILE", 'I');
		ThreeToOne.put("LEU", 'L');
		ThreeToOne.put("LYS", 'K');
		ThreeToOne.put("MET", 'M');
		ThreeToOne.put("PHE", 'F');
		ThreeToOne.put("PRO", 'P');
		ThreeToOne.put("SER", 'S');
		ThreeToOne.put("THR", 'T');
		ThreeToOne.put("TRP", 'W');
		ThreeToOne.put("TYR", 'Y');
		ThreeToOne.put("VAL", 'V');
		ThreeToOne.put("UNK", UNKNOWN);
		/*
		 *  uncommon three letter codes (based on http://ligand-expo.rcsb.org/index.html)
		 */
		ThreeToOne.put("CSE", 'U'); // SELENOCYSTEINE
		// with nonstandard parent
		ThreeToOne.put("MSE", 'M'); // SELENOMETHIONINE
		ThreeToOne.put("FME", 'M'); // N-FORMYLMETHIONINE
		ThreeToOne.put("CXM", 'M'); // N-CARBOXYMETHIONINE
		ThreeToOne.put("SAC", 'S'); // N-ACETYL-SERINE
		ThreeToOne.put("HSE", 'S'); // L-HOMOSERINE
		ThreeToOne.put("SEP", 'S'); // PHOSPHOSERINE
		ThreeToOne.put("TPO", 'T'); // PHOSPHOTHREONINE
		ThreeToOne.put("PCA", 'E'); // PYROGLUTAMIC ACID
		ThreeToOne.put("GMA", 'E'); // 4-AMIDO-4-CARBAMOYL-BUTYRIC ACID
		ThreeToOne.put("PHD", 'D'); // ASPARTYL PHOSPHATE
		ThreeToOne.put("BFD", 'D'); // ASPARTATE BERYLLIUM TRIFLUORIDE
		ThreeToOne.put("LYR", 'K'); // N~6~-[(2Z,4E,6E,8E)-3,7-DIMETHYL-9-(2,6,6-TRIMETHYLCYCLOHEX-1-EN-1-YL)NONA-2,4,6,8-TETRAENYL]LYSINE
		ThreeToOne.put("MLY", 'K'); // N-DIMETHYL-LYSINE
		ThreeToOne.put("CSO", 'C'); // S-HYDROXYCYSTEINE
		ThreeToOne.put("SAH", 'C'); // S-ADENOSYL-L-HOMOCYSTEINE
		ThreeToOne.put("R1A", 'C'); // S-(THIOMETHYL-3-[2,2,5,5-TETRAMETHYL PYRROLINE-1-OXYL]) CYSTEINE
		ThreeToOne.put("YCM", 'C'); // S-(2-AMINO-2-OXOETHYL)-L-CYSTEINE
		ThreeToOne.put("SCH", 'C'); // S-METHYL-THIO-CYSTEINE
		ThreeToOne.put("AIB", 'A'); // ALPHA-AMINOISOBUTYRIC ACID
		ThreeToOne.put("ALS", 'A'); // 2-AMINO-3-OXO-4-SULFO-BUTYRIC ACID
		ThreeToOne.put("ALN", 'A'); // NAPHTHALEN-2-YL-3-ALANINE
		ThreeToOne.put("ORN", 'A'); // L-ornithine
		ThreeToOne.put("HYP", 'P'); // 4-HYDROXYPROLINE
		ThreeToOne.put("PHL", 'F'); // L-PHENYLALANINOL
		ThreeToOne.put("MEA", 'F'); // N-METHYLPHENYLALANINE
		ThreeToOne.put("PPN", 'F'); // PARA-NITROPHENYLALANINE
		ThreeToOne.put("PHI", 'F'); // IODO-PHENYLALANINE
		ThreeToOne.put("FVA", 'V'); // N-FORMYL-L-VALINE
		ThreeToOne.put("4BF", 'Y'); // 4-BROMO-L-PHENYLALANINE
		ThreeToOne.put("DBY", 'Y'); // 3,5 DIBROMOTYROSINE
		ThreeToOne.put("HSK", 'H'); // 3-HYDROXY-L-HISTIDINE
		// without nonstandard parent
		ThreeToOne.put("DAL", 'A'); // D-ALANINE
		ThreeToOne.put("DLE", 'L'); // D-LEUCINE
		ThreeToOne.put("DVA", 'V'); // D-VALINE
		ThreeToOne.put("DIV", 'V'); // D-ISOVALINE
		ThreeToOne.put("DPR", 'P'); // D-PROLINE
		ThreeToOne.put("DTR", 'W'); // D-TRYPTOPHAN
		ThreeToOne.put("SC2", 'C'); // N-ACETYL-L-CYSTEINE
		// Other
		ThreeToOne.put("ETA", NULL_AA); // ETHANOLAMINE
		ThreeToOne.put("NAG", NULL_AA); // N-ACETYL-D-GLUCOSAMINE
		ThreeToOne.put("BMA", NULL_AA); // BETA-D-MANNOSE
		ThreeToOne.put("HAO", NULL_AA); // {[3-(hydrazinocarbonyl)-4-methoxyphenyl]amino}(oxo)acetic acid
		// NON-POLYMER
		ThreeToOne.put("CA", NULL_AA);
		ThreeToOne.put("NH2", NULL_AA);
		ThreeToOne.put("ACE", NULL_AA);
		ThreeToOne.put("PLM", NULL_AA); // PALMITIC ACID
		ThreeToOne.put("FLC", NULL_AA); // CITRATE ANION
		ThreeToOne.put("BET", NULL_AA); // TRIMETHYL GLYCINE
		ThreeToOne.put("UMQ", NULL_AA); // UNDECYL-MALTOSIDE
		ThreeToOne.put("PS6", NULL_AA); // O-[(S)-{[(2S)-2-(hexanoyloxy)-3-(tetradecanoyloxy)propyl]oxy}(hydroxy)phosphoryl]-D-serine
		ThreeToOne.put("PLY", NULL_AA); // PALMITOYL
		ThreeToOne.put("0SA", NULL_AA); // undecyl 4-O-alpha-D-glucopyranosyl-1-thio-beta-D-glucopyranoside
		ThreeToOne.put("CN5", NULL_AA); // (5S,11R)-5,8,11-trihydroxy-5,11-dioxido-17-oxo-4,6,10,12,16-pentaoxa-5,11-diphosphaoctadec-1-yl pentadecanoate
		ThreeToOne.put("CN3", NULL_AA); // (2R,5S,11R,14R)-5,8,11-trihydroxy-2-(nonanoyloxy)-5,11-dioxido-16-oxo-14-[(propanoyloxy)methyl]-4,6,10,12,15-pentaoxa-5,11-diphosphanonadec-1-yl undecanoate
		ThreeToOne.put("TYF", NULL_AA); // (2S)-2-hydroxy-3-(4-hydroxyphenyl)propanoic acid
		ThreeToOne.put("ZK1", NULL_AA); // {[7-morpholin-4-yl-2,3-dioxo-6-(trifluoromethyl)-3,4-dihydroquinoxalin-1(2H)-yl]methyl}phosphonic acid
		ThreeToOne.put("TYF", NULL_AA); // (2S)-2-hydroxy-3-(4-hydroxyphenyl)propanoic acid
		ThreeToOne.put("AME", NULL_AA); // N-ACETYLMETHIONINE
		ThreeToOne.put("KAI", NULL_AA); // 3-(CARBOXYMETHYL)-4-ISOPROPENYLPROLINE
		ThreeToOne.put("FOL", NULL_AA); // FOLIC ACID
		ThreeToOne.put("OOC", NULL_AA); // octyl 4-O-beta-D-allopyranosyl-1-thio-beta-D-altropyranoside
		ThreeToOne.put("CN6", NULL_AA); // (2R,5R,11S,14R)-2-(butanoyloxy)-5,8,11-trihydroxy-5,11-dioxido-16-oxo-14-[(propanoyloxy)methyl]-4,6,10,12,15-pentaoxa-5,11-diphosphanonadec-1-yl undecanoate
	}
	
	
	public static char parseThreeToOneCode(String aminoAcid, boolean verbose)
	{
		if (aminoAcid == null)
		{
			return NULL_AA;
		}
		else
		{
			Character aa = ThreeToOne.get(aminoAcid.toUpperCase());
			
			if (aa != null)
			{
				return aa;
			}
			else
			{
				if (verbose)
				{
					System.err.println("Warning: Unknown amino acid: "+aminoAcid);
				}
				
				return UNKNOWN;
			}
		}
	}

}
