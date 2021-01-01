/**
 * @Title: ColorEnum.java
 * @Package com.bus.enums
 * @author yuqingming
 * @date 2020年6月12日
 * @version V1.0
 */
package com.bus.enums;

import java.awt.Color;

/**
 * @ClassName: ColorEnum
 * @Description: 颜色枚举
 * @author yuqingming
 * @date 2020年6月12日
 * @since JDK 1.8
 */
public enum ColorEnum {

    /**
     * @Fields MENU_ON : 菜单鼠标进入颜色
     */
    MENU_ON(new Color(51, 102, 153)),
    /**
     * @Fields MENU_OUT : 菜单鼠标离开颜色
     */
    MENU_OUT(Color.BLACK),
    MAIN_USER_PANEL(new Color(230, 127, 35)),
    MAIN_LINE_PANEL(new Color(53, 152, 219)),
    MAIN_BUS_PANEL(new Color(155, 88, 181)),
    MAIN_STATION_PANEL(new Color(39, 174, 97)),
    MAIN_SCHEDULING_PANEL(new Color(243, 155, 19)),
    MAIN_CHANGE_PASS_PANEL(new Color(236, 76, 61));

    /**
     * @Fields color : 颜色
     */
    private Color color;

    private ColorEnum(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
