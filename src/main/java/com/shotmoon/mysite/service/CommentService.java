package com.shotmoon.mysite.service;

import com.shotmoon.mysite.common.ServerResponse;
import com.shotmoon.mysite.domain.Comment;
import org.springframework.data.domain.Page;

/**
 * @author shotmoon
 */
public interface CommentService {

    ServerResponse<Comment> addOrUpdateComment(Comment comment);

    ServerResponse<String> delteComment(int commentId);

    ServerResponse<Page> getCommentList(int page, int size, int sortCode);
}
