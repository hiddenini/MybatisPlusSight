package com.xz;

import com.xz.entity.User;
import com.xz.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author xz
 * @date 2020/4/22 15:33
 **/

/**
 *  pom文件中spring-boot-starter-test默认继承Junit5（JUnit Platform + JUnit Jupiter + JUnit Vintage），我们需要排除Junit5相关的依赖包
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectList() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}
