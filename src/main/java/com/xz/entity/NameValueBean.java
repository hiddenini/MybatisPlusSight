package com.xz.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NameValueBean implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Long value;

    private Long count;


}
