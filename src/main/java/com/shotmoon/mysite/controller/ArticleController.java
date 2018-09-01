package com.shotmoon.mysite.controller;

import com.shotmoon.mysite.common.ServerResponse;
import com.shotmoon.mysite.domain.Article;
import com.shotmoon.mysite.domain.User;
import com.shotmoon.mysite.service.ArticleService;
import com.shotmoon.mysite.utils.CheckLoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shotmoon
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ServerResponse newArticle(HttpServletRequest request,
                                     String title,
                                     String content,
                                     int categoryId) {
        ServerResponse<User> response = CheckLoginUtil.checkLogin(request);
        if (!response.isSuccess()) {
            return response;
        }

        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCategoryId(categoryId);
        article.setUserId(response.getData().getId());
        article.setVisits(0);
        article.setLikes(0);

        return articleService.addArticle(article);
    }

    @GetMapping("/{articleId}")
    public ServerResponse getArticle(HttpServletRequest request,
                                     @PathVariable Integer articleId) {
        ServerResponse<User> response = CheckLoginUtil.checkLogin(request);
        if (!response.isSuccess()) {
            return response;
        }

        return articleService.getArticle(articleId);
    }

    @PutMapping
    public ServerResponse updateArticle(HttpServletRequest request,
                                        String title,
                                        String content,
                                        int categoryId,
                                        int articleId,
                                        int userId) {
        ServerResponse<User> response = CheckLoginUtil.checkLogin(request);
        if (!response.isSuccess()) {
            return response;
        }
        if (response.getData().getId() != userId) {
            return ServerResponse.createByErrorMessage("无权修改限");
        }

        return articleService.updateArticle(title, content, categoryId, articleId);
    }

    @DeleteMapping("/{articleId}")
    public ServerResponse<String> deleteArticle(@PathVariable int articleId) {
        return articleService.deleteArticle(articleId);
    }
}
