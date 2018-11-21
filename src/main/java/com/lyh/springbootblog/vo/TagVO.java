package com.lyh.springbootblog.vo;

/**
 * Created with IntelliJ IDEA.
 * User: Van
 * Date: 2018/11/22
 * Time: 0:24
 * Description: Tag 值对象
 */
public class TagVO {

    private String name;
    private Long count;

    public TagVO(String name, Long count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}
