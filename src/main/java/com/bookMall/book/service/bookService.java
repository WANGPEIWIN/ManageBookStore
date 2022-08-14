package com.bookMall.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bookMall.book.entity.book;

import java.util.List;

public interface bookService extends IService<book> {
    List<book> getClassicName(book book);
}
