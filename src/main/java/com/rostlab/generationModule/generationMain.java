package com.rostlab.generationModule;

import com.rostlab.generationModule.PDB.PDBManager;
import com.rostlab.generationModule.uniprot.UniProtManager;
import uk.ac.ebi.uniprot.dataservice.client.exception.ServiceException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Longes on 26.08.2016.
 */
public class generationMain {

    public ArrayList<String> accIdList = new ArrayList<>();
    private final static Charset ENCODING = StandardCharsets.UTF_8;
    private Path path;

    public void readFileByLine(Path path) throws IOException {
        try (Scanner scanner =  new Scanner(path, ENCODING.name())){
            while (scanner.hasNextLine()){
                accIdList.add(scanner.nextLine());
            }
        }
    }

    public void downloadEntries(boolean fromSwissProt) {
        UniProtManager uniProtManager = new UniProtManager();
        PDBManager pdbManager = new PDBManager();
        if (fromSwissProt) {
            try {
                uniProtManager.initiateDatabase();
                pdbManager.initiateDatabase(uniProtManager.queryResult);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        } else {
            ;
        }
    }
}
