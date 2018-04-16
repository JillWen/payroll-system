package com.bamboocloud.homework.pay.controller;

import com.bamboocloud.homework.pay.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * <p>文件名称: PaymentController </p>
 * <p>文件描述:   </p>
 * <p>版权所有: 版权所有(C)2017 BambooCloud</p>
 * <p>公 司: 深圳竹云科技有限公司</p>
 *
 * @author : 文洁
 * 创建日期: 2018/4/16
 */
@RestController
@RequestMapping("payroll_system")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @RequestMapping(value = "payment", method = RequestMethod.GET)
    public BigDecimal getPayment(@RequestParam(value = "employeeId") String employeeId,
                                 @RequestParam(value = "workMonth") String workMonth) {
        return paymentService.getPayment(employeeId, workMonth);
    }
}
