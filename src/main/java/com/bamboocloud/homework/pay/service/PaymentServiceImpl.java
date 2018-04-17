package com.bamboocloud.homework.pay.service;

import com.bamboocloud.homework.pay.common.exception.EmployeeException;
import com.bamboocloud.homework.pay.common.util.RegexUtil;
import com.bamboocloud.homework.pay.common.util.WageCardinalityTool;
import com.bamboocloud.homework.pay.dto.PayDTO;
import com.bamboocloud.homework.pay.mapper.PayMapper;
import com.bamboocloud.homework.pay.model.Employee;
import com.bamboocloud.homework.pay.mapper.AttendanceMapper;
import com.bamboocloud.homework.pay.mapper.EmployeeMapper;
import com.bamboocloud.homework.pay.model.Pay;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>文件名称: PaymentServiceImpl </p>
 * <p>文件描述: 工资服务接口实现  </p>
 * <p>版权所有: 版权所有(C)2017 BambooCloud</p>
 * <p>公 司: 深圳竹云科技有限公司</p>
 *
 * @author : 文洁
 * 创建日期: 2018/4/15
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    AttendanceMapper attendanceMapper;
    @Autowired
    PayMapper payMapper;

    @Override
    public BigDecimal getSingleEmployeePayment(String employeeId, String workMonth) {
        if (StringUtils.isBlank(employeeId)) {
            throw new EmployeeException("员工工号不能为空");
        }
        if (!RegexUtil.isMonth(workMonth)) {
            throw new EmployeeException("员工工作月份格式错误");
        }
        Employee employee = employeeMapper.selectByPrimaryKey(employeeId);
        if (employee == null) {
            throw new EmployeeException("未找到工号为" + employeeId + "的员工");
        }
        double workDays = attendanceMapper.selectWorkDays(employeeId, workMonth);
        BigDecimal basicWage = BigDecimal.valueOf(employee.getWage());
        String role = employee.getRole();
        //方便以后扩展用的其他工资扣除项
        BigDecimal otherDeductions = BigDecimal.valueOf(0);
        return getFinalWage(basicWage, role, workDays, otherDeductions);
    }

    @Override
    public List<Pay> getEmployeePaymentsByMonth(String workMonth) {
        if (!RegexUtil.isMonth(workMonth)) {
            throw new EmployeeException("员工工作月份格式错误");
        }
        List<PayDTO> payComponents = payMapper.getWageBasicComponentByTime(workMonth);
        List<Pay> pays = new ArrayList<>();
        for (PayDTO payDTO : payComponents){
            Pay pay = setPay(workMonth, payDTO);
            pays.add(pay);
        }
        return pays;
    }

    /**
     * 设置薪资信息
     * @param workMonth 工作月份
     * @param payDTO payDTO
     * @return 薪资信息
     */
    private Pay setPay(String workMonth, PayDTO payDTO) {
        String id = UUID.randomUUID().toString();
        String employeeId = payDTO.getEmployeeId();
        String name = payDTO.getName();
        Double workDays = payDTO.getWorkDays();
        BigDecimal currentWage = payDTO.getCurrentWage();
        BigDecimal socialSecurity = getSocialSecurity(currentWage);
        BigDecimal welfare = BigDecimal.valueOf(0);
        BigDecimal otherDeduction = BigDecimal.valueOf(0);
        BigDecimal finalPay = getFinalWage(currentWage,payDTO.getRole(),workDays,otherDeduction);
        Pay pay = new Pay(id,employeeId,name,workMonth,workDays,currentWage);
        pay.setSocialSecurity(socialSecurity).setWelfare(welfare)
                .setFinalPay(finalPay).setOtherDeduction(otherDeduction);
        return pay;
    }

    /**
     * 得到扣除社保和其他扣款后的最终工资
     *
     * @param basicWage 员工基本薪资
     * @param role      员工工种
     * @param workDays  出勤天数
     * @return 其他扣款
     */
    private BigDecimal getFinalWage(BigDecimal basicWage, String role, double workDays, BigDecimal otherDeductions) {
        BigDecimal wage = basicWage.multiply
                (BigDecimal.valueOf(WageCardinalityTool.getWageCardinality(role)))
                .multiply(BigDecimal.valueOf(workDays / 22));
        BigDecimal socialSecurity = getSocialSecurity(basicWage);
        return wage.subtract(socialSecurity)
                .subtract(otherDeductions);
    }

    /**
     * 计算应缴纳社保
     * @param basicWage 基本薪资
     * @return 社保金额
     */
    private BigDecimal getSocialSecurity(BigDecimal basicWage) {
        return basicWage
                    .multiply(BigDecimal.valueOf(8 + 1 + 2L))
                    .divide(BigDecimal.valueOf(100.0));
    }
}
