package com.liu.weibocomment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.liu.weibocomment.mapper")
@EnableScheduling //定时启动
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
