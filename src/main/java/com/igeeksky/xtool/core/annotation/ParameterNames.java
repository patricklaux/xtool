package com.igeeksky.xtool.core.annotation;

import java.lang.annotation.*;

/**
 * 记录参数名称信息
 * <p>
 * JDK 1.8 之前不记录构造器和方法的参数名称；JDK 1.8 及之后的版本如果编译时未指定 -parameters，则默认不记录参数名称。
 * 因此需通过注解记录参数名信息，反射时才能根据名称匹配构造器和方法。
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
