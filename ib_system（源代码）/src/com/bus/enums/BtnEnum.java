/**
 * @Title: BtnEnum.java
 * @Package com.bus.enums
 * @author yuqingming
 * @date 2020年6月9日
 * @version V1.0
 */
package com.bus.enums;

/**
 * @ClassName: BtnEnum
 * @Description: 按钮枚举
 * @author yuqingming
 * @date 2020年6月9日
 * @since JDK 1.8
 */
public enum BtnEnum {

    /**
     * @Fields LOGIN : 登录按钮
     */
    LOGIN("登录", "login"),
    /**
     * @Fields CLEAR : 清空按钮
     */
    CLEAR("清空", "clear"),
    /**
     * @Fields SEARCH : 主页查询按钮
     */
    SEARCH("查询", "main_search"),
    /**
     * @Fields ADD : 添加按钮
     */
    ADD("添加", "add"),
    /**
     * @Fields DEL : 删除按钮
     */
    DEL("删除", "del"),
    /**
     * @Fields SAVE : 保存按钮
     */
    SAVE("保存", "save"),
    /**
     * @Fields LINE_STATION_SEARCH : 线路包含站点窗口查询按钮
     */
    LINE_STATION_SEARCH("查询", "station_search"),
    /**
     * @Fields LINE_STATION_CONFIRM : 线路包含站点窗口确认按钮
     */
    LINE_STATION_CONFIRM("确认", "station_confirm"),
    /**
     * @Fields SCHEDULING_CONFIRM : 排班计划窗口确认按钮
     */
    SCHEDULING_CONFIRM("确认", "scheduling_confirm"),
    /**
     * @Fields DIALOG_CLOSE : 窗口中的关闭按钮
     */
    DIALOG_CLOSE("关闭", "dialog_close"),
    /**
     * @Fields USER_CHANGE_PASS : 修改密码panel
     */
    USER_CHANGE_PASS("修改密码", "change_pass"),
    /**
     * @Fields USER_CHANGE_PASS_CONFIRM : 修改密码窗口确认按钮
     */
    USER_CHANGE_PASS_CONFIRM("确认", "changepass_confirm");

    /**
     * @Fields name : 按钮文字
     */
    private String name;

    /**
     * @Fields actionCommand : 按钮点击监听的ActionCommand
     */
    private String actionCommand;

    BtnEnum(String name, String actionCommand) {
        this.name = name;
        this.actionCommand = actionCommand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActionCommand() {
        return actionCommand;
    }

    public void setActionCommand(String actionCommand) {
        this.actionCommand = actionCommand;
    }

}
