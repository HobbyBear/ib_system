/**
 * @Title: ParseConfig.java
 * @Package com.bus.utils
 * @author yuqingming
 * @date 2020年6月8日
 * @version V1.0
 */
package com.bus.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: ParseConfig
 * @Description: 配置文件解析类
 * @author yuqingming
 * @date 2020年6月8日
 * @since JDK 1.8
 */
public class ParseConfig {

    /**
     * @Fields LOGGER : log日志
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(ParseConfig.class);

    /**
     * @Fields configMap : 静态Map，存储配置文件信息
     */
    private static Map<String, String> configMap = new HashMap<>();

    /**
     * @Title: getConfigMap
     * @Description: 获取配置文件Map
     * @param @return
     * @return Map<String, String>
     * @throws
     */
    public static Map<String, String> getConfigMap() {
        return configMap;
    }

    /**
     * @Title: initParse
     * @Description: 解析配置文件
     * @throws
     */
    public static void initParse() {
        LOGGER.info("开始解析配置文件");
        // 获取配置Properties对象
        Properties properties = new Properties();
        // 获取配置文件路径
        String configPath = new File("config/config.xml").getAbsolutePath();
        // 解析配置文件
        try {
            properties.loadFromXML(new FileInputStream(new File(configPath)));
            LOGGER.info("properties keys: {}", properties.keySet());
            // 连接超时时间
            configMap.put("connTime", properties.getProperty("connTime"));
            // mina服务端地址
            configMap.put("minaServer", properties.getProperty("minaServer"));
            // mina服务端端口
            configMap.put("mimaServerPort", properties.getProperty("mimaServerPort"));
            LOGGER.info("configMap data: {}", configMap);
            LOGGER.info("完成解析配置文件");
        } catch (IOException e) {
            LOGGER.error("解析配置文件出错", e);
        }
    }

}
