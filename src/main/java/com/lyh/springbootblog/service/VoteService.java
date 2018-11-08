package com.lyh.springbootblog.service;

import com.lyh.springbootblog.domain.Vote;

/**
 * Created with IntelliJ IDEA.
 * Author: Van
 * Date: 2018/11/7
 * Time: 下午9:51
 * Description: Vote 服务接口
 */
public interface VoteService {
    /**
     * 根据id获取 Vote
     * @param id
     * @return
     */
    Vote getVoteById(Long id);
    /**
     * 删除Vote
     * @param id
     * @return
     */
    void removeVote(Long id);
}
