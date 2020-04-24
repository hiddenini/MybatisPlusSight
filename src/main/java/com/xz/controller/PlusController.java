package com.xz.controller;

import com.xz.entity.User;
import com.xz.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xz
 * @date 2020/4/22 17:07
 **/
@RestController
public class PlusController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/userlist")
    public List<User> getUser(){
        return userMapper.selectList(null);
    }

}
