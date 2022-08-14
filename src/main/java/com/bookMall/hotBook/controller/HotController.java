package com.bookMall.hotBook.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookMall.common.entity.responseCode;
import com.bookMall.common.entity.responseJson;
import com.bookMall.hotBook.entity.hot;
import com.bookMall.hotBook.service.hotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*")

public class HotController {
    @Autowired
    hotService hotBookService;

    HttpServletResponse response;
    @PostMapping("/hot")
    public responseJson addHotBook(@RequestBody hot hotBook){
        try {
            hot oldHot = hotBookService.getOne(new QueryWrapper<hot>().eq("book_id",hotBook.getBookId()));
            if(oldHot==null){
                hotBookService.save(hotBook);
                return new responseJson(responseCode.SUCCESS,hotBookService.list());
            }
            return new responseJson(responseCode.ADD_FAIL,hotBookService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }
    @DeleteMapping("/hot/{id}")
    public responseJson deleteHotBook(@PathVariable long id){
        try {
            hotBookService.removeById(id);
            return new responseJson(responseCode.SUCCESS,hotBookService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }

    @PutMapping("/hot")
    public responseJson updateHotBook(@RequestBody hot hotBook){
        try {
            hotBookService.updateById(hotBook);
            return new responseJson(responseCode.SUCCESS,hotBookService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }

    @PatchMapping("/hot")
    public responseJson selectAll(@RequestBody hot hot){
        try {
            return new responseJson(responseCode.SUCCESS,hotBookService.getHot(hot));
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
