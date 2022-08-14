package com.bookMall.book.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class book {
    Long id;
    String bookName;
    String imgUrl;
    Long price;
    Long discount;
    String author;

    Long categoryId;
    @TableField(exist = false)
    String classicName;

    String press;

    @TableField(fill = FieldFill.INSERT)
    String createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    String  updateTime;
}
