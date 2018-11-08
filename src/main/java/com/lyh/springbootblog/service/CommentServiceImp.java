package com.lyh.springbootblog.service;

import com.lyh.springbootblog.domain.Comment;
import com.lyh.springbootblog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Van
 * Date: 2018/11/6
 * Time: 14:00
 * Description: Comment Service接口实现
 */
@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public void removeComment(Long id) {
        commentRepository.deleteById(id);
    }

}
