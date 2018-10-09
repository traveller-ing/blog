package com.lyh.springbootblog.repository;

import com.lyh.springbootblog.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * Author: Van
 * Date: 2018/10/9
 * Time: 下午10:34
 * Description: Authority Repository 接口
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
