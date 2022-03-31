package com.devcation.sns.persistence;

import com.devcation.sns.model.SnsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnsRepository extends JpaRepository<SnsEntity, Integer> {


    List<SnsEntity> findByUserid(String userid); // 특정 사용자 전체 게시글

    SnsEntity findBySid(Integer sid); //특정 게시글(상셰)
    List<SnsEntity> findAll();

}
