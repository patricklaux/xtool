package com.igeeksky.xtool.core.annotation;

import java.lang.annotation.*;

/**
 * <p><b>反射时提供参数名称信息</b></p>
 * JDK 1.8 之前无法获取构造器和方法的参数名称；
 * JDK 1.8及之后如果编译时未指定 -parameters，则默认不记录参数名称.因此需要通过注解来补全信息，反射时才能根据名称匹配参数。
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-08
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParameterNames {

    String[] value() default {};

}
