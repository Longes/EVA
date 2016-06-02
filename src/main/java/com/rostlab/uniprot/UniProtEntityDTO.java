package com.rostlab.uniprot;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Longes on 01.06.2016.
 */
public class UniProtEntityDTO {

    protected static ObjectMapper om = new ObjectMapper();

    private String acc_id;

    private String sequence;

    public String getAccId() {
        return acc_id;
    }

    public void setStatusId(String acc_id) {
        this.acc_id = acc_id;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
}
