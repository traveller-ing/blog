package com.lyh.springbootblog.controller;

import com.lyh.springbootblog.domain.User;
import com.lyh.springbootblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created with IntelliJ IDEA.
 * User: Van
 * Date: 2018/9/12
 * Time: 14:06
 * Description:User 控制器
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    /**
     * 查询所有用户
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView list(Model model) {
        model.addAttribute("userList", userRepository.findAll());
        model.addAttribute("title", "用户管理");
        return new ModelAndView("users/list", "userModel", model);
    }

    /**
     * 根据id 查询用户
     * @param id
     * @param model
     * @return
     */
    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model) {
        User user = userRepository.findById(id).orElse(null); //springboot2.0改版后取消了findOne方法
        model.addAttribute("user", user);
        model.addAttribute("title", "查看用户");
        return new ModelAndView("users/view", "userModel", model);
    }

    /**
     * 获取创建表单页面
     * @param model
     * @return
     */
    @GetMapping("/form")
    public ModelAndView creatForm(Model model) {
        model.addAttribute("user", new User(null, null, null, null));
        model.addAttribute("title", "创建用户");
        return new ModelAndView("users/form", "userModel", model);
    }

    /**
     * 保存或修改用户
     * @param user
     * @return
     */
    @PostMapping
    public ModelAndView saveOrUpdataUser(User user) {
        userRepository.save(user);
        return new ModelAndView("redirect:/users");//重定向到list页面
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
        return new ModelAndView("redirect:/users");
    }

    /**
     * 获取修改用户界面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/modify/{id}")
    public  ModelAndView modify(@PathVariable("id") Long id, Model model) {
        User user = userRepository.findById(id).get();

        model.addAttribute("user",user);
        model.addAttribute("title","修改用户");
        return  new ModelAndView("users/form", "userModel", model);
    }

}
