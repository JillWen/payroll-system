package com.bamboocloud.homework.pay.model;

import java.io.Serializable;

/**
 * <p>文件名称: Attendance </p>
 * <p>文件描述: 出勤  </p>
 * <p>版权所有: 版权所有(C)2017 BambooCloud</p>
 * <p>公 司: 深圳竹云科技有限公司</p>
 *
 * @author : 文洁
 * 创建日期: 2018/4/15
 */
public class Attendance implements Serializable {
    private static final long serialVersionUID = 1195246481936236896L;
    /**
     * 物理ID
     */
    private String id;

    /**
     * 工号
     */
    private String employeeId;

    /**
     * 出勤月份
     */
    private String workMonth;

    /**
     * 出勤天数
     */
    private Double workDays;

    public Attendance() {
    }

    public Attendance(String id, String employeeId, String workMonth, Double workDays) {
        this.id = id;
        this.employeeId = employeeId;
        this.workMonth = workMonth;
        this.workDays = workDays;
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
}
