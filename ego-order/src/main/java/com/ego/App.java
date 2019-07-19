package com.ego;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;



@EnableDubbo
@SpringBootApplication
public class App {
public static void main(String[] args) {
	SpringApplication.run(App.class, args);
}
}
