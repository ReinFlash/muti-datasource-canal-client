package com.xyt.canalclient;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.xyt.canalclient.consumer.**.mapper")
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class CanalClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CanalClientApplication.class, args);
    }

}
