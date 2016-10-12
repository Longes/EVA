package com.rostlab.generationModule.PDB;

import com.rostlab.requestModule.mail.PSIPREDparser;
import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.Chain;
import org.biojava.nbio.structure.StructureException;
import org.biojava.nbio.structure.StructureIO;
import org.biojava.nbio.structure.align.util.AtomCache;
import org.biojava.nbio.structure.io.FileParsingParameters;
import org.biojava.nbio.structure.secstruc.DSSPParser;
import org.biojava.nbio.structure.secstruc.SecStrucState;

import java.io.IOException;
import java.util.List;

import static com.sun.xml.internal.bind.v2.util.EditDistance.editDistance;

/**
 * Created by Longes on 26.05.2016.
 */
public class PDBEntity {
    public String acc_id;
    public String dssp;
    public Double similarity;

    public PDBEntity(String acc_id) {
        this.acc_id = acc_id;
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

    public void setStructure() {
        FileParsingParameters params = new FileParsingParameters();
        //Only change needed to the normal Structure loading
        params.setParseSecStruc(true); //this is false as DEFAULT

        AtomCache cache = new AtomCache();
        cache.setFileParsingParams(params);

        Structure s = null;
        //The loaded Structure contains the SS assigned
        try {
            s = cache.getStructure(acc_id);
            List<SecStrucState> list = DSSPParser.fetch(acc_id, s, true);
            String slist = "";
            for (SecStrucState struct : list) {
                if (struct.getType().toString().equals(" ")) {
                    slist += "-";
                } else {
                    slist += struct.getType().toString();
                }
            }
            dssp = slist;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (StructureException e) {
            e.printStackTrace();
        }
    }

    public void comparePSIPRED() {
        PSIPREDparser psipreDparser = new PSIPREDparser("test.txt");//acc_id + ".txt");
        try {
            psipreDparser.parsePSIPREDMail();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("\nPDB SS: " + dssp);
        System.out.print("\nSimilarity: "+ similarity(dssp, psipreDparser.jpred));
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
