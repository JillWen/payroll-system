package com.bamboocloud.homework.pay.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * <p>文件名称: RegexUtil </p>
 * <p>文件描述: 正则表达工具  </p>
 * <p>版权所有: 版权所有(C)2017 BambooCloud</p>
 * <p>公 司: 深圳竹云科技有限公司</p>
 *
 * @author : 文洁
 * 创建日期: 2018/4/15
 */
public class RegexUtil {
    public static boolean isMonth(String month) {
        return !StringUtils.isBlank(month) && Pattern.matches("[0-9]{4}(0[1-9]|1[0-2])", month);
    }

    private RegexUtil() {
    }
}
