package com.bamboocloud.homework.pay.common;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>文件名称: Constants </p>
 * <p>文件描述: 常量类  </p>
 * <p>版权所有: 版权所有(C)2017 BambooCloud</p>
 * <p>公 司: 深圳竹云科技有限公司</p>
 *
 * @author : 文洁
 * 创建日期: 2018/4/16
 */
public class Constants {
    public enum ROLE_TYPE {
        /**
         * 干部工资基数
         */
        CADRE(1.68),

        /**
         * 基层工资基数
         */
        GRASS_ROOT(1.1);

        double val;

        ROLE_TYPE(double val) {
            this.val = val;
        }

        public double getVal() {
            return val;
        }

        public void setVal(double val) {
            this.val = val;
        }
        public static boolean contains(String val) {
            for (ROLE_TYPE typeEnum : ROLE_TYPE.values()) {
                if (typeEnum.name().equals(val)) {
                    return true;
                }
            }
            return false;
        }

        private static final Map<String, ROLE_TYPE> STRING_TO_ENUM = new HashMap<String, ROLE_TYPE>();

        static {
            for (ROLE_TYPE type : values()) {
                STRING_TO_ENUM.put(type.toString(), type);
            }
        }

        public static ROLE_TYPE fromString(String symbol) {
            return STRING_TO_ENUM.get(symbol);
        }
    }
}
