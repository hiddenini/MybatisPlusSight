package com.xz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author xz
 * @date 2020/4/22 15:28
 **/

@SpringBootApplication
@MapperScan("com.xz.mapper*")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Scheduled(fixedDelay=5000)
    public void doSomething() {
        System.out.println("Scheduled=======");
    }
}
