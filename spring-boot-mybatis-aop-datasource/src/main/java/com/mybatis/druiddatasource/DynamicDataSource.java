package com.mybatis.druiddatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dbSource = DataSourceContextHolder.getDbDBSource();
        logger.debug("数据源为:"+dbSource);
        return dbSource;
    }
}
