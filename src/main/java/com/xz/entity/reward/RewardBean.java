package com.xz.entity.reward;

import lombok.Data;

import java.io.Serializable;

@Data
public class RewardBean implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 券定义id
     */
    private String couponIds;

    /**
     * 积分倍数
     */
    private String multiple;

    /**
     * 触发奖励的单号
     */
    private String rewardNo;

    /**
     * 奖励的普通积分数
     */
    private String score;

    /**
     * 积分类型
     */
    private String scoreType;

    /**
     * 礼包编号
     */
    private String giftBag;

}
