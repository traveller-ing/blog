package com.lyh.springbootblog.service;

import com.lyh.springbootblog.domain.Vote;
import com.lyh.springbootblog.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Author: Van
 * Date: 2018/11/7
 * Time: 下午9:58
 * Description:
 */
@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public Vote getVoteById(Long id) {
        return voteRepository.findById(id).get();
    }

    @Override
    public void removeVote(Long id) {
        voteRepository.deleteById(id);
    }
}
