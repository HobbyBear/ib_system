/**
 * @Title: ImageEnum.java
 * @Package com.bus.enums
 * @author yuqingming
 * @date 2020年6月12日
 * @version V1.0
 */
package com.bus.enums;

import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum ImageEnum {

    /**
     * @Fields LOGIN_BG : 登录界面背景
     */
    LOGIN_BG("img/login_bg.png"),
    /**
     * @Fields HOME_BG : 主界面背景
     */
    HOME_BG("img/home_bg.jpg"),
    /**
     * @Fields ICON_TITLE : 界面图标
     */
    ICON_TITLE("img/title.png"),
    /**
     * @Fields HEADER_EXIT : 关闭按钮图标
     */
    HEADER_EXIT("img/close.png"),
    /**
     * @Fields HEADER_EXIT_ON : 关闭按钮鼠标进入图标
     */
    HEADER_EXIT_ON("img/close_on.png");

    /**
     * @Fields logger : log日志
     */
    private final Logger logger = LoggerFactory.getLogger(ImageEnum.class);

    /**
     * @Fields icon : 图标
     */
    private ImageIcon icon;

    ImageEnum(String filename) {
        try {
            this.icon = new ImageIcon(filename);
        } catch (Exception e) {
            logger.error("找不到图片", e);
        }
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

}
