package com.xz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xz
 * @date 2020/4/22 15:31
 **/
@Data
@TableName("t_user")
public class User implements Serializable {
    @TableId(value ="user_id" ,type = IdType.AUTO)
    private Long userId;
    private String name;
    private Integer age;
    private String email;

}
