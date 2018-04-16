package com.bamboocloud.homework.pay.service;

import com.bamboocloud.homework.pay.common.exception.EmployeeException;
import com.bamboocloud.homework.pay.common.util.RegexUtil;
import com.bamboocloud.homework.pay.common.util.WageCardinalityTool;
import com.bamboocloud.homework.pay.model.Employee;
import com.bamboocloud.homework.pay.mapper.AttendanceMapper;
import com.bamboocloud.homework.pay.mapper.EmployeeMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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

    @Override
    public BigDecimal getPayment(String employeeId, String workMonth) {
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
        int basicWage = employee.getWage();
        String role = employee.getRole();
        //方便以后扩展用的其他工资扣除项
        double otherDeductions = 0;
        return getFinalWage(basicWage, role, workDays, otherDeductions);
    }

    /**
     * 得到扣除社保和其他扣款后的最终工资
     *
     * @param basicWage 员工基本薪资
     * @param role 员工工种
     * @param workDays 出勤天数
     * @return 其他扣款
     */
    private BigDecimal getFinalWage(int basicWage, String role, double workDays, double otherDeductions) {

        BigDecimal wage = BigDecimal.valueOf(basicWage).multiply
                (BigDecimal.valueOf(WageCardinalityTool.getWageCardinality(role)))
                .multiply(BigDecimal.valueOf(workDays / 22));
        BigDecimal socialSecurity = BigDecimal.valueOf(basicWage)
                .multiply(BigDecimal.valueOf(8 + 1 + 2L))
                .divide(BigDecimal.valueOf(100.0));
        return wage.subtract(socialSecurity)
                .subtract(BigDecimal.valueOf(otherDeductions));
    }
}
