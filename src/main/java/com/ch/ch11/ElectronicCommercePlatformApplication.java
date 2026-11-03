package com.ch.ch11;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ch.ch11.repository")
public class ElectronicCommercePlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElectronicCommercePlatformApplication.class, args);
    }
}
