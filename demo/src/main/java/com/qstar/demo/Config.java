package com.qstar.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class Config {//配置类,把properties文件导入，到时候一些数据在properties文件内定义即可
}
