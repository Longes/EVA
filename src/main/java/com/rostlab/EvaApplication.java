package com.rostlab;

import com.rostlab.generationModule.PDB.PDBEntity;
import com.rostlab.generationModule.generationMain;
import com.rostlab.generationModule.uniprot.UniProtEntity;
import com.rostlab.requestModule.mail.PSIPREDparser;
import com.rostlab.requestModule.request.RaptorXRequester;
import com.rostlab.requestModule.requestMain;

import java.io.IOException;

/**
 * Created by Longes
 */
public class EvaApplication {

    private final static String email = "longesway@gmail.com";

    public static void main(String[] args) {

        generationMain Generator = new generationMain();
        requestMain Requester = new requestMain();

        /*String sequence = "MAFSAEDVLKEYDRRRRMEALLLSLYYPNDRKLLDYKEWSPPRVQVECPKAPVEWNNPPS" +
                "EKGLIVGHFSGIKYKGEKAQASEVDVNKMCCWVSKFKDAMRRYQGIQTCKIPGKVLSDLD" +
                "AKIKAYNLTVEGVEGFVRYSRVTKQHVAAFLKELRHSKQYENVNLIHYILTDKRVDIQHL" +
                "EKDLVKDFKALVESAHRMRQGHMINVKYILYQLLKKHGHGPDGPDILTVKTGSKGVLYDD" +
                "SFRKIYTDLGWKFTPL";*/
        /*String accId = "P69905";
        UniProtEntity entity = Generator.downloadEntries(false, accId);
        System.out.print(entity.sequence);
        entity.sequence = "MVLSPADKTNVKAAWGKVGAHAGEYGAEALERMFLSFPTTKTYFPHFDLSHGSAQVKGHGKKVADALTNAVAHVDDMPNALSALSDLHAHKLRVDPVNFKLLSHCLLVTLAAHLPAEFTPAVHASLDKFLASVSTVLTSKYR";
        Requester.requestAllByEntity(entity);*/
        /*PSIPREDparser parser = new PSIPREDparser("test.txt");
        try {
            parser.parsePSIPREDMail();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        PDBEntity pdbEntity = new PDBEntity("4M4A");
        pdbEntity.setStructure();
        pdbEntity.comparePSIPRED();

        /*String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);*/
        //PSIPREDrequester psipredRequester = new PSIPREDrequester(email);
        /*JpredRequester jpredRequester = new JpredRequester(email);
        try {
            jpredRequester.makeRequest(sequence);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*RaptorXRequester raptorXRequester = new RaptorXRequester(email);
        try {
            raptorXRequester.makeRequest(sequence);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
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
