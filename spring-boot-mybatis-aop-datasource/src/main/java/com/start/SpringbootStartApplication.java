package com.start;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//如果禁止了DataSourceAutoConfiguration这个，就不需要在DataSourceConfig中指定主数据源了。@Primary
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        MybatisAutoConfiguration.class,
        //HibernateJpaAutoConfiguration.class, //（如果使用Hibernate时，需要加）
        DataSourceTransactionManagerAutoConfiguration.class
})

//@SpringBootApplication
@EnableTransactionManagement
//@EnableCaching 注解缓存
//@MapperScan(basePackages = "com.mybatis") //扫描mapper的目录
@ComponentScan(basePackages = "com.mybatis")
public class SpringbootStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootStartApplication.class,args);
    }
}
