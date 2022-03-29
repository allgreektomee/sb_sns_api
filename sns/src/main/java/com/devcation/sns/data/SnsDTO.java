package com.devcation.sns.data;

import com.devcation.sns.model.SnsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Data Transfer Object DTO

@NoArgsConstructor  // 인자가 없는
@AllArgsConstructor // 인자가 있는
@Data // getter setter
public class SnsDTO {
    private Integer id;
    private String title;
    private String img;
    private String date;
    private String content;

    //response
    //SnsEntity로 DB 조회 결과를 넘겨받고 SnsDTO에 대입
    //SnsEntity를 Response해주는게 아니라 SnsDTO로 변환해서 넘겨준다
    public SnsDTO (final SnsEntity snsEntity) {
        this.id = snsEntity.getSid();
        this.title = snsEntity.getTitle();
        this.img = snsEntity.getImg();
        this.date = snsEntity.getDate();
        this.content = snsEntity.getContent();

    }

    //request
    public static SnsEntity toSnsEntity(final SnsDTO dto) {
        return SnsEntity.builder()
                .sid(dto.getId())
                .title(dto.getTitle())
                .img(dto.getImg())
                .date(dto.getDate())
                .content(dto.getContent())
                .build();
    }

}
