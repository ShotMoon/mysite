package com.shotmoon.mysite.service.impl;

import com.shotmoon.mysite.common.ServerResponse;
import com.shotmoon.mysite.domain.Article;
import com.shotmoon.mysite.domain.Comment;
import com.shotmoon.mysite.enums.SortEnum;
import com.shotmoon.mysite.repository.CommentRepository;
import com.shotmoon.mysite.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author shotmoon
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public ServerResponse<Comment> addOrUpdateComment(Comment comment) {
        Comment resultComment = commentRepository.save(comment);
        if (resultComment != null) {
            return ServerResponse.createBySuccess(resultComment);
        }

        return ServerResponse.createByErrorMessage("留言失败");
    }


    @Override
    public ServerResponse<String> delteComment(int commentId) {
        try {
            commentRepository.delete(commentId);
        } catch (Exception e) {
            log.error(e.toString());
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    @Override
    public ServerResponse<Page> getCommentList(int page, int size, int sortCode) {
        Sort sort = SortEnum.ASC.getCode() == sortCode ?
                new Sort(Sort.Direction.ASC, "createTime") :
                new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = new PageRequest(page, size, sort);

        Page<Comment> comments = commentRepository.findAll(pageable);
        return ServerResponse.createBySuccess("获取成功", comments);
    }

}
