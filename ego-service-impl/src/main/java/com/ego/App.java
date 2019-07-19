package com.ego;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;


@EnableDubbo
@EnableTransactionManagement
@SpringBootApplication
@MapperScan(basePackages="com.ego.mapper")
public class App {
public static void main(String[] args) {
	SpringApplication.run(App.class, args);
}

}
