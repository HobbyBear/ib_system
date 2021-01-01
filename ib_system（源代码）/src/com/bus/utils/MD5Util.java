/**
 * @Title: MD5Util.java
 * @Package com.bus.utils
 * @author yuqingming
 * @date 2020年6月8日
 * @version V1.0
 */
package com.bus.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuqingming
 * @ClassName: MD5Util
 * @Description: MD5 工具类
 * @date 2020年6月8日
 * @since JDK 1.8
 */
public class MD5Util {

    /**
     * @Fields LOGGER : log日志
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(MD5Util.class);

    /**
     * @param str 目标字符串
     * @return 加密结果
     * @Title: md5Encode
     * @Description: 给参数字符串进行MD5加密
     */
    public static String md5Encode(String str) {
        MessageDigest md5 = null;
        byte[] byteArray = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byteArray = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("不支持的编码格式", e);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("", e);
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

}
