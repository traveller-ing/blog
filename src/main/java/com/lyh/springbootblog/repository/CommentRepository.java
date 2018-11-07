package com.lyh.springbootblog.repository;

import com.lyh.springbootblog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: Van
 * Date: 2018/11/6
 * Time: 13:56
 * Description: Comment Repository 接口
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
