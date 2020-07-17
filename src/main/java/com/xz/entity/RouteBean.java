package com.xz.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

//第二层mq的bean

@Data
@ToString
public class RouteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// 原始数据内容
	private String data;

	// 策略类型
	private String strategyType;

	// 任务code
	private String taskCode;

	// 是否通过校验
	private Boolean pass;

	private String merchantId;
}
