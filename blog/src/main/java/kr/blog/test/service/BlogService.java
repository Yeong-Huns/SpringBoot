package kr.blog.test.service;

import kr.blog.test.domain.Article;
import kr.blog.test.dto.AddArticleRequest;
import kr.blog.test.dto.RequestUpdateArticle;
import kr.blog.test.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor // final 이나 @NotNull 붙은 필드의 생성자 추가
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    //글 추가 메서드
    public Article save(AddArticleRequest request){return blogRepository.save(request.toEntity());}
    public List<Article> findAll(){return blogRepository.findAll();}
    //database에서 모든 Article을 찾아서 리스트로 만든다.
    public Article findbyId(long id){
        return blogRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
    public void delete(long id){
        blogRepository.deleteById(id);
    }
    @Transactional
    public Article updateArticle(long id, RequestUpdateArticle request){
        Article article = blogRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        article.update(request.getTitle(), request.getContent());
        return article;
    }
}

