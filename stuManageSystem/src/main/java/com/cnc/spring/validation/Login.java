package com.cnc.spring.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * 权限控制的类，可以根据实际情况增加更多的控制项
 * 这里只针对json和普通的url请求进行权限控制
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
	ResultTypeEnum value() default ResultTypeEnum.page;
}

