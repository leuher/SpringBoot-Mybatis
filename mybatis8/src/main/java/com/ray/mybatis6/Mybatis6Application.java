package com.ray.mybatis6;

import com.ray.mybatis6.util.MyMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// 开启注解事务管理
@EnableTransactionManagement  // 等同于xml配置方式的 <tx:annotation-driven />
@MapperScan(basePackages = "com.ray.mybatis6.dao", markerInterface = MyMapper.class)
public class Mybatis6Application {

    public static void main(String[] args) {
        SpringApplication.run(Mybatis6Application.class, args);
    }
}
