package com.cnc.spring.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Ȩ�޿��Ƶ��࣬���Ը���ʵ��������Ӹ���Ŀ�����
 * ����ֻ���json����ͨ��url�������Ȩ�޿���
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
	ResultTypeEnum value() default ResultTypeEnum.page;
}

