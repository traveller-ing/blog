package com.lyh.springbootblog.service;

import com.lyh.springbootblog.domain.Authority;
import com.lyh.springbootblog.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Author: Van
 * Date: 2018/10/9
 * Time: 下午10:39
 * Description: Authority 服务接口实现
 */

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority getAuthorityById(Long id) {
        return authorityRepository.findById(id).get();
    }
}
