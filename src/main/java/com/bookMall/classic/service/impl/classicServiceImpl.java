package com.bookMall.classic.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bookMall.classic.entity.classic;
import com.bookMall.classic.mapper.classicMapper;
import com.bookMall.classic.service.classicService;
import org.springframework.stereotype.Service;

@Service

public class classicServiceImpl extends ServiceImpl<classicMapper,classic> implements classicService {
}
