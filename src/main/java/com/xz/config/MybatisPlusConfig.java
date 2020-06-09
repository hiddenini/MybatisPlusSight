package com.xz.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xz
 * @date 2020/4/28 11:46
 **/
@Slf4j
@EnableTransactionManagement
@Configuration
@MapperScan("com.xz.mapper*")
public class MybatisPlusConfig {

    public static ThreadLocal<String> myTableName = new ThreadLocal<>();


/*    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }*/

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        /*动态表名*/
        List<ISqlParser> sqlParserList = new ArrayList<>();
        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
        Map<String, ITableNameHandler> tableNameHandlerMap = new HashMap<>();
        //t_user是数据库表名
        tableNameHandlerMap.put("t_user", new ITableNameHandler() {
            @Override
            public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
                String s = myTableName.get();
                log.info("当前表名是:{}", s);
                return s;//返回null不会替换 注意 多租户过滤会将它一块过滤不会替换@SqlParser(filter=true) 可不会替换
            }
        });

        /**
         * 能拿到sql以及params但是不能保证这个里面会有商户号，必须在某个全局的地方设置下商户号,这里才能拿到,所以暂时只有ThreadLocal的方案
         */
        //t_store是数据库表名
        tableNameHandlerMap.put("t_store", new ITableNameHandler() {
            @Override
            public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
                Object originalObject = metaObject.getOriginalObject();
                JSONObject originalObjectJSON = JSON.parseObject(JSON.toJSONString(originalObject));
                JSONObject boundSql = originalObjectJSON.getJSONObject("boundSql");
                log.info("boundSql:{}", JSON.toJSONString(boundSql));
                JSONObject parameterObject = boundSql.getJSONObject("parameterObject");
                String merchantId = parameterObject.getString("merchantId");
                String s = tableName;
                if (!StringUtils.isEmpty(merchantId)) {
                    s = tableName + "_" + merchantId;
                }
                log.info("parameterObject:{}", JSON.toJSONString(parameterObject));
                //String s = myTableName.get();
                log.info("当前表名是:{}", s);
                return s;//返回null不会替换 注意 多租户过滤会将它一块过滤不会替换@SqlParser(filter=true) 可不会替换
            }
        });
        dynamicTableNameParser.setTableNameHandlerMap(tableNameHandlerMap);
        sqlParserList.add(dynamicTableNameParser);
        paginationInterceptor.setSqlParserList(sqlParserList);

        return paginationInterceptor;
    }

}
