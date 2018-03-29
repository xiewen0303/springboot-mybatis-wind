package com.mybatis.druiddatasource;

import org.apache.log4j.Logger;

public class DataSourceContextHolder {
    /**
     *  日志工具类
     */
    public static final Logger log = Logger.getLogger(DataSourceContextHolder.class);

    /**
     *  默认数据源
     */
    public static final String DEFAULT_DS = "defult_ds";

    /***
     * 每个线程都拷贝一个新的对象，
     *
     * 需要指出的是，当线程结束后，对应该线程的局部变量将自动被垃圾回收。
     *
     * ThreadLocal的作用是提供线程内的局部变量，这种变量在线程的生命周期内起作用。
     * 作用：提供一个线程内公共变量（比如本次请求的用户信息），
     * 减少同一个线程内多个函数或者组件之间一些公共变量的传递的复杂度，或者为线程提供一个私有的变量副本，
     * 这样每一个线程都可以随意修改自己的变量副本，而不会对其他线程产生影响。
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源
     * @param dbType
     */
    public static void setDBSource(String dbType){
        log.debug("切换到{}数据源");
        contextHolder.set(dbType);
    }

    /**
     * 获取数据源
     */
    public static String getDbDBSource(){
        return contextHolder.get();
    }

    /**
     * 清除数据源
     */
    public static void cleanDBSource(){
        contextHolder.remove();
    }
}