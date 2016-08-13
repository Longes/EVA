package com.rostlab.PDB;

import com.rostlab.mail.PSIPREDparser;
import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.Chain;
import org.biojava.nbio.structure.StructureIO;

import static com.sun.xml.internal.bind.v2.util.EditDistance.editDistance;

/**
 * Created by Longes on 26.05.2016.
 */
public class PDBEntity {
    public String acc_id;
    public String dssp;
    public Double similarity;

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

    public void comparePSIPRED() {
        PSIPREDparser psipreDparser = new PSIPREDparser(acc_id + ".txt");
        this.similarity = similarity(dssp, psipreDparser.jpred);
    }

    public static double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2; shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) { return 1.0; /* both strings are zero length */ }
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
    }

}
