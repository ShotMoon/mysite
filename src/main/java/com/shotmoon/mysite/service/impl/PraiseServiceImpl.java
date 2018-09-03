package com.shotmoon.mysite.service.impl;

import com.shotmoon.mysite.common.ServerResponse;
import com.shotmoon.mysite.domain.Praise;
import com.shotmoon.mysite.repository.PraiseRepository;
import com.shotmoon.mysite.service.PraiseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shotmoon
 */
@Service
@Slf4j
public class PraiseServiceImpl implements PraiseService {

    @Autowired
    private PraiseRepository praiseRepository;

    @Override
    public ServerResponse<String> isPraise(int userId, int articleId) {
        Praise praise = praiseRepository.findByUserIdAndArticleId(userId, articleId);
        if (praise != null) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse<String> praise(int userId, int articleId) {
        Praise like = new Praise();
        like.setUserId(userId);
        like.setArticleId(articleId);

        Praise resultLike = praiseRepository.save(like);

        if (resultLike != null) {
            return ServerResponse.createBySuccessMessage("点赞成功");
        }
        return ServerResponse.createByErrorMessage("点赞失败");
    }

    @Override
    public ServerResponse<String> unPraise(int userId, int articleId) {
        try {
            praiseRepository.deleteByUserIdAndArticleId(userId, articleId);
        } catch (Exception e) {
            log.error(e.toString());
            return ServerResponse.createByErrorMessage("取消点赞失败");
        }

        return ServerResponse.createBySuccessMessage("取消点赞成功");
    }
}
