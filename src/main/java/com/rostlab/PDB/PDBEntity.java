package com.rostlab.PDB;

import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.Chain;
import org.biojava.nbio.structure.StructureIO;

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

    public void parsePDB() {
        try {

            Structure s = StructureIO.getStructure(acc_id);

            for ( Chain c : s.getChains()) {

                // only the observed residues
                System.out.println(c.getAtomSequence());

                // print biological sequence
                System.out.println(c.getSeqResSequence());
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
