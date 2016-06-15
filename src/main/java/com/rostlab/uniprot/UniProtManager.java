package com.rostlab.uniprot;

import com.rostlab.PDB.PDBEntity;
import com.rostlab.sifts.map.SiftsMap;
import uk.ac.ebi.kraken.interfaces.uniprot.UniProtEntry;
import uk.ac.ebi.uniprot.dataservice.client.Client;
import uk.ac.ebi.uniprot.dataservice.client.QueryResult;
import uk.ac.ebi.uniprot.dataservice.client.ServiceFactory;
import uk.ac.ebi.uniprot.dataservice.client.alignment.blast.BlastService;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Longes
 */
public class UniProtManager {

    private UniProtDAO uniProtDAO;

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

    public QueryResult<UniProtEntry> getByDate() throws ServiceException {
        uniprotService.start();
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse("01-01-2008");
            endDate = format.parse("01-12-2008");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Query query = UniProtQueryBuilder.created(startDate, endDate);
        Query query = UniParcQueryBuilder.accession("A2BC19");
        QueryResult<UniProtEntry> queryResult = uniprotService.getEntries(query);
        uniprotService.stop();
        return queryResult;
    }

    private void initiateDatabase() throws ServiceException {
        uniprotService.start();

        // Search for all Swiss-Prot entries.
        Query query = UniProtQueryBuilder.swissprot();
        QueryResult<UniProtEntry> queryResult = uniprotService.getEntries(query);
        uniprotService.stop();
        while (queryResult.hasNext()) {
            UniProtEntry entry = queryResult.next();
            UniProtEntity entity = new UniProtEntity(entry.getUniProtId().toString(), entry.getSequence().toString());
            uniProtDAO.insert(entity);
            SiftsMap map = SiftsMap.newMap(entity.acc_id);
        }
    }

    private void sift(PDBEntity pdbEntity) {
        SiftsMap map = SiftsMap.newMap(pdbEntity.acc_id);
    }

}
