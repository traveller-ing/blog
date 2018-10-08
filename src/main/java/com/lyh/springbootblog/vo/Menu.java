package com.lyh.springbootblog.vo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Author: Van
 * Date: 2018/10/8
 * Time: 下午9:59
 * Description:菜单 值对象
 */

public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String url;

    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
