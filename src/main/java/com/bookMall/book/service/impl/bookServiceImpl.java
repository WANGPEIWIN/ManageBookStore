package com.bookMall.book.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bookMall.book.entity.book;
import com.bookMall.book.mapper.bookMapper;
import com.bookMall.book.service.bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class bookServiceImpl extends ServiceImpl<bookMapper, book> implements bookService {
    @Autowired
    bookMapper bookMapper;

    @Override
    public List<book> getClassicName(book book) {
        return bookMapper.getClassicName(book);
    }
}
