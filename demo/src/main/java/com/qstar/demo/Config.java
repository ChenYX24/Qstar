package com.qstar.demo;

import com.qstar.demo.filter.TokenInterceptors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:application.properties")
public class Config implements WebMvcConfigurer {//配置类,把properties文件导入，到时候一些数据在properties文件内定义即可

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptors()).addPathPatterns("/**").excludePathPatterns("/login",
                "/register","/sendcode","/checkcode","changepassword","/autologin","/getgrcode","test");    //拦截除了登录之外的访问路径
    }

    @Bean
    public UserService userService(){
        return new UserService();
    }
    @Bean
    public DataIO userio(){
        return new DataIO("id.json");
    }
    @Bean
    public EmailSender emailSender(){
        return new EmailSender();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
