package com.bamboocloud.homework.pay.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>文件名称: Pay </p>
 * <p>文件描述: 每月工资  </p>
 * <p>版权所有: 版权所有(C)2017 BambooCloud</p>
 * <p>公 司: 深圳竹云科技有限公司</p>
 *
 * @author : 文洁
 * 创建日期: 2018/4/16
 */
public class Pay  implements Serializable {
    private static final long serialVersionUID = -8088637531804486029L;
    /**
     * 物理ID
     */
    private String id;

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
     * 出勤天数
     */
    private Double workDays;

    /**
     * 当月薪资
     */
    private BigDecimal currentWage;

    /**
     * 社保扣款
     */
    private BigDecimal socialSecurity;

    /**
     * 公司福利
     */
    private BigDecimal welfare;

    /**
     * 其他扣款
     */
    private BigDecimal otherDeduction;

    /**
     * 实际薪资
     */
    private BigDecimal finalPay;

    public Pay() {
    }

    public Pay(String id, String employeeId, String name, String workMonth, Double workDays,
               BigDecimal currentWage) {
        this.id = id;
        this.employeeId = employeeId;
        this.name = name;
        this.workMonth = workMonth;
        this.workDays = workDays;
        this.currentWage = currentWage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public BigDecimal getSocialSecurity() {
        return socialSecurity;
    }

    public Pay setSocialSecurity(BigDecimal socialSecurity) {
        this.socialSecurity = socialSecurity;
        return this;
    }

    public BigDecimal getWelfare() {
        return welfare;
    }

    public Pay setWelfare(BigDecimal welfare) {
        this.welfare = welfare;
        return this;
    }

    public BigDecimal getOtherDeduction() {
        return otherDeduction;
    }

    public Pay setOtherDeduction(BigDecimal otherDeduction) {
        this.otherDeduction = otherDeduction;
        return this;
    }

    public BigDecimal getFinalPay() {
        return finalPay;
    }

    public Pay setFinalPay(BigDecimal finalPay) {
        this.finalPay = finalPay;
        return this;
    }
}
