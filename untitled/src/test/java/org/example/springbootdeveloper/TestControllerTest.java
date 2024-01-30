package org.example.springbootdeveloper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest //스프링 부트 테스트용 어노테이션
/*
 @SpringBootTest : @SpringBootApplication이 있는 클래스를 찾고,
 그 클래스에 포함되어 있는 Bean을 찾은 다음, 테스트용 애플리케이션 컨텍스트를 만든다.
* */
@AutoConfigureMockMvc // MockMVC 생성 및 자동 구성
/*
 @AutoConfigureMockMvc : MockMvc를 생성하고 자동으로 구성하는 어노테이션.
 MockMvc는 애플리케이션을 서버에 배포하지 않고도 테스트용 MVC 환경을 만들어 요청 및 전송,
 응답 기능을 제공하는 유틸리티 클래스이다. 즉, 컨트롤러를 테스트할 때 사용되는 클래스이다.
*/
class TestControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach // 매번 테스트 전 실행하는 메서드
    //MockMvcSetUp을 MockMvc를 설정해줌.
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach // 테스트 실행 후 실행하는 메서드
    //cleanUp 메서드를 사용해 member 테이블에 있는 데이터들을 모두 삭제.
    public void cleanUp(){
        memberRepository.deleteAll();
    }

    @DisplayName("getAllMembers: 아티클 조회에 성공한다.")
    @Test
    public void goetAllmembers() throws Exception {
        //given
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L, "홍길동"));
        //when
        final ResultActions result = mockMvc.perform(get(url) // 1
                .accept(MediaType.APPLICATION_JSON)); // 2
        //then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }
}