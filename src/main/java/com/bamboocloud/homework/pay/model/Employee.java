package com.bamboocloud.homework.pay.model;

import java.io.Serializable;

/**
 * <p>文件名称: Employee </p>
 * <p>文件描述: 员工  </p>
 * <p>版权所有: 版权所有(C)2017 BambooCloud</p>
 * <p>公 司: 深圳竹云科技有限公司</p>
 *
 * @author : 文洁
 * 创建日期: 2018/4/15
 */
public class Employee  implements Serializable {
    private static final long serialVersionUID = 3794804122931231229L;
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
     * 基本工资
     */
    private Integer wage;

    /**
     * 工种
     */
    private String role;

    public Employee() {
    }

    public Employee(String id, String employeeId, String name, Integer wage, String role) {
        this.id = id;
        this.employeeId = employeeId;
        this.name = name;
        this.wage = wage;
        this.role = role;
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

    public Integer getWage() {
        return wage;
    }

    public void setWage(Integer wage) {
        this.wage = wage;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
