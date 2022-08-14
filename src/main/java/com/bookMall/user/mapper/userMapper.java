package com.bookMall.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bookMall.user.entity.user;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface userMapper extends BaseMapper<user>{

}
