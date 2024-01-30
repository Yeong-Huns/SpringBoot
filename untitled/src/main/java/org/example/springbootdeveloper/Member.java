package org.example.springbootdeveloper;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)  //기본생성자 설정 public || protected (권장)
@AllArgsConstructor
@Getter
@Entity 
/*
    @Entity 어노테이션 : Member 객체를 JPA가 관리하는 엔티티로 지정
    지정시 name속성을 사용하지 않았으면 class명과 동일한 table에 매칭
    -->예제는 Entity(name="") 속성을 사용하지 않아서 자동으로 Member 테이블에 매칭됨.
    
    name 파라미터로 Article 클래스와 member_list 매칭
        ex) @Entity(name="member_list")
            public class Article{
            .....
            }  
* */
public class Member {
    @Id // id 필드를 기본키로 지정 
    //ex) private Long id 를 Id로 지정하겠다...
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 1씩 증가 설정 
    @Column(name = "id", updatable = false) //컬럼(DB 테이블 요소)name은 id / updatable=false(수정불가)
    private Long id; // DB 테이블의 'id' 컬럼과 매칭
    @Column(name = "name", nullable = false) // private String name을 컬럼으로 지정
    // name 컬럼 / nullable=false(비워둘수없음) 
    private String name; // DB 테이블의 'name' 컬럼과 매칭
}
