package com.rostlab.requestModule;

import com.rostlab.requestModule.request.JpredRequester;
import com.rostlab.requestModule.request.PSIPREDrequester;
import com.rostlab.requestModule.request.RaptorXRequester;

import java.io.IOException;

/**
 * Created by Longes on 15.09.2016.
 */
public class requestMain {

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
    }
}
