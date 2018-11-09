package com.lyh.springbootblog.service;

import com.lyh.springbootblog.domain.Catalog;
import com.lyh.springbootblog.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Van
 * Date: 2018/11/8
 * Time: 23:29
 * Description: Catalog 服务接口
 */
public interface CatalogService {
    /**
     * 保存Catalog
     * @param catalog
     * @return
     */
    Catalog saveCatalog(Catalog catalog);

    /**
     * 删除Catalog
     * @param id
     * @return
     */
    void removeCatalog(Long id);

    /**
     * 根据id获取Catalog
     * @param id
     * @return
     */
    Catalog getCatalogById(Long id);

    /**
     * 获取Catalog列表
     * @return
     */
    List<Catalog> listCatalogs(User user);
}