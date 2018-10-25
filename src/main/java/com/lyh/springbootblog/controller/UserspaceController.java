package com.lyh.springbootblog.controller;

import com.lyh.springbootblog.domain.User;
import com.lyh.springbootblog.service.UserService;
import com.lyh.springbootblog.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: Van
 * Date: 2018/10/4
 * Time: 21:36
 * Description: 用户主页控制器
 */

@Controller
@RequestMapping("/u")
public class UserspaceController {

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("userServiceImp")
    private UserDetailsService userDetailsService;

    @Value("${file.server.url}")
    private String fileServerUrl;

    @GetMapping("/{username}")
    public String userSpace(@PathVariable("username") String username) {
        System.out.println("username" + username);
        return "/userspace/u";
    }

    /**
     * 获取个人设置页面
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)") // 判断username是否为当前授权用户
    public ModelAndView profile(@PathVariable("username") String username, Model model) {
        User user = (User)userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("fileServerUrl",fileServerUrl); //文件服务器地址返回给客户端
        return new ModelAndView("/userspace/profile", "userModel", model);
    }

    /**
     * 保存个人设置
     * @param user
     * @param username
     * @return
     */
    @PostMapping("/{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)")
    public String saveProfile(@PathVariable("username") String username,User user) {
        User originalUser = userService.getUserById(user.getId());
        originalUser.setEmail(user.getEmail());
        originalUser.setName(user.getName());

        // 判断密码是否做了变更
        String rawPassword = originalUser.getPassword();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePasswd = encoder.encode(user.getPassword());
        boolean isMatch = encoder.matches(rawPassword, encodePasswd);
        if (!isMatch) {
            originalUser.setEncodePassword(user.getPassword());
        }
        userService.saveOrUpdateUser(originalUser);
        return "redirect:/u/" + username + "/profile";
    }

    /**
     * 获取编辑头像的界面
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/{username}/avatar")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView avatar(@PathVariable("username") String username, Model model) {
        User  user = (User)userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        return new ModelAndView("/userspace/avatar", "userModel", model);
    }

    /**
     * 保存头像
     * @param username
     * @param user
     * @return
     */
    @PostMapping("/{username}/avatar")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResponseEntity<Response> saveAvatar(@PathVariable("username") String username, @RequestBody User user) {
        String avatarUrl = user.getAvatar();

        User originalUser = userService.getUserById(user.getId());
        originalUser.setAvatar(avatarUrl);
        userService.saveOrUpdateUser(originalUser);

        return ResponseEntity.ok().body(new Response(true, "处理成功", avatarUrl));
    }


    @GetMapping("/{username}/blogs")
    public String listBlogsByOrder(@PathVariable("username") String username,
                                   @RequestParam(value="order",required=false,defaultValue="new") String order,
                                   @RequestParam(value="category",required=false ) Long category,
                                   @RequestParam(value="keyword",required=false ) String keyword) {

        if (category != null) {

            System.out.print("category:" + category );
            System.out.print("selflink:" + "redirect:/u/" + username + "/blogs?category=" + category);
            return "/userspace/u";

        } else if (keyword != null && keyword.isEmpty() == false) {

            System.out.print("keyword:" + keyword );
            System.out.print("selflink:" + "redirect:/u/"+ username + "/blogs?keyword=" + keyword);
            return "/userspace/u";
        }

        System.out.print("order:" + order);
        System.out.print("selflink:" + "redirect:/u/"+ username + "/blogs?order=" + order);
        return "/userspace/u";
    }

    @GetMapping("/{username}/blogs/{id}")
    public String listBlogsByOrder(@PathVariable("id") Long id) {
        System.out.print("blogId:" + id);
        return "/userspace/blog";
    }

    @GetMapping("/{username}/blogs/edit")
    public String editBlog() {
        return "/userspace/blogedit";
    }
}
