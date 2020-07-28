package com.xz.controller;

import com.alibaba.fastjson.JSON;
import com.xz.entity.SomeEntity;
import com.xz.response.Response;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ParamController {
    @RequestMapping(value = "/param")
    public Response<String> param(@RequestBody @Valid SomeEntity someEntity, BindingResult b) {
        Response<String> response = new Response<>();
        response.setCode(200);
        response.setMessage("success");
        if (b.hasErrors()) {
            response.setCode(201);
            StringBuilder error = new StringBuilder();
            b.getAllErrors().forEach(objectError -> {
                System.out.println(objectError.getDefaultMessage());
                error.append(objectError.getDefaultMessage());
            });
            System.out.println(error.toString());
            response.setMessage(error.toString());
            return response;
        }

        System.out.println("zjw:" + JSON.toJSONString(someEntity));
        return response;
    }

}
