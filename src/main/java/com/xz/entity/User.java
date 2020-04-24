package com.xz.entity;

import lombok.Data;

/**
 * @author xz
 * @date 2020/4/22 15:31
 **/

@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
