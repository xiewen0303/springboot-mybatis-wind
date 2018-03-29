package com.mybatis.annotation;


import com.mybatis.annotation.SetDataSource;
import com.mybatis.druiddatasource.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
//@Order(-99) // 控制多个Aspect的执行顺序，越小越先执行
@Component
public class DataSourceAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *TODO 指定需要监控的service
     */
    @Pointcut("execution(* com.mybatis.usera.services..*.*(..))")
    public void aspect(){
        //TODO  可以自己动态的分配那个service属于那个database
    }

    @Before("aspect()")
    private void before(JoinPoint point){
        Object target = point.getTarget();
        String method = point.getSignature().getName();
        Class<?> clazz = target.getClass();

        Class<?>[] parameterTypes = ((MethodSignature)point.getSignature()).getMethod().getParameterTypes();

        try{
            Method m = clazz.getMethod(method,parameterTypes);
            if(m != null && m.isAnnotationPresent(SetDataSource.class)){
                SetDataSource dataSource = m.getAnnotation(SetDataSource.class);
                DataSourceContextHolder.setDBSource(dataSource.value().getName());
                logger.debug("设置了数据源类型："+dataSource.value().getName());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @After("aspect()")
    public void after(){
        DataSourceContextHolder.cleanDBSource();
    }
}