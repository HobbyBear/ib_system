/**
 * @Title: Constants.java
 * @Package com.bus.constants
 * @author yuqingming
 * @date 2020年6月8日
 * @version V1.0
 */
package com.bus.constants;

/**
 * @author yuqingming
 * @ClassName: Constants
 * @Description: 常量类
 * @date 2020年6月8日
 * @since JDK 1.8
 */
public class Constants {

    /**
     * 提示字符串
     */
    public static final String PROMPT = "提示";

    /**
     * "请选择" 字符串
     */
    public static final String CHOOSE = "请选择";

    /**
     * 消息编号Key
     */
    public static final String MESSAGE_CODE = "messageCode";
    /**
     * 数据Key
     */
    public static final String RESULT_DATA = "data";

    /**
     * 查询成功
     */
    public static final String FIND_SUCCESS = "FIND_SUCCESS";

    /**
     * 保存MESSAGE：保存成功
     */
    public static final String SAVE_SUCCESS = "保存成功";

    /**
     * 保存MESSAGE：保存失败
     */
    public static final String SAVE_FAILURE = "保存失败";

    /**
     * 查下拉框
     */
    public static final String FIND_SELECT = "FIND_SELECT";

    /**
     * 用户名和密码传参时key命名
     */
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USERCODE = "usercode";
    public static final String KEY_AUTHORITY = "authority";
    public static final String KEY_NEWPASSWORD = "newpassword";
    public static final String KEY_USERROLE = "role";

    /**
     * 查询条件传参key  condition
     */
    public static final String KEY_CONDITION = "condition";

    /**
     * 保存发送时，数据key
     */
    public static final String KEY_DATA = "data";


    // ===== 消息编号 开始 =====

    /**
     * 保存应答-保存成功
     */
    public static final String MS1000001 = "MS10000-01";

    /**
     * 保存应答-保存失败
     */
    public static final String MS1000002 = "MS10000-02";

    /**
     * 查询成功
     */
    public static final String MS2000001 = "MS20000-01";

    /**
     * 查询异常
     */
    public static final String MS2000002 = "MS20000-02";

    /**
     * 线路页面中，点击站点弹出dialog中筛选按钮请求码
     */
    public static final String MS3000001 = "MS30000-01";

    /**
     * 线路页面中，点击站点弹出dialog中筛选按钮,查询成功码
     */
    public static final String MS3000002 = "MS30000-02";


    /**
     * 登录请求
     */
    public static final String MS0010101 = "MS00101-01";


    /**
     * 修改密码
     */
    public static final String MS0010204 = "MS00102-04";

    /**
     * 修改密码-成功
     */
    public static final String MS0010401 = "MS00104-01";

    /**
     * 修改密码-失败
     */
    public static final String MS0010402 = "MS00104-02";

    /**
     * 获取全部用户请求
     */
    public static final String MS0020101 = "MS00201-01";

    /**
     * 获取全部用户成功
     */
    public static final String MS0020201 = "MS00202-01";

    /**
     * 添加用户请求
     */
    public static final String MS0030101 = "MS00301-01";

    /**
     * 添加用户应答-添加成功
     */
    public static final String MS0030201 = "MS00302-01";

    /**
     * 获取全部车辆请求
     */
    public static final String MS0040101 = "MS00401-01";

    /**
     * 添加车辆请求
     */
    public static final String MS0040102 = "MS00401-02";

    /**
     * 查询线路请求
     */
    public static final String MS0050101 = "MS00501-01";

    /**
     * 添加线路请求
     */
    public static final String MS0050102 = "MS00501-02";

    /**
     * 查询站点请求
     */
    public static final String MS0060101 = "MS00601-01";

    /**
     * 添加站点请求
     */
    public static final String MS0060102 = "MS00601-02";

    /**
     * 查询排班请求
     */
    public static final String MS0070101 = "MS00701-01";

    /**
     * 添加排班请求
     */
    public static final String MS0070102 = "MS00701-02";

    /**
     * 读卡时查排班
     */
    public static final String MS0070103 = "MS00701-03";

    /**
     * 读卡时查排班-成功
     */
    public static final String MS0070201 = "MS00702-01";

    /**
     * 读卡时查排班-失败
     */
    public static final String MS0070202 = "MS00702-02";


    // ===== 消息编号 结束 =====

    /**
     * 排班表中线路名字列
     */
    public static final Integer LINE_NAME_COLUMN_SHOW = 1;

    /**
     * 排班表中车辆名字列
     */
    public static final Integer BUS_NAME_COLUMN_SHOW = 2;

    /**
     * 排班表中趟次列
     */
    public static final Integer TC_NAME_COLUMN_SHOW = 3;

    /**
     * 排班表中用户名字列
     */
    public static final Integer USER_NAME_COLUMN_SHOW = 5;

    /**
     * 排班表中始发站点列
     */
    public static final Integer START_STATION_COLUMN = 6;

    /**
     * 排班表中终到站点列
     */
    public static final Integer END_STATION_COLUMN = 7;

    /**
     * 排班表中隐藏用户名字列
     */
    public static final Integer USER_NAME_COLUMN = 9;

    /**
     * 排班表中隐藏线路编号列
     */
    public static final Integer LINE_NAME_COLUMN = 8;

    /**
     * 车辆表中车辆状态列
     */
    public static final Integer BUSSTATUS_COLUMN_SHOW = 3;

    /**
     * 车辆表中车辆启运日期
     */
    public static final Integer BUS_START_DATE_SHOW = 4;

    /**
     * 用户表中用户状态列
     */
    public static final Integer USERSTATUS_COLUMN_SHOW = 7;

    /**
     * 用户表中角色列
     */
    public static final Integer ROLE_COLUMN_SHOW = 5;

    /**
     * 用户表中驾龄列
     */
    public static final Integer DRIVING_COLUMN_SHOW = 6;

    /**
     * 线路表中线路状态列
     */
    public static final Integer LINESTATUS_COLUMN_SHOW = 2;

    /**
     * 线路表中开线日期
     */
    public static final Integer LINE_START_DATE_SHOW = 3;

    /**
     * 线路表中线路状态列
     */
    public static final Integer LINEDIR_COLUMN_SHOW = 4;

    /**
     * 线路表中包含站点列
     */
    public static final Integer LINESTATIONS_COLUMN_SHOW = 5;

    /**
     * 用户角色管理员
     */
    public static final String ROLE_ADMIN = "管理员";
    /**
     * 用户角色调度员
     */
    public static final String ROLE_DISPATCHER = "调度员";
    /**
     * 用户角色驾驶员
     */
    public static final String ROLE_DRIVER = "驾驶员";

    /**
     * 无权限ActionCommand
     */
    public static final String NO_AUTHORITY_COMMAND = "NO_AUTHORITY";

    //修正使用的常量消息
    /**
     * 登录应答-登陆成功
     */
    public static final String MS0010201 = "MS00102-01";

    /**
     * 登录应答-无此用户
     */
    public static final String MS0010202 = "MS00102-02";

    /**
     * 登录应答-无此用户消息
     */
    public static final String MS0010202_MSG = "登录用户不存在，请提供有效的用户名信息。";

    /**
     * 登录应答-无效密码
     */
    public static final String MS0010203 = "MS00102-03";

    /**
     * 登录应答-无效密码消息
     */
    public static final String MS0010203_MSG = "密码错误，请提供有效的密码。";
}
