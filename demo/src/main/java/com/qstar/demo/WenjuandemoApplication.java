package com.qstar.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class WenjuandemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(WenjuandemoApplication.class, args);
    }

}
