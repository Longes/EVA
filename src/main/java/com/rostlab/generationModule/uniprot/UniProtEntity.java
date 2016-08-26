package com.rostlab.generationModule.uniprot;

/**
 * Created by Longes on 26.05.2016.
 */
public class UniProtEntity {
    public String acc_id;
    public String sequence;

    public UniProtEntity(String acc_id, String sequence) {
        this.acc_id = acc_id;
        this.sequence = sequence;
    }
}
