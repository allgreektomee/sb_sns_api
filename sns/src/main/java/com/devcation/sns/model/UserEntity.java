package com.devcation.sns.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
//https://araikuma.tistory.com/692  컬럼에 UNIQUE 제약 조건을 설정하면 대상의 컬럼에 중복 된 값이 저장될 수 없다.

public class UserEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id; // 유저에게 고유하게 부여되는 id.


//    @Column의 속성 nullable 기본값은 true
//    false 설정시  not null
    @Column(nullable = false)
    private String username; // 유저의 이름

    @Column(nullable = false)
    private String email; // 유저의 email, 아이디와 같은 기능을 한다.

    @Column(nullable = false)
    private String password;
}