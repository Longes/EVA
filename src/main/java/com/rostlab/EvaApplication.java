package com.rostlab;

import com.rostlab.request.JpredRequester;
import com.rostlab.request.PSIPREDrequester;
import com.rostlab.request.RaptorXRequester;
import uk.ac.ebi.kraken.interfaces.uniprot.UniProtEntry;
import uk.ac.ebi.uniprot.dataservice.client.QueryResult;
import uk.ac.ebi.uniprot.dataservice.client.exception.ServiceException;

import java.io.IOException;

/**
 * Created by Longes
 */
public class EvaApplication {

    private final static String email = "longesway@gmail.com";

    public static void main(String[] args) {
        String sequence = "MAFSAEDVLKEYDRRRRMEALLLSLYYPNDRKLLDYKEWSPPRVQVECPKAPVEWNNPPS" +
                "EKGLIVGHFSGIKYKGEKAQASEVDVNKMCCWVSKFKDAMRRYQGIQTCKIPGKVLSDLD" +
                "AKIKAYNLTVEGVEGFVRYSRVTKQHVAAFLKELRHSKQYENVNLIHYILTDKRVDIQHL" +
                "EKDLVKDFKALVESAHRMRQGHMINVKYILYQLLKKHGHGPDGPDILTVKTGSKGVLYDD" +
                "SFRKIYTDLGWKFTPL";
        PSIPREDrequester psipredRequester = new PSIPREDrequester(email);
        JpredRequester jpredRequester = new JpredRequester(email);
        try {
            jpredRequester.makeRequest(sequence);
        } catch (IOException e) {
            e.printStackTrace();
        }
        RaptorXRequester raptorXRequester = new RaptorXRequester(email);
        try {
            raptorXRequester.makeRequest(sequence);
        } catch (IOException e) {
            e.printStackTrace();
        }
        UniProtManager uniProtManager = new UniProtManager();
        QueryResult<UniProtEntry> query = null;
        try {
            query = uniProtManager.getByDate();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        int i = 0;
    }
}
