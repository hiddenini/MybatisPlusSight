package com.xz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_commodity")
public class Commodity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String commodityName;
    private String commodityId;
    private String groupId;
}
