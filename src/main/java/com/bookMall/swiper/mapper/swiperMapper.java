package com.bookMall.swiper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bookMall.swiper.entity.swiper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RestController;

@Mapper
@RestController
public interface swiperMapper extends BaseMapper<swiper> {
}
