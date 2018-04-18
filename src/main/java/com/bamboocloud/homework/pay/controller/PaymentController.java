package com.bamboocloud.homework.pay.controller;

import com.bamboocloud.homework.pay.common.util.FileUtil;
import com.bamboocloud.homework.pay.common.util.excel.ExcelUtil;
import com.bamboocloud.homework.pay.model.Pay;
import com.bamboocloud.homework.pay.service.PaymentService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    PaymentService paymentService;

    /**
     * 获取单个员工工资
     *
     * @param employeeId 工号
     * @param workMonth  工作月份
     * @return 该员工该月工资
     */
    @RequestMapping(value = "payment", method = RequestMethod.GET)
    public BigDecimal getPayment(@RequestParam(value = "employeeId") String employeeId,
                                 @RequestParam(value = "workMonth") String workMonth) {
        return paymentService.getSingleEmployeePayment(employeeId, workMonth);
    }

    /**
     * 获取所有员工已存档到数据库的某月工资信息列表
     *
     * @param workMonth 工作月份
     * @return 工资信息列表
     */
    @RequestMapping(value = "month_payment", method = RequestMethod.GET)
    public List<Pay> getPayment(@RequestParam(value = "workMonth") String workMonth) {
        return paymentService.getEmployeePaymentsByMonth(workMonth);
    }

    /**
     * 导入出勤信息，得到工资表
     *
     * @param workMonth 工作月份
     * @param request   HttpServletRequest enctype="multipart/form-data" 传入文件name="file"
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String getPaymentByAttendanceFile(@RequestParam(value = "workMonth") String workMonth,
                                             HttpServletRequest request) {
        String file = FileUtil.uploadFile(request);
        List<Pay> pays = paymentService.getEmployeePaymentsByExcel(workMonth, file);

        try {
            if (file != null) {
                Path filePath = Paths.get(file);
                Files.delete(filePath);
            }
        } catch (IOException e) {
            LOGGER.error("delete file failed : {}", e.getMessage());
        }
        final String outPutFile = StringUtils.join("D:"
                , File.separator, "payment_", workMonth, ".xlsx");
        ExcelUtil.getInstance().exportObjToExcel(outPutFile
                , pays, Pay.class);
        return outPutFile;
    }
}
