package com.rostlab.request;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Longes
 */
public class PSIPREDrequester extends Requester {

    public PSIPREDrequester(String email) {
        this.email = email;
    }

    public void makeRequest (String sequence) {
        try {
            this.url = new URL ("http://bioinf.cs.ucl.ac.uk/psipred/submit/?complex=true&coil=false&membrane=false&program=psipred&email=" + email + "&subject=evatest&sequence=" + sequence);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
