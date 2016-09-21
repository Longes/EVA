package com.rostlab.generationModule.uniprot;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Longes on 31.05.2016.
 */
public interface UniProtDAO {

    UniProtEntity findByAccId (@Param("acc_id") String acc_id);

    UniProtEntity findBySequence (@Param("sequence") String sequence);

    List<UniProtEntity> findAll ();

    void update(@Param("entity") UniProtEntity uniProtEntity);

    void insert(@Param("entity") UniProtEntity uniProtEntity);

}
