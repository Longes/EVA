package com.rostlab.PDB;

/**
 * Created by Longes on 26.05.2016.
 */
public class PDBEntity {
    public String acc_id;
    public String dssp;

    public PDBEntity(String acc_id, String dssp) {
        this.acc_id = acc_id;
        this.dssp = dssp;
    }
}
