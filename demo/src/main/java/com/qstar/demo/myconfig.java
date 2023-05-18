package com.qstar.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.jdbc.core.JdbcTemplate;

// import com.zaxxer.hikari.HikariConfig;
// import com.zaxxer.hikari.HikariDataSource;
// import javax.sql.DataSource;
//@EnableAutoConfiguration
//@Import({User.class,UserDao.class,UserService.class})
@Configuration
public class myconfig {
    // @Bean
    // public DataSource dataSource() {
    //     HikariConfig config = new HikariConfig();
    //     config.setJdbcUrl("jdbc:mysql://43.139.112.204:3306/test");
    //     config.setUsername("root");
    //     config.setPassword("123456789");
    //     config.addDataSourceProperty("cachePrepStmts", "true");
    //     config.addDataSourceProperty("prepStmtCacheSize", "250");
    //     config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    //     return new HikariDataSource(config);
    // }
    // @Bean
    // public JdbcTemplate jdbcTemplate(){
    //     return new JdbcTemplate(dataSource());
    // }
    @Bean
    public UserService userService(){
        return new UserService("user.json");
    }
    @Bean
    public DataIO userio(){
        return new DataIO("user.json");
    }
    @Bean
    public EmailSender emailSender(){
        return new EmailSender();
    }
}
