package com.lyh.springbootblog.repository;

import com.lyh.springbootblog.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * Author: Van
 * Date: 2018/11/7
 * Time: 下午9:50
 * Description: Vote Repository接口
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
