package com.bookMall.common.entity;

import lombok.ToString;


public class responseJson {
    public int code;
    public String message;
    public Object data;
//    成功
    public responseJson(responseCode code,Object data){
        this.code=code.code;
        this.message=code.message;
        this.data=data;
    }
//    失败
    public responseJson(responseCode code){
        this.code=code.code;
        this.message=code.message;
        this.data=null;
    }

    @Override
    public String toString() {
        return "{" +
                "\"code\"=\"" + code +
                "\", \"message\"=\"" + message +
                "\"}";
    }
}
