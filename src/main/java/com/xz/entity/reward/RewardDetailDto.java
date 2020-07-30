package com.xz.entity.reward;


import java.io.Serializable;
import java.util.List;

public class RewardDetailDto implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 非礼包中的券是否发送成功
     */
    private Boolean couponSuccess;

    /**
     * 非礼包中的积分是否发送成功
     */
    private Boolean integralSuccess;


    /**
     * 非礼包中应该发送的券
     */
    private List<String> couponCodes;

    /**
     * 非礼包中送达的券
     */
    private List<String> successCouponCodes;


    /**
     * 非礼包中的积分(如果是倍数积分则需要算好了给我)
     */
    private Long integral;


    public Boolean getCouponSuccess() {
        return couponSuccess;
    }

    public void setCouponSuccess(Boolean couponSuccess) {
        this.couponSuccess = couponSuccess;
    }

    public Boolean getIntegralSuccess() {
        return integralSuccess;
    }

    public void setIntegralSuccess(Boolean integralSuccess) {
        this.integralSuccess = integralSuccess;
    }


    public List<String> getCouponCodes() {
        return couponCodes;
    }

    public void setCouponCodes(List<String> couponCodes) {
        this.couponCodes = couponCodes;
    }

    public List<String> getSuccessCouponCodes() {
        return successCouponCodes;
    }

    public void setSuccessCouponCodes(List<String> successCouponCodes) {
        this.successCouponCodes = successCouponCodes;
    }

    public Long getIntegral() {
        return integral;
    }

    public void setIntegral(Long integral) {
        this.integral = integral;
    }
}
