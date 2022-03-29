package com.devcation.sns.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Data Transfer Object DTO
@Builder //  Builder패턴으로 객체 생성
@NoArgsConstructor  // 인자가 없는
@AllArgsConstructor // 인자가 있는
@Data // getter setter
public class _SnsDTO {
    private int sid;
    private String title;
    private String img;
    private String date;
    private String content;
}
