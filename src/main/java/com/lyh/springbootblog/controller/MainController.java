package com.lyh.springbootblog.controller;

import com.lyh.springbootblog.domain.Authority;
import com.lyh.springbootblog.domain.User;
import com.lyh.springbootblog.service.AuthorityService;
import com.lyh.springbootblog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Van
 * Date: 2018/10/4
 * Time: 21:36
 * Description: 主页控制器
 */

@Controller
public class MainController {

    private static final Long ROLE_USER_AUTHORITY_ID = 2L;

    @Autowired
    UserService userService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 根跳转
     * @return
     */
    @GetMapping("/")
    public String root(){
        return "redirect:/index";
    }

    /**
     * 主页跳转
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 登录跳转
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 登录失败跳转
     * @param model
     * @return
     */
    @GetMapping("/login-error")
    public ModelAndView loginError(Model model){
        model.addAttribute("loginError", true);
        model.addAttribute("errorMsg", "登录失败,用户名或者密码错误");
        return new ModelAndView("login","userModel",model);
    }

    /**
     * 注册页面
     * @return
     */
    @GetMapping("/register")
    public String register() {
        return  "register";
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @PostMapping("/register")
    public String registerUser(User user) {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID));
        user.setAuthorities(authorities);

        userService.saveOrUpdateUser(new User(user.getId(), user.getName(), user.getUsername(), user.getEmail(),
                passwordEncoder.encode(user.getPassword())));
        return "redirect:/login";
    }
}
