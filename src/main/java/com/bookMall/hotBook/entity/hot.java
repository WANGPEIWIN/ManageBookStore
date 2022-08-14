package com.bookMall.hotBook.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@TableName("hot")
public class hot {
    @TableId(type = IdType.AUTO)
    Long id;
    Long bookId;

    @TableField(exist = false)
    String bookName;
    @TableField(exist = false)
    String imgUrl;

    @TableField(fill = FieldFill.INSERT)
    String createTime;
}
