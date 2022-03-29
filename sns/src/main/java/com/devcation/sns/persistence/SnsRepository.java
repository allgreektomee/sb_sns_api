package com.devcation.sns.persistence;

import com.devcation.sns.model.SnsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnsRepository extends JpaRepository<SnsEntity, Integer> {


    List<SnsEntity> findBySid(Integer sid); // 특정 게시글
    List<SnsEntity> findAll();

}
