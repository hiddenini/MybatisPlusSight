package com.xz.controller;

import com.alibaba.fastjson.JSON;
import com.xz.entity.User;
import com.xz.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GateWayController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/hello/gateWay/first")
    public String getUser() {
        return "hello gateWay first";
    }

    @PostMapping("/user/getOne")
    public User getOne() {
        User user = new User();
        user.setName("zjw");
        return user;
        //return userMapper.selectById(id);
    }

    @PostMapping("/user/getById")
    public User getUserById(@RequestBody User user) {
        log.info("user:{}", JSON.toJSONString(user));
        return userMapper.selectByUserId(user.getId());
    }

    @PostMapping("/user/getByName")
    public User getUserByName(@RequestBody User user) {
        log.info("user:{}", JSON.toJSONString(user));
        return userMapper.selectByUserName(user.getName());
    }

    @PostMapping("/user/getByMemberId")
    public User getByMemberId(@RequestBody User user) {
        log.info("user:{}", JSON.toJSONString(user));
        return userMapper.selectByUserMemberId(user.getMemberId());
    }

    @PostMapping("/user/getByMId")
    public User getByMId(@RequestBody User user) {
        log.info("getByMId:{}", JSON.toJSONString(user));
        return userMapper.selectByUserMemberId(user.getMemberId());
    }
}
