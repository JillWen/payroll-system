package com.bamboocloud.homework.pay.common.util.excel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 用在getter方法上的注解，用于生成表格头
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelResources {
  String title();
  int order() default 9999;
}
