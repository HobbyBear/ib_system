/**
 * @Title: TextEnum.java
 * @Package com.bus.enums
 * @author yuqingming
 * @date 2020年6月9日
 * @version V1.0
 */
package com.bus.enums;

/**
 * @ClassName: TextEnum
 * @Description: 页面显示文字枚举
 * @author yuqingming
 * @date 2020年6月9日
 * @since JDK 1.8
 */
public enum TextEnum {

    TITLE_LOGIN("智慧公交管理系统"),
    TITLE_MAIN("智慧公交"),
    LABEL_USERNAME("用户名"),
    LABLE_USERROLE("角色"),
    LABLE_USERSTATUS("状态"),
    LABEL_BUSLICENSE("车牌号"),
    LABEL_BUSSTATUS("车辆状态"),
    LABEL_LINENAME("线路名称"),
    LABEL_LINESTATUS("线路状态"),
    LABEL_LINEDIRECTION("方向"),
    LABEL_STATIONNAME("站点名称"),
    LABEL_STATIONREGION("所在区域"),
    LABEL_STATIONSTREET("所在街道"),
    LABEL_SCHEDULING_STARTSTATION("始发站名称"),
    LABEL_SCHEDULING_ENDSTATION("终到站名称");

    /**
     * @Fields name : label名称
     */
    private String name;

    private TextEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
