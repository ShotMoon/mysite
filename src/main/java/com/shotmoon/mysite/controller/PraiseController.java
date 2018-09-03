package com.shotmoon.mysite.controller;

import com.shotmoon.mysite.common.ServerResponse;
import com.shotmoon.mysite.domain.User;
import com.shotmoon.mysite.service.PraiseService;
import com.shotmoon.mysite.utils.CheckLoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shotmoon
 */
@RestController
@RequestMapping("/praise")
public class PraiseController {

    @Autowired
    private PraiseService praiseService;

    @GetMapping
    public ServerResponse isPraise(HttpServletRequest httpServletRequest,
                                 int articleId) {
        ServerResponse<User> response = CheckLoginUtil.checkLogin(httpServletRequest);
        if (!response.isSuccess()) {
            return response;
        }

        return praiseService.isPraise(response.getData().getId(), articleId);
    }

    @PostMapping
    public ServerResponse Praise(HttpServletRequest httpServletRequest,
                                 int articleId) {
        ServerResponse<User> response = CheckLoginUtil.checkLogin(httpServletRequest);
        if (!response.isSuccess()) {
            return response;
        }

        return praiseService.praise(response.getData().getId(), articleId);
    }

    @DeleteMapping
    public ServerResponse unPraise(HttpServletRequest httpServletRequest,
                               int articleId) {
        ServerResponse<User> response = CheckLoginUtil.checkLogin(httpServletRequest);
        if (!response.isSuccess()) {
            return response;
        }

        return praiseService.unPraise(response.getData().getId(), articleId);
    }
}
