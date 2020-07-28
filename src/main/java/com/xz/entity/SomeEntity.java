package com.xz.entity;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Valid
@Data
public class SomeEntity {

    @NotNull(message = "address不能为空")
    private String address;

    @NotNull(message = "name不能为空")
    private String name;



    private String location;

}
