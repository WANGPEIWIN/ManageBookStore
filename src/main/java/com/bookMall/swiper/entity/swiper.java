package com.bookMall.swiper.entity;

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
public class swiper {
    Long id;
    String title;
    String explains;
    String imgUrl;
    @TableField(fill = FieldFill.INSERT)
    String createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    String updateTime;
}
