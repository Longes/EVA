package com.rostlab.PDB;

import org.apache.ibatis.annotations.Param;

/**
 * Created by Longes on 31.05.2016.
 */
public interface PDBDAO {

    PDBEntity findByAccId(@Param("acc_id") String acc_id);

    PDBEntity findByDssp(@Param("dssp") String dssp);

    void insertEntry(@Param("entity") PDBEntityDTO);

}