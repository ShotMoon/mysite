package com.shotmoon.mysite.service;

import com.shotmoon.mysite.common.ServerResponse;
import com.shotmoon.mysite.domain.Article;

/**
 * @author shotmoon
 */
public interface ArticleService {

    ServerResponse<String> addArticle(Article article);

    ServerResponse<Article> getArticle(Integer articleId);

    ServerResponse<String> updateArticle(String title, String content, int categoryId, int articlrId);

    ServerResponse<String> deleteArticle(int articleId);
}
