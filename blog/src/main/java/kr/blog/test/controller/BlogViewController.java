package kr.blog.test.controller;

import kr.blog.test.domain.Article;
import kr.blog.test.dto.ArticleListViewResponse;
import kr.blog.test.dto.ArticleViewResponse;
import kr.blog.test.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {
    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponse> articles = blogService.findAll()
                .stream().map(ArticleListViewResponse::new).toList();
        model.addAttribute("articles", articles);
        return "articleList";
    }
    @GetMapping("/article/{id}")
    public String getArticle(@PathVariable long id, Model model){
        Article article = blogService.findbyId(id);
        model.addAttribute("article", new ArticleViewResponse(article));
        return "article";
    }
}
