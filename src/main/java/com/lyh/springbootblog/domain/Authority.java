package com.lyh.springbootblog.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Van
 * Date: 2018/10/9
 * Time: 下午10:04
 * Description: 权限实体
 */
@Entity
public class Authority implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id;

    @Column(nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.getAuthority();
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
