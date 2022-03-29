package com.devcation.sns.service;

import com.devcation.sns.model.SnsEntity;
import com.devcation.sns.persistence.SnsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//퍼시스턴스 컨트롤러 사이에서 비즈니스로직 처리

@Slf4j
@Service
public class SnsService {

    @Autowired
    private SnsRepository snsRepository;


    public String sampleService() {

        return "hello service";
    }

    public String sampleService2() {
        // SnsEntity 생성
        SnsEntity entity = SnsEntity.builder().title("안녕하세요")
                                            .content("jpa 테스트 게시글입니다.")
                                            .date("2020-03-29")
                                            .img("http://sns.com/img.png")
                                            .build();
        // SnsEntity 저장
        snsRepository.save(entity);
        // SnsEntity 검색
        SnsEntity snsEntity = snsRepository.findById(entity.getSid()).get();

        log.info("sid : {} ", snsEntity.getSid());

        return snsEntity.getTitle();
    }

//    private Boolean isValidation(final SnsEntity snsEntity) {
    private void isValidation(final SnsEntity snsEntity) {

        if(snsEntity == null) {
            log.warn("snsEntity == null");
            throw new RuntimeException("snsEntity is null.");
//            return false;
        }

        if(snsEntity.getTitle() == null) {
            log.warn("snsEntity.getTitle() == null");
            throw new RuntimeException(".getTitle() is null.");
//            return false;
        }
    }

    public List<SnsEntity> addSns(final SnsEntity snsEntity) {
        // Validations
//        if (isValidation(snsEntity)) return null;

        isValidation(snsEntity);
        snsRepository.save(snsEntity);
        log.info("addPost sid = {} ", snsEntity.getSid());
        return snsRepository.findBySid(snsEntity.getSid());
    }

    public List<SnsEntity> listSns() {

        return snsRepository.findAll();
    }

    public List<SnsEntity> updateSns(final SnsEntity snsNewEntity) {


        isValidation(snsNewEntity); // 널체크


        final Optional<SnsEntity> snsEntity = snsRepository.findById(snsNewEntity.getSid()); // 저장된 게시글 가져오기



        snsEntity.ifPresent(sns -> {
            // 업데이트
            sns.setTitle(snsNewEntity.getTitle());
            sns.setImg(snsNewEntity.getImg());
            sns.setDate(snsNewEntity.getDate());
            sns.setContent(snsNewEntity.getContent());

            //저장
            snsRepository.save(sns);
        });


        //업데이트 내역 전달
        return snsRepository.findAll();
    }

    public List<SnsEntity> deleteSns(final SnsEntity snsEntity) {

        isValidation(snsEntity);

        try {

            snsRepository.delete(snsEntity);
        } catch(Exception e) {

            log.error("error deleting entity ", snsEntity.getSid(), e);

            throw new RuntimeException("error" + snsEntity.getSid());
        }

        return snsRepository.findAll();
    }
}
