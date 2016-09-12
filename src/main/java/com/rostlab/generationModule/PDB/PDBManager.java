package com.rostlab.generationModule.PDB;

import com.rostlab.generationModule.uniprot.UniProtEntity;
import com.rostlab.request.PDBRequester;
import com.rostlab.sifts.map.SiftsMap;
import uk.ac.ebi.kraken.interfaces.uniprot.UniProtEntry;
import uk.ac.ebi.uniprot.dataservice.client.QueryResult;

import java.io.IOException;
import java.util.List;

/**
 * Created by Longes on 12.09.2016.
 */
public class PDBManager {

    public void initiateDatabase(QueryResult<UniProtEntry> queryResult) {
        while (queryResult.hasNext()) {
            UniProtEntry entry = queryResult.next();
            UniProtEntity entity = new UniProtEntity(entry.getUniProtId().toString(), entry.getSequence().toString());
            PDBRequester pdbRequester = new PDBRequester();
            try {
                List<String> pdbIds = pdbRequester.makeRequest(entity.acc_id);
                for (String string : pdbIds)
                {
                    System.out.println(string);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
