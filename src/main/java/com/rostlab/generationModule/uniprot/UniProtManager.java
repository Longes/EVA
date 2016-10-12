package com.rostlab.generationModule.uniprot;

import com.rostlab.generationModule.PDB.PDBDAO;
import com.rostlab.generationModule.PDB.PDBEntity;
import com.rostlab.requestModule.request.PDBRequester;
import com.rostlab.sifts.map.SiftsMap;
import uk.ac.ebi.kraken.interfaces.uniprot.UniProtEntry;
import uk.ac.ebi.uniprot.dataservice.client.Client;
import uk.ac.ebi.uniprot.dataservice.client.QueryResult;
import uk.ac.ebi.uniprot.dataservice.client.ServiceFactory;
import uk.ac.ebi.uniprot.dataservice.client.alignment.blast.UniParcBlastService;
import uk.ac.ebi.uniprot.dataservice.client.alignment.blast.UniProtBlastService;
import uk.ac.ebi.uniprot.dataservice.client.alignment.blast.UniRefBlastService;
import uk.ac.ebi.uniprot.dataservice.client.exception.ServiceException;
import uk.ac.ebi.uniprot.dataservice.client.uniparc.UniParcQueryBuilder;
import uk.ac.ebi.uniprot.dataservice.client.uniparc.UniParcService;
import uk.ac.ebi.uniprot.dataservice.client.uniprot.UniProtQueryBuilder;
import uk.ac.ebi.uniprot.dataservice.client.uniprot.UniProtService;
import uk.ac.ebi.uniprot.dataservice.client.uniref.UniRefService;
import uk.ac.ebi.uniprot.dataservice.query.Query;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Longes
 */
public class UniProtManager {

    private UniProtDAO uniProtDAO;

    private PDBDAO pdbdao;

    ServiceFactory serviceFactoryInstance = Client.getServiceFactoryInstance();

    // UniProtService
    UniProtService uniprotService = serviceFactoryInstance.getUniProtQueryService();

    // UniParcService
    UniParcService uniparcService = serviceFactoryInstance.getUniParcQueryService();

    // UniRefService
    UniRefService unirefService = serviceFactoryInstance.getUniRefQueryService();

    // Blast Service
    //BlastService blastService = serviceFactoryInstance.getBlastService();

    // UniProt Blast Service
    UniProtBlastService uniProtBlastService = serviceFactoryInstance.getUniProtBlastService();

    // UniParc Blast Service
    UniParcBlastService uniParcBlastService = serviceFactoryInstance.getUniParcBlastService();

    // UniRef Blast Service
    UniRefBlastService uniRefBlastService = serviceFactoryInstance.getUniRefBlastService();

    public QueryResult<UniProtEntry> queryResult;

    public QueryResult<UniProtEntry> getByDate(String start, String end) throws ServiceException {
        uniprotService.start();
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse(start);
            endDate = format.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Query query = UniProtQueryBuilder.created(startDate, endDate);
        Query query = UniParcQueryBuilder.accession("A2BC19");
        QueryResult<UniProtEntry> queryResult = uniprotService.getEntries(query);
        uniprotService.stop();
        return queryResult;
    }

    public void initiateFromUniprot() throws ServiceException {
        uniprotService.start();

        // Search for all Swiss-Prot entries.
        Query query = UniProtQueryBuilder.swissprot();
        queryResult = uniprotService.getEntries(query);
        uniprotService.stop();
        PDBRequester pdbRequester = new PDBRequester();
        while (queryResult.hasNext()) {
            UniProtEntry entry = queryResult.next();
            UniProtEntity entity = new UniProtEntity(entry.getUniProtId().toString(), entry.getSequence().toString());
            try {
                List<String> pdbIds = pdbRequester.makeRequest(entity.acc_id);
                entity.pdb_ids = pdbIds.toString();
                if (entity.pdb_ids.equals("[null]")) {
                    entity.pdb_ids = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            uniProtDAO.insert(entity);
        }
    }

    public UniProtEntity initiateFromId(String accId) throws ServiceException {
        PDBRequester pdbRequester = new PDBRequester();
        uniprotService.start();
        UniProtEntry entry = uniprotService.getEntry(accId);
        UniProtEntity entity = new UniProtEntity(entry.getUniProtId().toString(), entry.getSequence().toString());
        try {
            List<String> pdbIds = pdbRequester.makeRequest(entity.acc_id);
            entity.pdb_ids = pdbIds.toString();
            if (entity.pdb_ids.equals("[null]")) {
                entity.pdb_ids = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
        /*try {
            uniProtDAO.insert(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    private void sift(PDBEntity pdbEntity) {
        SiftsMap map = SiftsMap.newMap(pdbEntity.acc_id);
    }

}
