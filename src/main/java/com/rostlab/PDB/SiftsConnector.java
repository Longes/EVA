package com.rostlab.PDB;

import com.rostlab.sifts.map.SiftsMap;

/**
 * Created by Longes on 22.07.2016.
 */
public class SiftsConnector {

    public String sift (String pdbId) {
        SiftsMap map = SiftsMap.newMap(pdbId);
        return map.getUniprotAc();
    }

}
