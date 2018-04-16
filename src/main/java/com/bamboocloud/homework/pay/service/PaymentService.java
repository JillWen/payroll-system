package com.bamboocloud.homework.pay.service;

import java.math.BigDecimal;

/**
 * <p>文件名称: PaymentService</p>
 * <p>文件描述: 工资服务接口  </p>
 * <p>版权所有: 版权所有(C)2017 BambooCloud</p>
 * <p>公 司: 深圳竹云科技有限公司</p>
 *
 * @author : 文洁
 * 创建日期: 2018/4/15
 */
public interface PaymentService {
    //TODO 工资导入 工资导出
    //TODO 基本信息存入 其他service里

    /**
     * 得到员工某个月工资
     *
     * @param employeeId 员工工号
     * @param workMonth  工作月份
     * @return 该员工当月工资
     */
    BigDecimal getPayment(String employeeId, String workMonth);
}
