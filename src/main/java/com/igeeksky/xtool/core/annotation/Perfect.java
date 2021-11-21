package com.igeeksky.xtool.core.annotation;

import java.lang.annotation.*;

/**
 * If the annotation {@link Perfect} appears on types, constructors and methods,
 * it indicates that the annotated code is stable and does not need to be modified later.
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-24
 */
@Target({ElementType.TYPE, ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface Perfect {

}
