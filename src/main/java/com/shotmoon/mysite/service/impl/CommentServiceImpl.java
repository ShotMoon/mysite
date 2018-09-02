package com.shotmoon.mysite.service.impl;

import com.shotmoon.mysite.common.ServerResponse;
import com.shotmoon.mysite.domain.Comment;
import com.shotmoon.mysite.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * @author shotmoon
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public ServerResponse<String> addComment(Comment comment) {
        //TODO
        return null;
    }
}
