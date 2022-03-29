package com.devcation.sns.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor //or @NoArgsConstructor
@Data
public class LombokTest {
    private String id;
    private String userId;
    private String userPw;

}


/*
@Builder 오브젝트 생성을 위한 Builder 패턴을 제공 (디자인 패턴)
 */

class TestBuilder {
    //@Builder 예시
     LombokTest lombokTest =  LombokTest.builder()
             .id("1")
             .userId("devcation")
             .userPw("123123")
             .build();
     //생성자를 이용해서 오브젝트 생성하는것과 같으나 매개변수 순서에 상관없다
}

/*
생성자
@NoArgsConstructor
@AllArgsConstructor

getter setter
@Data
 */

 class TestNoArgsConstructor {
    private String id;
    private String userId;
    private String userPw;

    //@NoArgsConstructor
    public TestNoArgsConstructor(){

    }

    //@AllArgsConstructor
    public TestNoArgsConstructor(String id, String userId, String userPw){

    }

    //@Data getter setter
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

     public String getUserId(){
         return userId;
     }
     public void setUserId(String userId){
         this.userId = id;
     }
     public String getUserPw(){
         return userPw;
     }
     public void setUserPw(String userPw){
         this.userPw = id;
     }

}