/**
 * @Title: CheckDataUtil.java
 * @Package com.bus.utils
 * @author yuqingming
 * @date 2020年6月8日
 * @version V1.0
 */
package com.bus.utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.bus.constants.Constants;
import com.bus.dao.model.Bus;
import com.bus.dao.model.Line;
import com.bus.dao.model.Scheduling;
import com.bus.dao.model.Station;
import com.bus.dao.model.User;
import com.bus.enums.ModuleEnum;
import com.bus.view.LoginView;

/**
 * @ClassName: CheckData
 * @Description: 数据校验工具类
 * @author yuqingming
 * @date 2020年6月8日
 * @since JDK 1.8
 */
public class CheckDataUtil {

    /**
     * @Title: checkTableData
     * @Description: 表格数据验证
     * @param vector
     *            table业务数据
     * @param currentModule
     *            当前操作的模块枚举
     * @param table
     *            table对象
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Boolean checkTableData(Vector vector, ModuleEnum currentModule, JTable table) throws SQLException {
        List<Object> datas = TableModelUtil.getTableDataListByModule(currentModule, vector);
        switch (currentModule) {
            case USER:
                for (int i = 0; i < datas.size(); i++) {
                    User user = (User) datas.get(i);
                    // 用户名 只能输入数字字母下划线，5-18位，字母开头
                    if (RegexUtil.checkReg(user.getLoginName(), RegexUtil.REG_USERNAME)) {
                        table.editCellAt(i, 1);
                        JOptionPane.showMessageDialog(null, "用户名 只能输入数字字母下划线，5-18位，字母开头", "错误", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    // 姓名 必填，20位字符长度限制
                    if (RegexUtil.checkNullAndLength(user.getName(), 20, null)) {
                        table.editCellAt(i, 2);
                        JOptionPane.showMessageDialog(null, "姓名 必填，20位字符长度限制", "错误", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    // 电话
                    if (RegexUtil.checkReg(user.getPhone(), RegexUtil.REG_PHONE)) {
                        table.editCellAt(i, 3);
                        JOptionPane.showMessageDialog(null, "电话不能为空或号码错误", "错误", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    // 身份证
                    if (RegexUtil.checkReg(user.getIdCard(), RegexUtil.REG_IDCARD)) {
                        table.editCellAt(i, 4);
                        JOptionPane.showMessageDialog(null, "身份证验证出错", "错误", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    // 角色
                    if (RegexUtil.checkNull(user.getRole()) || Constants.CHOOSE.equals(user.getRole())) {
                        table.editCellAt(i, 5);
                        JOptionPane.showMessageDialog(null, "角色设置错误", "错误", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    // 驾龄 选了驾驶员则驾龄不为0
                    if (Constants.ROLE_DRIVER.equals(user.getRole())
                            && !RegexUtil.checkNull(String.valueOf(user.getDriving())) && user.getDriving() == 0) {
                        table.editCellAt(i, 6);
                        JOptionPane.showMessageDialog(null, "驾驶员驾龄不为空，且不为0", "错误", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    // 状态 不为空
                    if (RegexUtil.checkNull(user.getStatus())) {
                        table.editCellAt(i, 7);
                        JOptionPane.showMessageDialog(null, "状态不能为空", "错误", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                }
                break;
            case BUS:
                for (int i = 0; i < datas.size(); i++) {
                    Bus bus = (Bus) datas.get(i);
                    // 车牌号
                    if (RegexUtil.checkReg(bus.getBusLicense(), RegexUtil.REG_BUSLICENSE)) {
                        table.editCellAt(i, 1);
                        JOptionPane.showMessageDialog(null, "车牌号为空或格式不正确！", "错误", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    // 车型 选填，50字符
                    if (RegexUtil.checkLength(bus.getBusType(), 50, null)) {
                        table.editCellAt(i, 2);
                        JOptionPane.showMessageDialog(null, "车型最多50个字符！", "错误", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                }
                break;
            case LINE:
                for (int i = 0; i < datas.size(); i++) {
                    Line line = (Line) datas.get(i);
                    // 验证开始
                    // 线路名称必填，20位字符长度限制
                    if (RegexUtil.checkNullAndLength(line.getLineName(), 50, null)) {
                        table.editCellAt(i, 1);
                        JOptionPane.showMessageDialog(null, "线路名称不能为空 ，且最多20个字符！", "错误", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    // 线路方向
                    if (RegexUtil.checkNull(line.getDirection()) || Constants.CHOOSE.equals(line.getDirection())) {
                        table.editCellAt(i, 4);
                        return false;
                    }
                    // 站点
                    if (RegexUtil.checkNull(line.getAllStation())) {
                        table.editCellAt(i, 5);
                        return false;
                    }
                }
                break;
            case STATION:
                for (int i = 0; i < datas.size(); i++) {
                    Station station = (Station) datas.get(i);
                    // 验证开始
                    // 站点名必填，位字符长度限制
                    if (RegexUtil.checkNullAndLength(station.getStationName(), 50, null)) {
                        table.editCellAt(i, 1);
                        JOptionPane.showMessageDialog(null, "站点名不能为空，且最多50个字符！", "错误", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    // 经度 -180.0～+180.0（整数部分为0～180，必须输入1到5位小数）
                    if (RegexUtil.checkReg(station.getLongitude(), RegexUtil.REG_LONGITUDE)) {
                        table.editCellAt(i, 2);
                        JOptionPane.showMessageDialog(null, "站点经度为空或格式不正确！", "错误", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    // 纬度 -90.0～+90.0（整数部分为0～90，必须输入1到5位小数）
                    if (RegexUtil.checkReg(station.getLatitude(), RegexUtil.REG_LATITUDE)) {
                        table.editCellAt(i, 3);
                        JOptionPane.showMessageDialog(null, "站点纬度为空或格式不正确！", "错误", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    // 区域必填，50位字符长度限制
                    if (RegexUtil.checkNullAndLength(station.getRegion(), 50, null)) {
                        table.editCellAt(i, 4);
                        JOptionPane.showMessageDialog(null, "区域不能为空，且最多50个字符！", "错误", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    // 街道必填，50位字符长度限制
                    if (RegexUtil.checkNullAndLength(station.getStreet(), 50, null)) {
                        table.editCellAt(i, 5);
                        JOptionPane.showMessageDialog(null, "街道不能为空，且最多50个字符！", "错误", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                }
                break;
            case SCHEDULING:
                for (int i = 0; i < datas.size(); i++) {
                    Scheduling scheduling = (Scheduling) datas.get(i);
                    // 线路下拉，必选
                    if (RegexUtil.checkNull(scheduling.getLineName())
                            || Constants.CHOOSE.equals(scheduling.getLineName())) {
                        table.editCellAt(i, 1);
                        return false;
                    }
                    // 车辆下拉，必选
                    if (RegexUtil.checkNull(scheduling.getBusCode()) || Constants.CHOOSE.equals(scheduling.getBusCode())) {
                        table.editCellAt(i, 2);
                        return false;
                    }
                    // 每趟时间
                    if (RegexUtil.checkReg(scheduling.getTcTime(), RegexUtil.REG_NUMBER)) {
                        table.editCellAt(i, 4);
                        JOptionPane.showMessageDialog(null, "趟次时间格式为整形！", "错误", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    // 用户下拉，必选
                    if (RegexUtil.checkNull(scheduling.getUserName())
                            || Constants.CHOOSE.equals(scheduling.getUserName())) {
                        table.editCellAt(i, 5);
                        return false;
                    }
                    // 始发站下拉，必选
                    if (RegexUtil.checkNull(scheduling.getStartStation())
                            || Constants.CHOOSE.equals(scheduling.getStartStation())) {
                        table.editCellAt(i, 6);
                        return false;
                    }
                    // 终到站下拉，必选
                    if (RegexUtil.checkNull(scheduling.getEndStation())
                            || Constants.CHOOSE.equals(scheduling.getEndStation())) {
                        table.editCellAt(i, 7);
                        return false;
                    }
                }
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * @Title: checkLoginData
     * @Description: 验登陆时用户名和密码是否正确
     * @param login
     * @return
     */
    public static Boolean checkLoginData(LoginView login) {
        if (RegexUtil.checkReg(login.getTxUsername().getText().trim(), RegexUtil.REG_USERNAME)) {
            JOptionPane.showMessageDialog(login, "用户名不能为空或格式错误！字母开头,5-18位字母或数字下划线", Constants.PROMPT,
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (RegexUtil.checkReg(login.getTxPassword().getText(), RegexUtil.REG_PASSWORD)) {
            JOptionPane.showMessageDialog(login, "密码不能为空或格式错误！！5-16位非空字符", Constants.PROMPT, JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

}
