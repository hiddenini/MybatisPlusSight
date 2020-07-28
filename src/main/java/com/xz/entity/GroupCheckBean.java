package com.xz.entity;

import lombok.Data;

import java.io.Serializable;

//调用crm接口的入参bean

@Data
public class GroupCheckBean implements Serializable {
    private static final long serialVersionUID = 1L;

    //原始数据内容
    private String data;

    //会员的id
    private String userId;

    //店铺的id
    private String storeId;

    //商品的id
    private String itemIds;

    //商户id
    private String subMerchantId;

    //会员的分组
    private String userGroup;

    //店铺的分组
    private String itemGroup;

    //商品的分组
    private String storeGroup;

    //活动的code
    private String taskCode;

    //策略类型
    private String strategyType;
}
