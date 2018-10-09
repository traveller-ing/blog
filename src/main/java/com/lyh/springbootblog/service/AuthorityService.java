package com.lyh.springbootblog.service;

import com.lyh.springbootblog.domain.Authority;

/**
 * Created with IntelliJ IDEA.
 * Author: Van
 * Date: 2018/10/9
 * Time: 下午10:37
 * Description: Authority 服务接口
 */
public interface AuthorityService {

    /**
     * 根据id查询Authority
     * @param id
     * @return
     */
    Authority getAuthorityById(Long id);
}
