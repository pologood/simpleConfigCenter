package com.jd.jr.simpleconfig.export.rpc.annotion;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * User: yangkuan@jd.com
 * Date: 15-1-27
 * Time: 下午7:58
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Controller {
    String value() default "";
}
