package com.bamboocloud.homework.pay.dto;

import java.math.BigDecimal;

/**
 * <p>文件名称: PayDTO </p>
 * <p>文件描述: 薪资DTO  </p>
 * <p>版权所有: 版权所有(C)2017 BambooCloud</p>
 * <p>公 司: 深圳竹云科技有限公司</p>
 *
 * @author : 文洁
 * 创建日期: 2018/4/17
 */
public class PayDTO {
    /**
     * 工号
     */
    private String employeeId;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 工作月份
     */
    private String workMonth;

    /**
     * 当月出勤天数
     */
    private Double workDays;

    /**
     * 当月薪资
     */
    private BigDecimal currentWage;

    /**
     * 当月工种
     */
    private String role;

    public PayDTO() {
    }

    public PayDTO(String employeeId, String name, String workMonth,
                  Double workDays, BigDecimal currentWage, String role) {
        this.employeeId = employeeId;
        this.name = name;
        this.workMonth = workMonth;
        this.workDays = workDays;
        this.currentWage = currentWage;
        this.role = role;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkMonth() {
        return workMonth;
    }

    public void setWorkMonth(String workMonth) {
        this.workMonth = workMonth;
    }

    public Double getWorkDays() {
        return workDays;
    }

    public void setWorkDays(Double workDays) {
        this.workDays = workDays;
    }

    public BigDecimal getCurrentWage() {
        return currentWage;
    }

    public void setCurrentWage(BigDecimal currentWage) {
        this.currentWage = currentWage;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
