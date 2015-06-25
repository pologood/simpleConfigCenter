package com.jd.jr.simpleconfig.util.pagination;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface PaginationAnnotion {
    public String pageName() default "p";

    public int pageSize() default 10;
}
