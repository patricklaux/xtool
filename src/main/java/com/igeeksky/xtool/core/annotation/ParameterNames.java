/*
 * Copyright 2021 Patrick.lau All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


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
