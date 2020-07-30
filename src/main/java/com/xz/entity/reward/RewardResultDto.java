package com.xz.entity.reward;

import java.io.Serializable;

public class RewardResultDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商户号
     */
    private String subMerchantId;

    /**
     * 活动编号
     */
    private String taskCode;

    /**
     * 会员id
     */
    private String userId;

    /**
     * 非礼包的奖励的详细信息
     */
    private RewardDetailDto rewardDetailDto;

    /**
     * 礼包的详细信息
     */
    private GiftBagDetailDto giftBagDetailDto;

    public String getSubMerchantId() {
        return subMerchantId;
    }

    public void setSubMerchantId(String subMerchantId) {
        this.subMerchantId = subMerchantId;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public RewardDetailDto getRewardDetailDto() {
        return rewardDetailDto;
    }

    public void setRewardDetailDto(RewardDetailDto rewardDetailDto) {
        this.rewardDetailDto = rewardDetailDto;
    }

    public GiftBagDetailDto getGiftBagDetailDto() {
        return giftBagDetailDto;
    }

    public void setGiftBagDetailDto(GiftBagDetailDto giftBagDetailDto) {
        this.giftBagDetailDto = giftBagDetailDto;
    }
}
