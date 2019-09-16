package com.hao.commonmodel.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author MuggleLee
 * @date 2019/7/21.
 */
@Target({ElementType.METHOD})//用于描述注解的范围
@Retention(RetentionPolicy.RUNTIME)//用于描述注解的生命周期
public @interface LogAnnotation {

    /**
     * 日志模块
     *
     * @return
     */
    String module();

    /**
     * 记录参数
     * 尽量记录普通参数类型的方法，和能序列化的对象
     *
     * @return
     */
    boolean recordParam() default true;
}
