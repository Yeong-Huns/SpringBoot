package kr.blog.test.dto;

import kr.blog.test.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //기본생성자 생성
@AllArgsConstructor //모든 인자를 받는 생성자 생성
@Getter
public class AddArticleRequest {
    private String title;
    private String content;

    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    };
    //Article article(title, content);
}
