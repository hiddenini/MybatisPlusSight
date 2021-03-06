package com.xz.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xz.entity.*;
import com.xz.entity.reward.GiftBagDetailDto;
import com.xz.entity.reward.RewardDetailDto;
import com.xz.entity.reward.RewardRequestBean;
import com.xz.entity.reward.RewardResultDto;
import com.xz.mapper.GroupMapper;
import com.xz.mapper.UserMapper;
import com.xz.service.CommodityService;
import com.xz.service.CouponService;
import com.xz.service.GroupService;
import com.xz.service.UserService;
import com.xz.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xz
 * @date 2020/4/22 17:07
 **/
@Slf4j
@RestController
public class PlusController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;


    @Autowired
    private CouponService couponService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private CommodityService commodityService;

    @RequestMapping("/userlist")
    public List<User> getUser() {
        QueryWrapper<User> eq = new QueryWrapper<User>().lt("id", "20");
        return userMapper.selectList(eq);
    }

    @RequestMapping("/helloParam")
    public String helloParam(@RequestBody User user) {
        return user.getName() + "hello world";
    }

/*    @RequestMapping("/getMemberIdsByGroupIdPage")
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
    }*/

    @RequestMapping(value = "/getMemberIdsByGroupIdPage", method = RequestMethod.GET)
    public ResponseVo getMemberIdsByGroupIdPageGet(@RequestParam Integer pageNo,
                                                   @RequestParam Integer pageSize, @RequestParam int groupId) {
        Page<User> page = new Page();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("group_id", groupId);
        IPage<User> userIPage = userService.page(page, queryWrapper);
        List<User> records = userIPage.getRecords();
        List<Long> list = records.stream().map(user -> user.getId()).collect(Collectors.toList());
        ResponseVo responseVo = new ResponseVo();
        responseVo.setPageNum(pageNo);
        responseVo.setPageSize(pageSize);
        responseVo.setIds(list);
        responseVo.setCount(userIPage.getTotal());
        return responseVo;

    }


    @RequestMapping(value = "/geCouponPage", method = RequestMethod.GET)
    public CouponResponseVo geCouponPage(@RequestParam Integer pageNo,
                                         @RequestParam Integer pageSize) {
        Page<Coupon> page = new Page();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        String merchant_id = "1";
        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<Coupon>().eq("merchant_id", merchant_id);
        IPage<Coupon> couponIPage = couponService.page(page, queryWrapper);
        List<Coupon> records = couponIPage.getRecords();
        CouponResponseVo responseVo = new CouponResponseVo();
        responseVo.setPageNum(pageNo);
        responseVo.setPageSize(pageSize);
        responseVo.setCount(couponIPage.getTotal());
        responseVo.setList(records);
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

    @RequestMapping("/getMemberGroups")
    public List<NameValueBean> getMemberGroups() {
        List<Group> groupList = groupService.list();
        List<NameValueBean> list = new ArrayList<>();
        groupList.stream().forEach(record -> {
            NameValueBean nameValueBean = new NameValueBean();
            nameValueBean.setName(record.getName());
            nameValueBean.setValue(record.getGroupId());
            nameValueBean.setCount(record.getCount());
            list.add(nameValueBean);
        });
        return list;
    }


    @RequestMapping("/pageGroups")
    public ResponseVo pageGroups(@RequestParam Integer pageNo,
                                 @RequestParam Integer pageSize, @RequestParam int merchantId, @RequestParam String groupType) {
        Page<Group> page = new Page();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        QueryWrapper<Group> queryWrapper = new QueryWrapper<Group>().eq("merchant_id", merchantId).eq("group_type", groupType);
        IPage<Group> userIPage = groupService.page(page, queryWrapper);
        List<Group> records = userIPage.getRecords();
        ResponseVo responseVo = new ResponseVo();
        responseVo.setPageNum(pageNo);
        responseVo.setPageSize(pageSize);
        responseVo.setGroups(records);
        responseVo.setCount(userIPage.getTotal());
        return responseVo;
    }


    @RequestMapping("/getCommodityGroupsByMerchantId")
    public List<NameValueBean> getCommodityGroupsByMerchantId(@RequestParam Integer pageNo,
                                                              @RequestParam Integer pageSize, @RequestParam int groupId) {
        Page<Commodity> page = new Page();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        QueryWrapper<Commodity> queryWrapper = new QueryWrapper<Commodity>().eq("group_id", groupId);
        IPage<Commodity> userIPage = commodityService.page(page, queryWrapper);
        List<Commodity> records = userIPage.getRecords();
        List<NameValueBean> list = new ArrayList<>();
        records.stream().forEach(record -> {
            NameValueBean nameValueBean = new NameValueBean();
            nameValueBean.setValue(Long.valueOf(record.getCommodityId()));
            list.add(nameValueBean);
        });
        return list;
    }

    @RequestMapping("/groupCheck")
    public String groupCheck(@RequestBody GroupCheckBean groupCheckBean) {
        RouteBean routeBean = new RouteBean();
        BeanUtils.copyProperties(groupCheckBean, routeBean);
        routeBean.setPass(true);
        System.out.println("routeBean:" + JSON.toJSONString(routeBean));
        //回调
        // 请求头
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        headers.put("accept", "application/json; charset=utf-8");
        String result = HttpClientUtil.dopost("http://localhost:3003/service-marketing.api/push/route", JSON.toJSONString(routeBean), headers);
        return "abc";
    }

    @RequestMapping("/sendReward")
    public void sendReward(@RequestBody RewardRequestBean rewardRequestBean) {
        log.info("enter sendReward params:{}", JSON.toJSONString(rewardRequestBean));
        RewardResultDto rewardResultDto = new RewardResultDto();
        BeanUtils.copyProperties(rewardRequestBean, rewardResultDto);
        RewardDetailDto rewardDetailDto = new RewardDetailDto();
        GiftBagDetailDto giftBagDetailDto = new GiftBagDetailDto();
        rewardDetailDto.setIntegralSuccess(true);
        rewardDetailDto.setIntegral(100l);
        giftBagDetailDto.setIntegralSuccess(true);
        giftBagDetailDto.setIntegral(200l);
        rewardResultDto.setRewardDetailDto(rewardDetailDto);
        rewardResultDto.setGiftBagDetailDto(giftBagDetailDto);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        headers.put("accept", "application/json; charset=utf-8");
        String result = HttpClientUtil.dopost("http://localhost:3003/service-marketing.api/callback/reward", JSON.toJSONString(rewardResultDto), headers);

    }


}
