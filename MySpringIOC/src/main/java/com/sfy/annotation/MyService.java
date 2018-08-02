package com.sfy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 自定义注解 service 注入 bean 容器
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyService {

}
