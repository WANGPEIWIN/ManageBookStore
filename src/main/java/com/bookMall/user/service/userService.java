package com.bookMall.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bookMall.common.entity.responseJson;
import com.bookMall.user.entity.user;

public interface userService extends IService<user>{
    responseJson login(user user);
}
