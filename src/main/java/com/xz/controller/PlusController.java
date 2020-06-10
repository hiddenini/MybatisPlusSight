package com.xz.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xz.entity.Group;
import com.xz.entity.RequestVo;
import com.xz.entity.ResponseVo;
import com.xz.entity.User;
import com.xz.mapper.GroupMapper;
import com.xz.mapper.UserMapper;
import com.xz.service.GroupService;
import com.xz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xz
 * @date 2020/4/22 17:07
 **/
@RestController
public class PlusController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @RequestMapping("/userlist")
    public List<User> getUser() {
        QueryWrapper<User> eq = new QueryWrapper<User>().lt("id", "20");
        return userMapper.selectList(eq);
    }

    @RequestMapping("/getMemberIdsByGroupIdPage")
    public ResponseVo page(@RequestBody RequestVo requestVo) {
        int pageSize = requestVo.getPageSize();
        int pageNum = requestVo.getPageNum();
        Page<User> page = new Page();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("group_id", requestVo.getGroupId());
        IPage<User> userIPage = userService.page(page, queryWrapper);
        List<User> records = userIPage.getRecords();
        List<Long> list = records.stream().map(user -> user.getId()).collect(Collectors.toList());
        ResponseVo responseVo = new ResponseVo();
        responseVo.setPageNum(pageNum);
        responseVo.setPageSize(pageSize);
        responseVo.setIds(list);
        responseVo.setCount(userIPage.getTotal());
        return responseVo;
    }


    @RequestMapping("/getMemberGroupsByMerchantIdPage")
    public ResponseVo pageGroups(@RequestBody RequestVo requestVo) {
        int pageSize = requestVo.getPageSize();
        int pageNum = requestVo.getPageNum();
        String merchantId = requestVo.getMerchantId();
        Page<Group> page = new Page();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        QueryWrapper<Group> queryWrapper = new QueryWrapper<Group>().eq("merchant_id", merchantId);
        IPage<Group> userIPage = groupService.page(page, queryWrapper);
        List<Group> records = userIPage.getRecords();
        ResponseVo responseVo = new ResponseVo();
        responseVo.setPageNum(pageNum);
        responseVo.setPageSize(pageSize);
        responseVo.setGroups(records);
        responseVo.setCount(userIPage.getTotal());
        return responseVo;
    }
}
