package com.xz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_coupon")
public class Coupon {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private String discount;
    private String merchantId;

}
