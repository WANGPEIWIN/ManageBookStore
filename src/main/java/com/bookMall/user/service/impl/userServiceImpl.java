package com.bookMall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bookMall.common.entity.responseCode;
import com.bookMall.common.entity.responseJson;
import com.bookMall.user.entity.user;
import com.bookMall.user.mapper.userMapper;
import com.bookMall.user.service.userService;
import com.bookMall.common.util.tokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImpl extends ServiceImpl<userMapper, user> implements userService {
    @Autowired
    userMapper userMapper;

    @Override
    public responseJson login(user user) {
        QueryWrapper<user> wrapper = new QueryWrapper<>();
        //用户名和密码是否为空
        if (!user.getUserName().isEmpty() && !user.getPwd().isEmpty()) {
            //通过userName筛选用户信息
            wrapper.eq("user_name", user.getUserName());
            //查询数据库里面的内容
            List<user> users = userMapper.selectList(wrapper);
            //判断用户是否存在
            if (users.size() == 1) {
                //取出数据库里面的密码与传进来的密码比较
                if (users.get(0).getPwd().equals(user.getPwd())) {
                    //密码正确调用token工具类传入用户id生成token;
                    return new responseJson(responseCode.SUCCESS,tokenUtils.sign(users.get(0).getId()));
                } else {
                    return new responseJson(responseCode.ERROR_PWD);
                }
            } else {
                return new responseJson(responseCode.NO_USER);
            }
        } else {
            return new responseJson(responseCode.NULL_USER);
        }
    }
}
