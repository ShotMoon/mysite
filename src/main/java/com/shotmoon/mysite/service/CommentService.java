package com.shotmoon.mysite.service;

import com.shotmoon.mysite.common.ServerResponse;
import com.shotmoon.mysite.domain.Comment;

/**
 * @author shotmoon
 */
public interface CommentService {

    ServerResponse<Comment> addOrUpdateComment(Comment comment);

    ServerResponse<String> delteComment(int commentId);

}
