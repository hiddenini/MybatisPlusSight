package com.xz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xz.entity.User;
import com.xz.mapper.UserMapper;
import com.xz.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author xz
 * @date 2020/4/27 17:36
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {


}
