package com.mybatis.druiddatasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = "com.mybatis.userb.mapper",sqlSessionTemplateRef = "bSqlSessionTemplate")
public class BDataSourceConfig {

    @Bean(name = "bDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.b")
    public DataSource setDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "bTransactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("bDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "bSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("bDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/b/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "bSqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("bSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
