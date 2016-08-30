package com.rostlab.generationModule.PDB;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Longes on 01.06.2016.
 */
public class PDBEntityDTO {

    protected static ObjectMapper om = new ObjectMapper();

    private String acc_id;

    private String dssp;

    public String getAccId() {
        return acc_id;
    }

    public void setStatusId(String acc_id) {
        this.acc_id = acc_id;
    }

    public String getDssp() {
        return dssp;
    }

    public void setDssp(String dssp) {
        this.dssp = dssp;
    }
}
