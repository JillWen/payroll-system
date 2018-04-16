package com.bamboocloud.homework.pay.common.exception;

/**
 * <p>文件名称: WrongRoleException </p>
 * <p>文件描述: 角色类型错误异常  </p>
 * <p>版权所有: 版权所有(C)2017 BambooCloud</p>
 * <p>公 司: 深圳竹云科技有限公司</p>
 *
 * @author : 文洁
 * 创建日期: 2018/4/16
 */
public class WrongRoleException extends RuntimeException{
    public WrongRoleException() {
    }

    public WrongRoleException(String message) {
        super(message);
    }

    public WrongRoleException(String message, Throwable cause) {
        super(message, cause);
    }
}
