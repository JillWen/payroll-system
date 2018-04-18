package com.bamboocloud.homework.pay.mapper;

import com.bamboocloud.homework.pay.model.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>文件名称: EmployeeMapper </p>
 * <p>文件描述: 员工Mapper  </p>
 * <p>版权所有: 版权所有(C)2017 BambooCloud</p>
 * <p>公 司: 深圳竹云科技有限公司</p>
 *
 * @author : 文洁
 * 创建日期: 2018/4/15
 */
@Repository
public interface EmployeeMapper {
    /**
     * 删除员工信息
     *
     * @param id 物理ID
     * @return 删除结果
     */
    int deleteByPrimaryKey(String id);

    /**
     * 批量插入员工信息
     *
     * @param list 员工信息列表
     * @return
     */
    int insertAll(@Param(value = "list") List<Employee> list);

    /**
     * 查询员工信息
     *
     * @param id 物理ID
     * @return 员工信息
     */
    Employee selectByPrimaryKey(String id);

    /**
     * 查询所有员工信息
     * @return 所有员工信息
     */
    List<Employee> selectAll();

    /**
     * 修改员工信息
     *
     * @param employee 员工信息
     * @return 修改结果
     */
    int updateByPrimaryKeySelective(Employee employee);
}
