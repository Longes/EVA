package com.rostlab.requestModule;

import com.rostlab.requestModule.request.JpredRequester;

import java.io.IOException;

/**
 * Created by Longes on 15.09.2016.
 */
public class requestMain {
    public void jpredRequest(String sequence) {
        JpredRequester jpredRequester = new JpredRequester(email);
        try {
            jpredRequester.makeRequest(sequence);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
