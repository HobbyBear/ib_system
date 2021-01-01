/**
 * @Title: RegexUtil.java
 * @Package com.bus.utils
 * @author yuqingming
 * @date 2020年6月13日
 * @version V1.0
 */
package com.bus.utils;

/**
 * @ClassName: RegexUtil
 * @Description: 正则工具类
 * @author yuqingming
 * @date 2020年6月13日
 * @since JDK 1.8
 */
public class RegexUtil {

    /**
     * @Fields REG_PHONE : 手机号正则表达式常量
     */
    public final static String REG_PHONE = "^1[3|4|5|7|8]\\d{9}$";

    /**
     * @Fields REG_IDCARD : 身份证正则表达式常量
     */
    public final static String REG_IDCARD = "^\\d{15}$|^\\d{17}[0-9Xx]$";

    /**
     * @Fields REG_USERNAME : 用户名正则表达式，只能输入数字字母下划线，5-18位，字母开头
     */
    public final static String REG_USERNAME = "^[A-Za-z][A-Za-z0-9_]{4,17}$";

    /**
     * @Fields REG_PASSWORD : 密码正则表达式，5-16位非空字符
     */
    public final static String REG_PASSWORD = "^[\\S]{5,16}$";

    /**
     * @Fields REG_BUSLICENSE : 车牌号正则表达式
     */
    public final static String REG_BUSLICENSE = "^[\u4e00-\u9fa5]{1}[A-Z]{1}-[A-Z_0-9]{5}$";

    /**
     * @Fields REG_LONGITUDE : 经度正则表达式 -180.0～+180.0（整数部分为0～180，必须输入1到5位小数）
     */
    public final static String REG_LONGITUDE = "^[\\-\\+]?(0?\\d{1,2}\\.\\d{1,5}|1[0-7]?\\d{1}\\.\\d{1,5}|180\\.0{1,5})$";

    /**
     * @Fields REG_LATITUDE : 纬度正则表达式 -90.0～+90.0（整数部分为0～90，必须输入1到5位小数）
     */
    public final static String REG_LATITUDE = "^[\\-\\+]?([0-8]?\\d{1}\\.\\d{1,5}|90\\.0{1,5})$";

    /**
     * @Fields REG_NUMBER : 整数正则常量
     */
    public final static String REG_NUMBER = "^(0|[1-9][0-9]*)$";

    /**
     * @Title: checkReg
     * @Description: 根据正则验数据
     * @param value
     *            需要验的数据
     * @param reg
     *            对比的正则
     * @return true: value为空或比对不合格 false: 数据合格
     */
    public static Boolean checkReg(String value, String reg) {
        if (value == null || value.isEmpty() || "".equals(value.trim())) {
            return true;
        } else {
            if (!value.matches(reg)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @Title: checkNotNull
     * @Description: 判断是否是空
     * @param value
     * @return true： 空 false： 非空
     */
    public static Boolean checkNull(String value) {
        if (value == null || value.isEmpty() || "".equals(value.trim())) {
            return true;
        }
        return false;
    }

    /**
     * @Title: checkNullAndLength
     * @Description: 必填项验空，验长度
     * @param value
     *            需要验的数据
     * @param le
     *            []数组形式，传一个就是总长度，传两个就是区间
     * @return true： 长度不合格 false： 长度合格
     */
    public static Boolean checkNullAndLength(String value, Integer le, Integer ge) {
        if (value == null || value.isEmpty() || "".equals(value.trim())) {
            return true;
        } else {
            if (ge == null || ge == 0) {
                if (value.length() > le) {
                    return true;
                }
            } else {
                if (value.length() < le || value.length() > ge) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @Title: checkLength
     * @Description: 选填项验长度
     * @param value
     *            需要验的数据
     * @param le
     *            []数组形式，传一个就是总长度，传两个就是区间
     * @return true： 长度不合格 false： 长度合格
     */
    public static Boolean checkLength(String value, Integer le, Integer ge) {
        if (value == null || "".equals(value.trim())) {
            return false;
        } else {
            if (ge == null || ge == 0) {
                if (value.length() > le) {
                    return true;
                }
            } else {
                if (value.length() < le || value.length() > ge) {
                    return true;
                }
            }
        }
        return false;
    }

}
