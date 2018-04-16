package com.bamboocloud.homework.pay.common.util;

import com.bamboocloud.homework.pay.common.exception.WrongRoleException;
import com.bamboocloud.homework.pay.common.Constants;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>文件名称: WageCardinalityTool </p>
 * <p>文件描述: 获取工资基数工具类 </p>
 * <p>版权所有: 版权所有(C)2017 BambooCloud</p>
 * <p>公 司: 深圳竹云科技有限公司</p>
 *
 * @author : 文洁
 * 创建日期: 2018/4/15
 */
public class WageCardinalityTool {
    private static final String CADRE = "a";
    private static final String GRASS_ROOT = "b";

    private WageCardinalityTool() {
    }

    public static double getWageCardinality(String role){
        if (StringUtils.isBlank(role)){
            throw new WrongRoleException("角色类型不能为空");
        }
        if (CADRE.equals(role)){
            return Constants.ROLE_TYPE.CADRE.getVal();
        }else if (GRASS_ROOT.equals(role)){
            return Constants.ROLE_TYPE.GRASS_ROOT.getVal();
        }else {
            throw new WrongRoleException("角色类型错误");
        }
    }
}
