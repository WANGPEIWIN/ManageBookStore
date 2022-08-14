package com.bookMall.hotBook.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bookMall.hotBook.entity.hot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HotMapper extends BaseMapper<hot> {
    List<hot> getHot(hot hot);
}
