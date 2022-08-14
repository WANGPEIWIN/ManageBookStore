package com;

import javafx.application.Application;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.bookMall.book","com.bookMall.classic", "com.bookMall.user","com.bookMall.hotBook","com.bookMall.swiper","com.bookMall.common"})
@MapperScan("com.bookMall.*.mapper")
@SpringBootApplication
public class manageBook extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(manageBook.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(manageBook.class);
    }
}
