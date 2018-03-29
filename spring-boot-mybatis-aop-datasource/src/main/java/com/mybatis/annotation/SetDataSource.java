package com.mybatis.annotation;

import com.mybatis.druiddatasource.DataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SetDataSource {

    DataSourceType value() default DataSourceType.Master; //默认主表
}
