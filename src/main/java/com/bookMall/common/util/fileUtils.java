package com.bookMall.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class fileUtils {
    //存储路径
//    static String imgPath="C:\\Users\\13284\\Desktop\\imgUrl\\";
    static String imgPath="/usr/black/image/";
    //访问路径
//    static String webPath="http://localhost:8080/upload/";
    static String webPath="https://wppjh.top/manageBook/upload/";
    public static String saveFile(MultipartFile file){
        try {
            //获取文件名字以及文件类型,
            String oldName = file.getOriginalFilename();
            //不能读取文字形式的文件名,随机生成英文文件名,截取文件类型
            String fileName= UUID.randomUUID()+oldName.substring(oldName.lastIndexOf("."));
            //用文件路径加文件名创建一个空文件
            File newFile = new File(imgPath + fileName);
            //传过来的数据流转到刚刚创建的空文件里面
            file.transferTo(newFile);
            //将访问路径返回出去
            return webPath+fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean removeFile(String webName){
        System.out.println(webName);
        //截取传过来的值获取图片文件名
        String fileName = webName.substring(webName.lastIndexOf("/") + 1);
        //获取图片文件的绝对路径
        File file = new File(imgPath+fileName);
        //删除图片文件
        return file.delete();
    }
}
