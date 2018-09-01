package com.shotmoon.mysite.service;

import com.shotmoon.mysite.common.ServerResponse;
import com.shotmoon.mysite.domain.Article;

/**
 * @author shotmoon
 */
public interface ArticleService {

    public ServerResponse<String> addArticle(Article article);

    public ServerResponse<Article> getArticle(Integer articleId);

    public ServerResponse<String> updateArticle(String title, String content, int categoryId, int articlrId);

    public ServerResponse<String> deleteArticle(int articleId);
}
