package com.rostlab.request;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;




public class PDBRequester
{

    public static final String SERVICELOCATION="http://www.rcsb.org/pdb/rest/search";

    static String readFile(String path)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, Charset.defaultCharset());
    }

    public static List<String> makeRequest(String accId) throws IOException {

        String xml = "<orgPdbQuery>\n" +
                "\n" +
                "    <queryType>org.pdb.query.simple.UpAccessionIdQuery</queryType>\n" +
                "\n" +
                "    <description>Query for a list of Uniprot Accession IDs: "+ accId +"</description>\n" +
                "\n" +
                "    <accessionIdList>"+ accId +"</accessionIdList>\n" +
                "\n" +
                "</orgPdbQuery>";


        PDBRequester t = new PDBRequester();
        return t.postQuery(xml);

    }

    public static void requestByPDBID(String pdbId) {
        String xml = "<orgPdbQuery>\n" +
                "\n" +
                "<queryType>org.pdb.query.simple.StructureIdQuery</queryType>\n" +
                "\n" +
                "<description>Simple query for a list of PDB IDs (1 IDs) : " + pdbId + "</description>\n" +
                "\n" +
                "<structureIdList>" + pdbId + "</structureIdList>\n" +
                "\n" +
                "</orgPdbQuery>";


        PDBRequester t = new PDBRequester();

        try {
            List<String> pdbIds = t.postQuery(xml);

            for (String string : pdbIds)
            {
                System.out.println(string);

            }
        } catch (Exception e){
            e.printStackTrace();

        }
    }

    /** post am XML query (PDB XML query format)  to the RESTful RCSB web service
     *
     * @param xml
     * @return a list of PDB ids.
     */
    public List<String> postQuery(String xml)
            throws IOException{



        URL u = new URL(SERVICELOCATION);

        String encodedXML = URLEncoder.encode(xml,"UTF-8");

        InputStream in =  doPOST(u,encodedXML);
        List<String> pdbIds = new ArrayList<String>();

        BufferedReader rd = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = rd.readLine()) != null) {

            pdbIds.add(line);

        }
        rd.close();

        return pdbIds;



    }

    /** do a POST to a URL and return the response stream for further processing elsewhere.
     *
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static InputStream doPOST(URL url, String data)

            throws IOException
    {

        // Send data

        URLConnection conn = url.openConnection();

        conn.setDoOutput(true);

        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

        wr.write(data);
        wr.flush();


        // Get the response
        return conn.getInputStream();

    }
}
