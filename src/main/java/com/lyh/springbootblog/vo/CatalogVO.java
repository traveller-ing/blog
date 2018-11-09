package com.lyh.springbootblog.vo;

import com.lyh.springbootblog.domain.Catalog;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Van
 * Date: 2018/11/8
 * Time: 21:39
 * Description: Catalog VO.
 */
public class CatalogVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private Catalog catalog;

    public CatalogVO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

}
