package com.bookMall.user.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Value;
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class user {
     Long id;
     String userName;
     String avatar;
     String pwd;
//     @Builder.Default
     String profile;
     String role;

     String phone;

     @TableField(fill = FieldFill.INSERT)
     String createTime;

     @TableField(fill = FieldFill.INSERT_UPDATE)
     String updateTime;
}
