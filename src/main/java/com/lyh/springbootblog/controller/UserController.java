package com.lyh.springbootblog.controller;

import com.lyh.springbootblog.domain.User;
import com.lyh.springbootblog.service.UserService;

import com.lyh.springbootblog.util.ConstraintViolationExceptionHandler;
import com.lyh.springbootblog.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: Van
 * Date: 2018/9/12
 * Time: 14:06
 * Description:User 控制器
 */
@RestController
@RequestMapping("/users")
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")  // 指定角色权限才能操作方法
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     * @param async
     * @param pageIndex
     * @param pageSize
     * @param name
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView list(@RequestParam(value="async",required=false) boolean async,
                             @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
                             @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
                             @RequestParam(value="name",required=false,defaultValue="") String name,
                             Model model) {

        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Page<User> page = userService.listUsersByNameLike(name, pageable);
        List<User> list = page.getContent();	// 当前所在页面数据列表

        model.addAttribute("page", page);
        model.addAttribute("userList", list);
        return new ModelAndView(async==true?"users/list :: #mainContainerRepleace":"users/list", "userModel", model);
    }


    /**
     * 获取创建表单页面
     * @param model
     * @return
     */
    @GetMapping("/add")
    public ModelAndView creatForm(Model model) {
        model.addAttribute("user", new User(null, null, null, null));
        model.addAttribute("title", "创建用户");
        return new ModelAndView("users/add", "userModel", model);
    }

    /**
     * 保存或修改用户
     * @param user
     * @return
     */
    @PostMapping
//    public ModelAndView saveOrUpdateUser(User user) {
//        userService.saveOrUpdateUser(user);
//        return new ModelAndView("redirect:users/list");//重定向到list页面
//    }
    public ResponseEntity<Response> saveOrUpdateUser(User user) {

//        List<Authority> authorities=new ArrayList<Authority>();
//        authorities.add(authorityService.getAuthorityById(authorityId));
//        user.setAuthorities(authorities);

        try {
            userService.saveOrUpdateUser(user);
        }
        catch(ConstraintViolationException e) {
            return ResponseEntity.ok().body(new Response(false	, ConstraintViolationExceptionHandler.getMessage(e)));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功",user));
    }


    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id) {
        userService.removeUser(id);
//        return new ModelAndView("redirect:/users");
        return new Response(true, "删除成功");
    }

    /**
     * 获取修改用户界面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public  ModelAndView modify(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);

        model.addAttribute("user",user);
        model.addAttribute("title","修改用户");
        return  new ModelAndView("users/edit", "userModel", model);
    }

}
