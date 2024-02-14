package kr.blog.test.dto;

import kr.blog.test.domain.Article;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ArticleListViewResponse {
    private final Long id;
    private final String name;
    private final String content;
    public ArticleListViewResponse(Article article){
        this.id = article.getId();
        this.name = article.getTitle();
        this.content = article.getContent();
    }
}
