package com.rostlab.requestModule;

import com.rostlab.generationModule.uniprot.UniProtDAO;
import com.rostlab.generationModule.uniprot.UniProtEntity;
import com.rostlab.requestModule.request.JpredRequester;
import com.rostlab.requestModule.request.PSIPREDrequester;
import com.rostlab.requestModule.request.RaptorXRequester;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Longes on 15.09.2016.
 */
public class requestMain {

    private UniProtDAO uniProtDAO;

    public String email = "alex.galtsev@tum.de";

    public void jpredRequest(String sequence) {
        JpredRequester jpredRequester = new JpredRequester(email);
        try {
            jpredRequester.makeRequest(sequence);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void raptorXRequest(String sequence) {
        RaptorXRequester raptorXRequester = new RaptorXRequester(email);
        try {
            raptorXRequester.makeRequest(sequence);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void psipredRequest(String sequence) {
        PSIPREDrequester psipredRequester = new PSIPREDrequester(email);
        psipredRequester.makeRequest(sequence);
    }

    public void requestAll() {
        List<UniProtEntity> uniprots = uniProtDAO.findAll();
        for (Iterator<UniProtEntity> i = uniprots.iterator(); i.hasNext(); ) {
            UniProtEntity entity = i.next();
            jpredRequest(entity.sequence);
            raptorXRequest(entity.sequence);
            psipredRequest(entity.sequence);
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
