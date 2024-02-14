package kr.blog.test.controller;

import kr.blog.test.domain.Article;
import kr.blog.test.dto.AddArticleRequest;
import kr.blog.test.dto.ArticleResponse;
import kr.blog.test.dto.RequestUpdateArticle;
import kr.blog.test.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
      Article savedArticle = blogService.save(request);
      return ResponseEntity.status(HttpStatus.CREATED)
              .body(savedArticle);
    };
    @GetMapping("/api/articles") //전체 조회
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(articles);
    }
    @GetMapping("/api/articles/{id}") //한건 조회
    public ResponseEntity<ArticleResponse> findbyId(@PathVariable long id){
        Article article = blogService.findbyId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ArticleResponse(article));
    }
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody RequestUpdateArticle request){
        Article upDatearticle = blogService.updateArticle(id, request);
        return ResponseEntity.ok()
                .body(upDatearticle);
    }
}
