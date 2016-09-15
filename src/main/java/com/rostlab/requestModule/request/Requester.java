package com.rostlab.requestModule.request;

import java.io.*;
import java.net.URL;

/**
 * Created by Longes
 */
public abstract class Requester {
    String email;
    URL url;
    String restInput;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRestInput() {
        return restInput;
    }

    public void setRestInput(String restInput) {
        this.restInput = restInput;
    }

    abstract void makeRequest(String sequence) throws IOException;

    public void sendHTTPRequest() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String strTemp = "";
            while (null != (strTemp = br.readLine())) {
                System.out.println(strTemp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendRESTRequest() {
    }
}