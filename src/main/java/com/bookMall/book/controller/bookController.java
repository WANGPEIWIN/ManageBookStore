package com.bookMall.book.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookMall.book.entity.book;
import com.bookMall.book.service.bookService;
import com.bookMall.common.entity.responseCode;
import com.bookMall.common.entity.responseJson;
import com.bookMall.common.util.fileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*")
public class bookController {
    @Autowired
   bookService bookService;

    HttpServletResponse response;

    @PostMapping("/book")
    public responseJson addBook(@RequestBody book book){
        try {
            book bookName = bookService.getOne(new QueryWrapper<book>().eq("book_name", book.getBookName()));
            if(bookName!=null){
                return new responseJson(responseCode.ADD_FAIL);
            }
            bookService.save(book);
            return new responseJson(responseCode.SUCCESS,bookService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }

    @PostMapping("/upload")
    public responseJson upload(MultipartFile file){
        try {
            System.out.println(file);
            String imgPath = fileUtils.saveFile(file);
            if(imgPath==null){
                return new responseJson(responseCode.IMG_SAVE_FAIL);
            }else{
                return new responseJson(responseCode.SUCCESS,imgPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }
    @PostMapping("/deleteFile")
    public responseJson deleteFile(@RequestParam String fileName){
        try {
            fileUtils.removeFile(fileName);
            return new responseJson(responseCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }

    @DeleteMapping("/book/{id}")
    public responseJson deleteBook(@PathVariable long id){
        try {
            //通过id到数据库查询书本对象
            book book = bookService.getById(id);
            //通过书本对象找到图片路径,调用工具类删除
            String imgUrl = book.getImgUrl();
            if(imgUrl!=null){
                boolean imgStatus = fileUtils.removeFile(imgUrl);
                System.out.println("文件删除状态:"+imgStatus);
            }
            bookService.removeById(id);
            return new responseJson(responseCode.SUCCESS,bookService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }

    @PutMapping("/book")
    public responseJson updateBook(@RequestBody book book){
        try {
            book oldBook = bookService.getById(book.getId());
            String imgUrl = oldBook.getImgUrl();
            if(imgUrl!=null&&!imgUrl.isEmpty()){
                fileUtils.removeFile(imgUrl);
            }
            bookService.updateById(book);
            return new responseJson(responseCode.SUCCESS,bookService.list());
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }

    @PatchMapping("/book")
    public responseJson selectAll(@RequestBody book book){
        try {
            return new responseJson(responseCode.SUCCESS,bookService.getClassicName(book));
        } catch (Exception e) {
            e.printStackTrace();
            return failedResponse();
        }
    }

    @GetMapping("/book")
    public responseJson details(@RequestParam Long id){
        try {
            return new responseJson(responseCode.SUCCESS,bookService.getById(id));
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
