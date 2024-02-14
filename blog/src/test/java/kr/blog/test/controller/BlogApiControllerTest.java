package kr.blog.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.blog.test.domain.Article;
import kr.blog.test.dto.AddArticleRequest;
import kr.blog.test.dto.ArticleResponse;
import kr.blog.test.dto.RequestUpdateArticle;
import kr.blog.test.repository.BlogRepository;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        blogRepository.deleteAll();
    }

    @DisplayName("addArticle: 블로그 글 추가에 성공")
    @Test
    public void addArticle() throws  Exception{
        //given
        final String url = "/api/articles"; //Http Path
        final String title = "제목"; //title: 제목
        final String content = "첫번째 게시글입니다."; //content: 첫번째 게시글입니다.
        final AddArticleRequest userRequest = new AddArticleRequest(title, content); //


        //객체를 JSON으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest);
        // requestBody = {"title":"제목","content":"첫번째 게시글입니다."}

        //when
        ResultActions result = mockMvc.perform(post(url)  //Method: POST , Path: /api/articles
                .contentType(MediaType.APPLICATION_JSON_VALUE) // HttpBody: JSON
                .content(requestBody)); // {"title":"제목","content":"첫번째 게시글입니다."}
        //then
        result.andExpect(status().isCreated()); // Http 상태코드 검증 : isCreated(201)을 기대중
        List<Article> articles = blogRepository.findAll(); //blogRepository에 저장된 모든값을 찾음

        assertThat(articles.size()).isEqualTo(1); //크기가 1인지 검증 (내가 하나 넣었으니깐)
        assertThat(articles.get(0).getTitle()).isEqualTo(title); //제목일치 확인
        assertThat(articles.get(0).getContent()).isEqualTo(content); //내용 일치 확인
    }
    //테스트 내역 : Http_status : 201 뜨는지 확인, /api/article 로 JSON_BODY POST시 결과(글 저장)기능 검증

    @DisplayName("블로그 글 조회에 성공한다.")
    @Test
    public void findAllArticles() throws Exception{
        //Given 블로그 글을 저장한다.
        final String url = "/api/articles";
        final String title = "제목 1";
        final String content = "내용 1";
        blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());
        //when 목록조회API를 호출한다.
        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));
        //Then 응답코드가 200, 반환값을 확인한다.
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title));
    }
    @DisplayName("ID로 글 조회")
    @Test
    public void findById() throws Exception{
        final String url = "/api/articles/{id}";
        final String title = "테스트 1";
        final String content = "내용 테스트";
        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());
        //블로그 글 저장
        final ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId()));
        //저장한 글을 id 로 가져옴
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.title").value(title));
        //응답코드 200, content title 확인
    }
    @DisplayName("블로그 글 삭제에 성공한다.")
    @Test
    public void deleteArticle() throws Exception{
        //given 저장
        final String url = "/api/articles/{id}";
        final String title = "제목1";
        final String content = "내용1";
        Article savedarticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());
        //when
        mockMvc.perform(delete(url, savedarticle.getId()))
                .andExpect(status().isOk());
        List<ArticleResponse> articleResponses = blogRepository.findAll().stream()
                .map(ArticleResponse::new)
                .toList();
        assertThat(articleResponses).isEmpty();
    }
    @DisplayName("블로그 글 업데이트에 성공한다.")
    @Test
    public void updateName() throws Exception{
        final String Url = "/api/articles/{id}";
        final String title = "원래 제목1";
        final String content = "바뀐 제목1";
        Article saved = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());
        final String newtitle = "바뀐 제목";
        final String newcontent = "바뀐 내용";
        RequestUpdateArticle request = new RequestUpdateArticle(newtitle, newcontent);
        ResultActions resultActions =  mockMvc.perform(put(Url, saved.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));
        resultActions.andExpect(status().isOk());
        Article article = blogRepository.findById(saved.getId()).get();
        //Optional
        assertThat(article.getTitle()).isEqualTo(newtitle);
        assertThat(article.getContent()).isEqualTo(newcontent);
    }
}