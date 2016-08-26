package com.rostlab;

import com.rostlab.request.RaptorXRequester;

import java.io.IOException;

/**
 * Created by Longes
 */
public class EvaApplication {

    private final static String email = "longesway@gmail.com";

    public static void main(String[] args) {

        /*String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);*/

        String sequence = "MAFSAEDVLKEYDRRRRMEALLLSLYYPNDRKLLDYKEWSPPRVQVECPKAPVEWNNPPS" +
                "EKGLIVGHFSGIKYKGEKAQASEVDVNKMCCWVSKFKDAMRRYQGIQTCKIPGKVLSDLD" +
                "AKIKAYNLTVEGVEGFVRYSRVTKQHVAAFLKELRHSKQYENVNLIHYILTDKRVDIQHL" +
                "EKDLVKDFKALVESAHRMRQGHMINVKYILYQLLKKHGHGPDGPDILTVKTGSKGVLYDD" +
                "SFRKIYTDLGWKFTPL";
        //PSIPREDrequester psipredRequester = new PSIPREDrequester(email);
        /*JpredRequester jpredRequester = new JpredRequester(email);
        try {
            jpredRequester.makeRequest(sequence);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        RaptorXRequester raptorXRequester = new RaptorXRequester(email);
        try {
            raptorXRequester.makeRequest(sequence);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*UniProtManager uniProtManager = new UniProtManager();
        QueryResult<UniProtEntry> query = null;
        try {
            query = uniProtManager.getByDate();
        } catch (ServiceException e) {
            e.printStackTrace();
        }*/

        /*PDBRequester pdbRequester = new PDBRequester();
        try {
            pdbRequester.makeRequest("1");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //SiftsConnector connector = new SiftsConnector();
        //String t = connector.sift("1A00");
        //RostLabSSH.getPDBFile("1111");
        /*PSIPREDparser parser = new PSIPREDparser("test.txt");
        try {
            parser.parsePSIPREDMail();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        int i = 0;
    }
}
