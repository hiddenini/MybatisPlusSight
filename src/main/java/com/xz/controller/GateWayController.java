package com.xz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GateWayController {

    @RequestMapping("/hello/gateWay/first")
    public String getUser() {
        return "hello gateWay first";
    }
}
