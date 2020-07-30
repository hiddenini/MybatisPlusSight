package com.xz.entity.reward;

import lombok.Data;

import java.io.Serializable;

@Data
public class RewardRequestBean implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商户号
     */
    private String merchantId;

    /**
     * 活动code
     */
    private String taskCode;

    /**
     * 会员id
     */
    private String userId;

    /**
     * 需要发送的具体奖励
     */
    private RewardBean rewardBean;


}
