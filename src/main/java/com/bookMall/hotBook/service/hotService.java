package com.bookMall.hotBook.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bookMall.hotBook.entity.hot;

import java.util.List;

public interface hotService extends IService<hot> {
    List<hot> getHot(hot hot);
}
