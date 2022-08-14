package com.bookMall.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookMall.common.entity.responseCode;
import com.bookMall.common.entity.responseJson;
import com.bookMall.common.util.fileUtils;
import com.bookMall.user.entity.user;
import com.bookMall.user.service.userService;
import com.bookMall.common.util.tokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*")
public class userController {
    @Autowired
    userService userService;

    HttpServletResponse response;

    @PostMapping("/user")
    public responseJson addUser(@RequestBody user user){
        try {
            user oldUser = userService.getOne(new QueryWrapper<user>().eq("user_name", user.getUserName()));
            if (oldUser!=null){
                return new responseJson(responseCode.ADD_FAIL);
            }
            userService.save(user);
            return new responseJson(responseCode.SUCCESS,userService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }
    @DeleteMapping("/user/{id}")
    public responseJson deleteUser(@PathVariable("id") long id){
        try {
            user user = userService.getById(id);
            String avatar = user.getAvatar();
            if(avatar!=null){
                boolean imgStatus = fileUtils.removeFile(avatar);
                System.out.println("文件删除状态:"+imgStatus);
            }
            userService.removeById(id);
            return new responseJson(responseCode.SUCCESS,userService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }

    @PutMapping("/user")
    public responseJson updateUser(@RequestBody user user){
        try {
            //过去原来的用户
            user oldUser = userService.getById(user.getId());
            //通过原来用户的图片路径去删除图片
            String avatar = oldUser.getAvatar();
            if(avatar!=null&&!avatar.isEmpty()){
                fileUtils.removeFile(avatar);
            }
            userService.updateById(user);
            return new responseJson(responseCode.SUCCESS,userService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }

    @PatchMapping("/user")
    public responseJson selectAll(@RequestBody user user){
        try {
            QueryWrapper<user> wrapper = new QueryWrapper<>();
            if(!user.getUserName().isEmpty()){
                //前端传用户名和密码过来,将其生成为token.
                wrapper.like("user_name",user.getUserName());
                if(!user.getPwd().isEmpty()){
                    wrapper.eq("pwd",user.getPwd());
                    String token = tokenUtils.sign(user.getId());
                    System.out.println("hhhh");
                }

            }
            if(!user.getRole().isEmpty()){
                wrapper.eq("role",user.getRole());
            }
            return new responseJson(responseCode.SUCCESS,userService.list(wrapper));
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }

    }

    @GetMapping("/user/{id}")
    public responseJson details(@PathVariable long id){
        try {
            return new responseJson(responseCode.SUCCESS,userService.getById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }

    @PostMapping("/login")
    public responseJson login(@RequestBody user user){
        return userService.login(user);
    }


    private responseJson failedResponse(){
        response.setStatus(500);
        return new responseJson(responseCode.FAIL);
    }
}

