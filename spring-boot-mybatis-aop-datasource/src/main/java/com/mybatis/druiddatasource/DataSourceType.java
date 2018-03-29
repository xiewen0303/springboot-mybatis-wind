package com.mybatis.druiddatasource;

/**
 * 数据源类型
 */
public enum DataSourceType {

    Master("master"),
    Slave("slave");

    private String name;

    private DataSourceType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
