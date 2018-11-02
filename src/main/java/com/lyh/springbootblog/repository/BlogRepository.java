package com.lyh.springbootblog.repository;

import com.lyh.springbootblog.domain.Blog;
import com.lyh.springbootblog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * Author: Van
 * Date: 2018/10/29
 * Time: 下午9:33
 * Description:Blog仓库
 */
public interface BlogRepository extends JpaRepository<Blog, Long> {

    /**
     * 根据用户名、博客标题分页查询博客列表
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> findByUserAndTitleLike(User user, String title, Pageable pageable);


    /**
     * 根据用户名、博客标题查询博客列表（时间逆序）
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(String title, User user, String tags, User user2, Pageable pageable);

}
