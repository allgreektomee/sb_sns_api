package com.devcation.sns.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder //롬북 빌더패턴
@NoArgsConstructor   // 매개변수가 없는 생성자
@AllArgsConstructor //모든 맴버 변수를 매개변수로 받는 생성자
@Data               //Getter Setter
@Entity
@Table(name = "sns")
public class SnsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터베이스가 자동으로 AUTO_INCREMENT
    private Integer sid;

    private String userid;
    private String title;
    private String img;
    private String date;
    private String content;
}
