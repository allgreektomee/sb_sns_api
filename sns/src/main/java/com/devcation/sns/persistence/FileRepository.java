package com.devcation.sns.persistence;

import com.devcation.sns.model.FileEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository  extends JpaRepository<FileEntity, String> {


//    List<FileEntity> findByFilecode(String filecode);

    List<FileEntity> findByUseridAndSnsid(String userid, Integer snsid);


}