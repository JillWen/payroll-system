package com.bamboocloud.homework.pay.service;

import com.bamboocloud.homework.pay.model.Pay;

import java.math.BigDecimal;
import java.util.List;

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
    BigDecimal getSingleEmployeePayment(String employeeId, String workMonth);

    /**
     * 得到某个月所有员工的工资
     *
     * @param workMonth 工作月份
     * @return 所有工资信息
     */
    List<Pay> getEmployeePaymentsByMonth(String workMonth);

    /**
     * 通过出勤表格得到某个月所有员工的工资
     *
     * @param workMonth 工作月份
     * @return 所有工资信息
     */
    List<Pay> getEmployeePaymentsByExcel(String workMonth, String file);
}
