package com.xz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_store")
public class Store {
    private String storeName;
    private String storeAddress;
    private String merchantId;
}
