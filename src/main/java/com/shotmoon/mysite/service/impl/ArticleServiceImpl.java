package com.shotmoon.mysite.service.impl;

import com.shotmoon.mysite.common.ServerResponse;
import com.shotmoon.mysite.domain.Article;
import com.shotmoon.mysite.repository.ArticleRepository;
import com.shotmoon.mysite.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shotmoon
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public ServerResponse<String> addArticle(Article article) {
        Article resultArticle = articleRepository.save(article);
        if (resultArticle != null) {
            return ServerResponse.createBySuccessMessage("发布成功");
        }

        return ServerResponse.createByErrorMessage("发布失败");
    }

    @Override
    public ServerResponse<Article> getArticle(Integer articleId) {
        Article article = articleRepository.findOne(articleId);

        if (article != null) {
            return ServerResponse.createBySuccess(article);
        }
        return ServerResponse.createByErrorMessage("获取文章信息失败");
    }

    @Override
    public ServerResponse<String> updateArticle(String title, String content, int categoryId, int articleId) {
        int resultCount = articleRepository.updateArticleContent(title, content, categoryId, articleId);
        if (resultCount != 0) {
            return ServerResponse.createBySuccessMessage("文章更新成功");
        }
        return ServerResponse.createByErrorMessage("文章更新失败");
    }

    @Override
    public ServerResponse<String> deleteArticle(int articleId) {

        try {
            articleRepository.delete(articleId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return ServerResponse.createBySuccessMessage("删除成功");
    }
}
