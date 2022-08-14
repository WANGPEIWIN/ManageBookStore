package com.bookMall.classic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bookMall.classic.entity.classic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface classicMapper extends BaseMapper<classic> {
}
