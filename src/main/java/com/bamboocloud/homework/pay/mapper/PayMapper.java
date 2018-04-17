package com.bamboocloud.homework.pay.mapper;

import com.bamboocloud.homework.pay.dto.PayDTO;
import com.bamboocloud.homework.pay.model.Pay;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>文件名称: PayMapper </p>
 * <p>文件描述: 每月工资Mapper  </p>
 * <p>版权所有: 版权所有(C)2017 BambooCloud</p>
 * <p>公 司: 深圳竹云科技有限公司</p>
 *
 * @author : 文洁
 * 创建日期: 2018/4/16
 */
@Repository
public interface PayMapper {
    /**
     * 获得工资信息
     *
     * @param id 物理ID
     * @return 工资信息
     */
    Pay selectByPrimaryKey(String id);

    /**
     * 获得所有工资信息
     *
     * @return 所有工资信息
     */
    List<Pay> selectAll();

    /**
     * 批量插入工资信息
     *
     * @param list 工资信息列表
     * @return 插入条数
     */
    int insertAll(@Param(value = "list") List<Pay> list);

    /**
     * 根据工作月份查出当月所有员工的基本薪资，当月出勤天数等影响最后工资的信息
     * @param workMonth 月份
     * @return PayDTO
     */
    List<PayDTO> getWageBasicComponentByTime(String workMonth);
}
