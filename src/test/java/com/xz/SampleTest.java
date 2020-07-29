package com.xz;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xz.config.MybatisPlusConfig;
import com.xz.entity.*;
import com.xz.mapper.*;
import com.xz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

/**
 * @author xz
 * @date 2020/4/22 15:33
 **/

/**
 * pom文件中spring-boot-starter-test默认继承Junit5（JUnit Platform + JUnit Jupiter + JUnit Vintage），我们需要排除Junit5相关的依赖包
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CommodityMapper commodityMapper;

    @Test
    public void exists() {
        System.out.println(("----- exists method test ------"));
        String createSql = "select count(*) from t_store_0007";
        jdbcTemplate.execute(createSql);
    }

    @Test
    public void createTable() {
        System.out.println(("----- createTable method test ------"));
        String createSql = "create table " + " t_index_0003" + "  like  t_index";
        jdbcTemplate.execute(createSql);
    }


    @Test
    public void selectList() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void selectListDynamicTableUser() {
        MybatisPlusConfig.myTableName.set("t_user_1");
        System.out.println(("----- selectAll selectListDynamicTable test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectListDynamicTableStore() {
        System.out.println(("----- selectAll selectListDynamicTableStore test ------"));
        List<Store> storeList = storeMapper.selectList(null);
        storeList.forEach(System.out::println);
    }


    @Test
    public void selectListDynamicTableStoreParam() {
        MybatisPlusConfig.myTableName.set("t_store_0001");
        System.out.println(("----- selectAll selectListDynamicTableStoreParam test ------"));
        Store store = new Store();
        store.setMerchantId("0002");
        List<Store> storeList = storeMapper.getStoresByMerchantId(store);
        storeList.forEach(System.out::println);
    }

    @Test
    public void selectListDynamicTable1() {
        //MybatisPlusConfig.myTableName.set("t_user");
        System.out.println(("----- selectAll selectListDynamicTable test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }


    @Test
    public void save() {
        System.out.println(("----- save method test ------"));
        User user = new User();
        user.setName("lz");
        user.setAge(25);
        userMapper.insert(user);
        /**
         * 主键id为啥不自增?
         */
        log.info("主键:{}", user.getId());
    }

    @Test
    public void saveGroup() {
        Group group = new Group();
        for (long i = 0; i < 33; i++) {
            group.setCount(i);
            group.setName("分组" + i);
            group.setGroupId(i);
            groupMapper.insert(group);
        }
        /**
         * 主键id为啥不自增?
         */
        log.info("主键:{}", group.getId());
    }

    @Test
    public void saveCoupon() {
        Coupon coupon = new Coupon();
        for (long i = 0; i < 33; i++) {
            coupon.setName("测试券" + i);
            coupon.setMerchantId("1");
            if ((i & 1) == 1) {
                //奇数
                double random1 = Math.random() * 10;
                String douStr = String.format("%.2f", random1);
                coupon.setDiscount(douStr + "折");
            } else {
                //偶数
                coupon.setDiscount("￥" + i);
            }
            couponMapper.insert(coupon);
        }
        log.info("主键:{}", coupon.getId());
    }


    @Test
    public void saveCommodity() {
        Commodity commodity = new Commodity();
        for (long i = 0; i < 33; i++) {
            commodity.setCommodityName("商品" + i);
            commodity.setCommodityId(String.valueOf(i));
            commodity.setGroupId(String.valueOf(1));
            commodityMapper.insert(commodity);
        }
        log.info("主键:{}", commodity.getId());
    }


    @Test
    public void save1000() {
        System.out.println(("----- save save1000 test ------"));
        for (int i = 1; i < 50; i++) {
            User user = new User();
            user.setName("tgy" + i);
            user.setGroupId(2);
            user.setMemberId(i);
            user.setEmail("7810**@qq.com");
            user.setAge(26);
            userMapper.insert(user);
            log.info("主键:{}", user.getId());
        }
    }


    @Test
    public void getById() {
        System.out.println(("----- getById method test ------"));
        User user = userService.getById(1);
        log.info("user:{}", JSON.toJSONString(user));
    }

    @Test
    public void saveOrUpdate() {
        System.out.println(("----- saveOrUpdate method test ------"));
        User user = new User();
        user.setId(1254719839229091845l);
        user.setName("lz1");
        user.setAge(25);
        userService.saveOrUpdate(user);
    }

    @Test
    public void remove() {
        System.out.println(("----- remove method test ------"));
        /**
         * queryWrapper 是条件构造器
         */
        QueryWrapper<User> eq = new QueryWrapper<User>().eq("name", "lz");
        userService.remove(eq);
    }

    @Test
    public void update() {
        System.out.println(("----- update method test ------"));
        /**
         * queryWrapper 是条件构造器
         */
        QueryWrapper<User> eq = new QueryWrapper<User>().eq("name", "zjw");
        User user = new User();
        user.setName("zjw1");
        userService.update(user, eq);
    }

    @Test
    public void get() {
        System.out.println(("----- get method test ------"));
        /**
         * queryWrapper 是条件构造器
         */
        QueryWrapper<User> eq = new QueryWrapper<User>().eq("name", "zjw1");
        User one = userService.getOne(eq);
        log.info("one:{}", JSON.toJSONString(one));
    }

    @Test
    public void list() {
        System.out.println(("----- get method test ------"));
        List<User> list = userService.list();
        log.info("list:{}", JSON.toJSONString(list));
    }

    @Test
    public void IPage() {
        System.out.println(("----- get method test ------"));
        Page<User> page = new Page<User>();
        page.setCurrent(1);
        page.setSize(5);
        Page<User> page1 = userService.page(page, null);
        log.info("list:{}", JSON.toJSONString(page1.getRecords()));

    }

    @Test
    public void count() {
        System.out.println(("----- count method test ------"));
        QueryWrapper<User> eq = new QueryWrapper<User>().eq("name", "zjw1");
        int count = userService.count(eq);
        log.info("count:{}", count);
    }

    @Test
    public void Chain() {
        System.out.println(("----- Chain method test ------"));
        /**
         * ??
         */
    }

    @Test
    public void orderByAsc() {
        System.out.println(("----- orderByAsc method test ------"));
        QueryWrapper<User> eq = new QueryWrapper<User>().orderByAsc("age");
        List<User> list = userService.list(eq);
        log.info("list:{}", JSON.toJSONString(list));
    }

    @Test
    public void or() {
        System.out.println(("----- or method test ------"));
        QueryWrapper<User> eq = new QueryWrapper<User>().eq("name", "zjw1").or().eq("age", 26);
        List<User> list = userService.list(eq);
        log.info("list:{}", JSON.toJSONString(list));
    }

    @Test
    public void selectPage() {
        System.out.println(("----- selectPage method test ------"));
        Page<User> page = new Page();
        page.setCurrent(1);
        page.setSize(2);
        IPage<User> userIPage = userMapper.selectPageVo(page);
        log.info("userIPage:{}", userIPage);
        log.info("userList:{}", JSON.toJSONString(userIPage.getRecords()));
    }

    @Test
    public void lambda() {
        System.out.println(("----- lambda method test ------"));
        LambdaQueryWrapper<User> eq = new QueryWrapper<User>().lambda().eq(User::getName, "zjw1");
        List<User> list = userMapper.selectList(eq);
        log.info("list:{}", JSON.toJSONString(list));
    }

    @Test
    public void testService() {
        System.out.println(("----- testService method test ------"));
        User one = userService.getOne(new QueryWrapper<User>().eq("name", "tgy1"));
        log.info("one:", JSON.toJSONString(one));
    }


    @Test
    public void updateM() {
        System.out.println(("----- updateM method test ------"));
        userMapper.update(11);
    }

}
