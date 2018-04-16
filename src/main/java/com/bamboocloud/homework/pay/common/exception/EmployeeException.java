package com.bamboocloud.homework.pay.common.exception;

/**
 * <p>文件名称: EmployeeException </p>
 * <p>文件描述: 员工信息异常 </p>
 * <p>版权所有: 版权所有(C)2017 BambooCloud</p>
 * <p>公 司: 深圳竹云科技有限公司</p>
 *
 * @author : 文洁
 * 创建日期: 2018/4/16
 */
public class EmployeeException extends RuntimeException{
    public EmployeeException() {
    }

    public EmployeeException(String message) {
        super(message);
    }

    public EmployeeException(String message, Throwable cause) {
        super(message, cause);
    }
}
