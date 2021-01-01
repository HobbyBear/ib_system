/**
 * @Title: ModuleEnum.java
 * @Package com.bus.enums
 * @author yuqingming
 * @date 2020年6月10日
 * @version V1.0
 */
package com.bus.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * @ClassName: ModuleEnum
 * @Description: 模块枚举
 * @author yuqingming
 * @date 2020年6月10日
 * @since JDK 1.8
 */
public enum ModuleEnum {

    /**
     * @Fields BUS : 车辆模块
     */
    BUS("车辆信息管理"
            , Arrays.asList("车辆编号", "车牌号", "车型", "车辆状态", "起运日期")
            , Arrays.asList(60, 120, 100, 100, 100)),
    /**
     * @Fields USER : 员工模块
     */
    USER("员工信息管理"
            , Arrays.asList("员工编号", "登录名", "姓名", "手机号", "身份证号", "角色", "驾龄", "用户状态", "密码")
            , Arrays.asList(80, 60, 60, 80, 110, 30, 30, 50, 0)),
    /**
     * @Fields LINE : 线路模块
     */
    LINE("线路信息管理"
            , Arrays.asList("线路编号", "线路名称", "线路状态", "开线日期", "线路方向", "包含站点")
            , Arrays.asList(80, 100, 80, 100, 100, 100)),
    /**
     * @Fields STATION : 站点模块
     */
    STATION("站点信息管理"
            , Arrays.asList("站点编号", "站点名称", "经度", "纬度", "所在区域", "所在街道")
            , Arrays.asList(80, 100, 100, 100, 100, 100)),
    /**
     * @Fields SCHEDULING : 排班模块
     */
    SCHEDULING("排班信息管理"
            , Arrays.asList("排班编号", "线路名称", "车辆信息", "趟次", "每趟时间", "司机姓名", "始发站点", "终点站点")
            , Arrays.asList(80, 90, 90, 20, 40, 120, 50, 50));

    /**
     * @Fields columnIdentifiers : 表格列名
     */
    private Vector<String> columnIdentifiers;

    /**
     * @Fields preferredWidth : 表格列宽
     */
    private List<Integer> preferredWidth;

    /**
     * @Fields name : 模块名称
     */
    private String name;

    ModuleEnum(String name, List<String> columnList, List<Integer> preferredWidth) {
        this.name = name;
        this.columnIdentifiers = new Vector<>(columnList);
        this.preferredWidth = preferredWidth;
    }

    public Vector<String> getColumnIdentifiers() {
        return columnIdentifiers;
    }

    public void setColumnIdentifiers(Vector<String> columnIdentifiers) {
        this.columnIdentifiers = columnIdentifiers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPreferredWidth() {
        return preferredWidth;
    }

    public void setPreferredWidth(List<Integer> preferredWidth) {
        this.preferredWidth = preferredWidth;
    }
}
