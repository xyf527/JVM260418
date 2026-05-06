package com.xin.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xyf527
 * @version 1.0
 * @description
 * @date 2026-05-05 17:12
 * @github https://github.com/xyf527
 * @copyright
 */

@SpringBootApplication
@MapperScan("com.xin.demo.mapper")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
