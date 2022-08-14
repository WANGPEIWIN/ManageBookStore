package com.bookMall.common.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum responseCode {
    SUCCESS(200,"操作成功"),
    FAIL(500,"操作失败"),
    NO_TOKEN(401,"请先登录"),
    TOKEN_EXPIRED(402,"token过期,请重新登录"),
    ADD_FAIL(501,"添加失败!参数重复!"),
    NULL_USER (502,"用户名密码存在空值"),
    NO_USER (503,"用户不存在"),
    ERROR_PWD(504,"密码错误"),
    IMG_SAVE_FAIL(505,"图片存储失败");
    public final int code;
    public final String message;
}
