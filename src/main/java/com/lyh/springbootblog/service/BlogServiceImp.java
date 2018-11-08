package com.lyh.springbootblog.service;

import com.lyh.springbootblog.domain.Blog;
import com.lyh.springbootblog.domain.Comment;
import com.lyh.springbootblog.domain.User;
import com.lyh.springbootblog.domain.Vote;
import com.lyh.springbootblog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * Author: Van
 * Date: 2018/10/29
 * Time: 下午9:47
 * Description:Blog服务
 */
@Service
public class BlogServiceImp implements BlogService {

    @Autowired
    private BlogRepository blogRepository;


    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }


    @Transactional
    @Override
    public void removeBlog(Long id) {
        blogRepository.deleteById(id);
    }


    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).get();
    }

    @Override
    public Page<Blog> listBlogsByTitleVote(User user, String title, Pageable pageable) {
        // 模糊查询
        title = "%" + title + "%";
        String tags = title;
        Page<Blog> blogs = blogRepository.findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(title, user, tags, user, pageable);
        return blogs;
    }

    @Override
    public Page<Blog> listBlogsByTitleVoteAndSort(User user, String title, Pageable pageable) {
        // 模糊查询
        title = "%" + title + "%";
        Page<Blog> blogs = blogRepository.findByUserAndTitleLike(user, title, pageable);
        return blogs;
    }

    @Override
    public void readingIncrease(Long id) {
        Blog blog = blogRepository.findById(id).get();
        blog.setReadSize(blog.getReadSize() + 1); // 在原有的阅读量基础上递增1
        blogRepository.save(blog);
    }

    @Override
    public Blog createComment(Long blogId, String commentContent) {
        Blog originalBlog = blogRepository.findById(blogId).get();
//        if(optionalBlog.isPresent()) {
//            originalBlog = optionalBlog;
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Comment comment = new Comment(user, commentContent);
            originalBlog.addComment(comment);
//        }
        return this.saveBlog(originalBlog);
    }

    @Override
    public void removeComment(Long blogId, Long commentId) {
        Blog originalBlog = blogRepository.findById(blogId).get();
//        if(optionalBlog.isPresent()) {
//            Blog originalBlog = optionalBlog.get();
            originalBlog.removeComment(commentId);
            this.saveBlog(originalBlog);
//        }
    }

    @Override
    public Blog createVote(Long blogId) {
        Blog optionalBlog = blogRepository.findById(blogId).get();
//        Blog originalBlog = null;
//
//        if (optionalBlog.isPresent()) {
//            originalBlog = optionalBlog.get();

            User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Vote vote = new Vote(user);
            boolean isExist = optionalBlog.addVote(vote);
            if (isExist) {
                throw new IllegalArgumentException("该用户已经点过赞了");
            }
//        }
        return this.saveBlog(optionalBlog);
    }

    @Override
    public void removeVote(Long blogId, Long voteId) {
        Blog optionalBlog = blogRepository.findById(blogId).get();
//        Blog originalBlog = null;

//        if (optionalBlog.isPresent()) {
//            originalBlog = optionalBlog.get();
            optionalBlog.removeVote(voteId);
            this.saveBlog(optionalBlog);
//        }
    }
}
