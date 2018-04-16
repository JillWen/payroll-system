package com.bamboocloud.homework.pay.mapper;

import com.bamboocloud.homework.pay.model.Attendance;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>文件名称: AttendanceMapper </p>
 * <p>文件描述: 出勤Mapper  </p>
 * <p>版权所有: 版权所有(C)2017 BambooCloud</p>
 * <p>公 司: 深圳竹云科技有限公司</p>
 *
 * @author : 文洁
 * 创建日期: 2018/4/15
 */
@Repository
public interface AttendanceMapper {
    /**
     * 删除出勤信息
     * @param id 物理ID
     * @return 删除结果
     */
    int deleteByPrimaryKey(String id);

    /**
     * 批量插入出勤信息
     * @param list 出勤信息列表
     * @return 插入结果
     */
    int insertAll(@Param(value = "list") List<Attendance> list);

    /**
     * 查询出勤信息
     * @param id 物理ID
     * @return 出勤信息
     */
    Attendance selectByPrimaryKey(String id);

    /**
     * 修改出勤信息
     * @param attendance 出勤信息
     * @return 修改结果
     */
    int updateByPrimaryKeySelective(Attendance attendance);

    /**
     * 查询出勤天数
     * @param employeeId 员工工号
     * @param workMonth 工作月份
     * @return 该月该员工出勤天数
     */
    double selectWorkDays(@Param(value = "employeeId") String employeeId,
                          @Param(value = "workMonth") String workMonth);
}
