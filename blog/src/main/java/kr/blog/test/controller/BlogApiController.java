package kr.blog.test.controller;

import kr.blog.test.domain.Article;
import kr.blog.test.dto.AddArticleRequest;
import kr.blog.test.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
