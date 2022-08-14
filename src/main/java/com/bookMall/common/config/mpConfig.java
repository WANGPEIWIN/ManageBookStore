package com.bookMall.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class mpConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        setFieldValByName("createTime",new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss").format(new Date()),metaObject);
        setFieldValByName("updateTime",new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss").format(new Date()),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime",new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss").format(new Date()),metaObject);
    }
}
