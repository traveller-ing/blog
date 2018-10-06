package com.lyh.springbootblog.repository;

import com.lyh.springbootblog.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Van
 * Date: 2018/9/13
 * Time: 下午8:36
 * Description:User Repository 接口
 */
public interface UserRepository extends CrudRepository<User, Long> {

}
