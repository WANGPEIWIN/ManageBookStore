package com.bookMall.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bookMall.book.entity.book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface bookMapper extends BaseMapper<book> {
    List<book> getClassicName(book book);
}
