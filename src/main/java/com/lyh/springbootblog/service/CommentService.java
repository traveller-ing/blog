package com.lyh.springbootblog.service;

import com.lyh.springbootblog.domain.Comment;

/**
 * Created with IntelliJ IDEA.
 * User: Van
 * Date: 2018/11/6
 * Time: 13:58
 * Description:Comment Service接口
 */

public interface CommentService {
    /**
     * 根据id获取 Comment
     * @param id
     * @return
     */
    Comment getCommentById(Long id);
    /**
     * 删除评论
     * @param id
     * @return
     */
    void removeComment(Long id);
}
