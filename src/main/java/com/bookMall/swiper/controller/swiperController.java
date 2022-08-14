package com.bookMall.swiper.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookMall.common.entity.responseCode;
import com.bookMall.common.entity.responseJson;
import com.bookMall.common.util.fileUtils;
import com.bookMall.swiper.entity.swiper;
import com.bookMall.swiper.service.swiperService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*")
public class swiperController {
    @Autowired
    swiperService swiperService;

    HttpServletResponse response;

    @PostMapping("/swiper")
    public responseJson addSwiper(@RequestBody swiper swiper){
        try {
            swiper title = swiperService.getOne(new QueryWrapper<swiper>().eq("title", swiper.getTitle()));
            if(title!=null){
                return new responseJson(responseCode.ADD_FAIL);
            }
            swiperService.save(swiper);
            return new responseJson(responseCode.SUCCESS,swiperService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }
    @DeleteMapping("/swiper/{id}")
    public responseJson deleteSwiper(@PathVariable long id){
        try {
            swiper swiper = swiperService.getById(id);
            String imgUrl = swiper.getImgUrl();
            if(imgUrl!=null){
                boolean imgStatus = fileUtils.removeFile(imgUrl);
                System.out.println("文件删除状态:"+imgStatus);
            }
            swiperService.removeById(id);
            return new responseJson(responseCode.SUCCESS, swiperService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }

    @PutMapping("/swiper")
    public responseJson updateSwiper(@RequestBody swiper swiper){
        try {
            swiper oldSwiper = swiperService.getById(swiper.getId());
            String imgUrl = oldSwiper.getImgUrl();
            if(imgUrl!=null&&!imgUrl.isEmpty()){
                fileUtils.removeFile(imgUrl);
            }
            swiperService.updateById(swiper);
            return new responseJson(responseCode.SUCCESS,swiperService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }

    @PatchMapping("/swiper")
    public responseJson selectAll(@RequestBody swiper swiper){
        try {
            QueryWrapper<swiper> wrapper = new QueryWrapper<>();
//            if(!swiper.getTitle().isEmpty()){
//                wrapper.like("title",swiper.getTitle());
//            }
            return new responseJson(responseCode.SUCCESS,swiperService.list(wrapper));
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }

    }

    @GetMapping("/swiper/{id}")
    public responseJson details(@PathVariable long id){
        try {
            return new responseJson(responseCode.SUCCESS,swiperService.getById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }


    private responseJson failedResponse(){
        response.setStatus(500);
        return new responseJson(responseCode.FAIL);
    }
}
