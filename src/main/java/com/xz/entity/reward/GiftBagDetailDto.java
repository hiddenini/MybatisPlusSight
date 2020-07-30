package com.xz.entity.reward;


import java.io.Serializable;
import java.util.List;

public class GiftBagDetailDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 礼包中的券是否发送成功
     */
    private Boolean couponSuccess;

    /**
     * 礼包中的积分是否发送成功
     */
    private Boolean integralSuccess;

    /**
     * 礼包中的对应应该发放的券号
     */
    private List<String> couponCodes;

    /**
     * 礼包中送达的券号
     */
    private List<String> successCoupons;

    /**
     * 礼包中的积分
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

    public List<String> getSuccessCoupons() {
        return successCoupons;
    }

    public void setSuccessCoupons(List<String> successCoupons) {
        this.successCoupons = successCoupons;
    }

    public Long getIntegral() {
        return integral;
    }

    public void setIntegral(Long integral) {
        this.integral = integral;
    }
}
