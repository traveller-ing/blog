package com.lyh.springbootblog.repository;

import com.lyh.springbootblog.domain.Catalog;
import com.lyh.springbootblog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Van
 * Date: 2018/11/8
 * Time: 21:42
 * Description: Catalog Repository
 */

public interface CatalogRepository extends JpaRepository<Catalog, Long> {

    /**
     * 根据用户查询
     * @param user
     * @return
     */
    List<Catalog> findByUser(User user);

    /**
     * 根据用户、分类名称查询
     * @param user
     * @param name
     * @return
     */
    List<Catalog> findByUserAndName(User user,String name);
}
