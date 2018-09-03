package com.shotmoon.mysite.controller;

import com.shotmoon.mysite.common.ServerResponse;
import com.shotmoon.mysite.domain.Comment;
import com.shotmoon.mysite.domain.User;
import com.shotmoon.mysite.service.CommentService;
import com.shotmoon.mysite.utils.CheckLoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shotmoon
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ServerResponse addComment(HttpServletRequest httpServletRequest,
                                     @RequestBody Comment comment) {
        ServerResponse<User> response = CheckLoginUtil.checkLogin(httpServletRequest);
        if (!response.isSuccess()) {
            return response;
        }

        comment.setUserId(response.getData().getId());

        return commentService.addOrUpdateComment(comment);
    }

    @DeleteMapping
    public ServerResponse deleteComment(HttpServletRequest httpServletRequest,
                                        @RequestParam int commentId) {
        ServerResponse<User> response = CheckLoginUtil.checkLogin(httpServletRequest);
        if (!response.isSuccess()) {
            return response;
        }

        return commentService.delteComment(commentId);
    }
}
