package com.bookMall.classic.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class classic {
    Long id;
    String classicName;
    String imgUrl;
    @TableField(fill = FieldFill.INSERT)
    String createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    String updateTime;
}
