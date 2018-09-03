package com.shotmoon.mysite.service;

import com.shotmoon.mysite.common.ServerResponse;

/**
 * @author shotmoon
 */
public interface PraiseService {

    ServerResponse<String> isPraise(int userId, int articleId);

    ServerResponse<String> praise(int userId, int articleId);

    ServerResponse<String> unPraise(int userId, int articleId);
}
