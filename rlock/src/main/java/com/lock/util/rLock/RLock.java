package com.lock.util.rLock;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RLock {
    //唯一标识 建议 类名+方法名，默认为""
    String name() default "";
    //过期时间，单位：秒
    int expireTime() default 50;
}
