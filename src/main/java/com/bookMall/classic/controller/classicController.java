package com.bookMall.classic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookMall.classic.service.classicService;
import com.bookMall.common.entity.responseCode;
import com.bookMall.common.entity.responseJson;
import com.bookMall.common.util.fileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bookMall.classic.entity.classic;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*")
public class classicController {
    @Autowired
    classicService classicService;

    HttpServletResponse response;

    @PostMapping("/classic")
    public responseJson addClassic(@RequestBody classic classic){
        try {
            classic name = classicService.getOne(new QueryWrapper<classic>().eq("classic_name", classic.getClassicName()));
            if(name !=null){
                return new responseJson(responseCode.ADD_FAIL);
            }
            classicService.save(classic);
            return new responseJson(responseCode.SUCCESS,classicService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }

    @DeleteMapping("/classic/{id}")
    public responseJson deleteClassic(@PathVariable long id){
        try {
            classic classic = classicService.getById(id);
            String imgUrl = classic.getImgUrl();
            if(imgUrl!=null){
                boolean imgStatus = fileUtils.removeFile(imgUrl);
                System.out.println("文件删除状态:"+imgStatus);
            }
            classicService.removeById(id);
            return new responseJson(responseCode.SUCCESS,classicService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }

    @PutMapping("/classic")
    public responseJson updateClassic(@RequestBody classic classic){
        try {
            classic oldClassic = classicService.getById(classic.getId());
            String imgUrl = oldClassic.getImgUrl();
            if(imgUrl!=null&&!imgUrl.isEmpty()){
                fileUtils.removeFile(imgUrl);
            }
            classicService.updateById(classic);
            return new responseJson(responseCode.SUCCESS,classicService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }

    @PatchMapping("/classic")
    public responseJson selectAll(@RequestBody classic classic){

        try {
            QueryWrapper<classic> wrapper = new QueryWrapper<>();
            if(classic.getClassicName()!=null&&!classic.getClassicName().isEmpty()){
                wrapper.like("classic_name",classic.getClassicName());
            }
            return new responseJson(responseCode.SUCCESS,classicService.list(wrapper));
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }

    @GetMapping("/classic/{id}")
    public responseJson details(@PathVariable long id){
        return new responseJson(responseCode.SUCCESS,classicService.getById(id));
    }

    private responseJson failedResponse(){
        response.setStatus(500);
        return new responseJson(responseCode.FAIL);
    }
}
