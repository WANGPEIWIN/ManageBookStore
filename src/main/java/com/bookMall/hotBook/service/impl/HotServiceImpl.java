package com.bookMall.hotBook.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bookMall.hotBook.entity.hot;
import com.bookMall.hotBook.mapper.HotMapper;
import com.bookMall.hotBook.service.hotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotServiceImpl extends ServiceImpl<HotMapper, hot> implements hotService {
    @Autowired
    HotMapper hotMapper;

    @Override
    public List<hot> getHot(hot hot) {
        return hotMapper.getHot(hot);
    }
}
