package kr.blog.test.dto;

import kr.blog.test.domain.Article;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ArticleListViewResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final int readcount;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    public ArticleListViewResponse(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.readcount = article.getReadcount();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
    }
}
