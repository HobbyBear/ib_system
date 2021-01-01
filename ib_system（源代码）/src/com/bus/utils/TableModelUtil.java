/**
 * @Title: TableModelUtil.java
 * @Package com.bus.utils
 * @author yuqingming
 * @date 2020年6月11日
 * @version V1.0
 */
package com.bus.utils;

import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableRowSorter;

import com.bus.constants.Constants;
import com.bus.dao.model.*;
import com.bus.enums.ModuleEnum;
import com.bus.service.*;
import com.bus.service.impl.ServiceFactory;
import com.bus.view.dto.ComboBoxDto;
import com.bus.view.listener.CustomerTableModelListener;
import com.github.lgooddatepicker.tableeditors.DateTableEditor;

/**
 * @author yuqingming
 * @ClassName: TableModelUtil
 * @Description: 表格模型工具类
 * @date 2020年6月11日
 * @since JDK 1.8
 */
public class TableModelUtil {

    /**
     * @param moduleEnum    模块
     * @param data          业务数据集合
     * @param focusListener 焦点变化监听
     * @return 创建好的业务表格
     * @Title: getTableModel
     * @Description: 根据业务模块及数据创建TableModel并返回JTable对象
     */
    public static JTable getTableByModule(ModuleEnum moduleEnum, List<?> data, FocusListener focusListener, List<ComboBoxDto> roleComboboxList) throws SQLException {
        DefaultTableModel tableModel = new DefaultTableModel() {
            /**
             * @Fields serialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return true;
            }

        };
        // 初始化table
        final JTable table = new JTable(tableModel);
        tableModel.addTableModelListener(new CustomerTableModelListener(moduleEnum, table));
        // 设置表头不可移动
        table.getTableHeader().setReorderingAllowed(false);
        // 设置行的高度
        table.setRowHeight(23);
        table.setAutoscrolls(true);
        table.setFillsViewportHeight(true);
        final Vector<Object> dataVector = new Vector<>();
        // 日期控件
        DateTableEditor dateEdit = new DateTableEditor();
        dateEdit.getDatePickerSettings().setTranslationClear("清空");
        dateEdit.getDatePickerSettings().setTranslationToday("今天");
        switch (moduleEnum) {
            case BUS:
                for (int i = 0; i < data.size(); i++) {
                    Vector<Object> buses = new Vector<>();
                    Bus bus = (Bus) data.get(i);
                    buses.add(bus.getBusCode());
                    buses.add(bus.getBusLicense());
                    buses.add(bus.getBusType());
                    buses.add(bus.getBusStatus());
                    buses.add(bus.getStartTime());
                    dataVector.add(buses);
                }
                // 设置车辆状态下拉列表
                JComboBox<ComboBoxDto> busCbx = new JComboBox<>();
                busCbx.addItem(new ComboBoxDto("1", "启用"));
                busCbx.addItem(new ComboBoxDto("0", "停运"));
                ;
                tableModel.setDataVector(dataVector, moduleEnum.getColumnIdentifiers());
                // 创建编辑器
                TableCellEditor busTce = new DefaultCellEditor(busCbx);
                // 车辆状态,设置下拉
                table.getColumnModel().getColumn(Constants.BUSSTATUS_COLUMN_SHOW).setCellEditor(busTce);
                // 时间列添加时间可选
                table.getColumnModel().getColumn(Constants.BUS_START_DATE_SHOW).setCellEditor(dateEdit);
                break;
            case USER:
                for (Object datum : data) {
                    Vector<Object> users = new Vector<>();
                    User user = (User) datum;
                    users.add(user.getCode());
                    users.add(user.getLoginName());
                    users.add(user.getName());
                    users.add(user.getPhone());
                    users.add(user.getIdCard());
                    // 在表格上显示
                    if ("1".equals(user.getRole())) {
                        user.setRole("驾驶员");
                    } else if ("2".equals(user.getRole())) {
                        user.setRole("管理员");
                    } else {
                        user.setRole("调度员");
                    }
                    users.add(user.getRole());
                    users.add(user.getDriving());
                    users.add(user.getStatus());
                    users.add(user.getPassword());
                    dataVector.add(users);
                }
                //标题
                tableModel.setDataVector(dataVector, moduleEnum.getColumnIdentifiers());
                // 设置用户状态下拉列表
                JComboBox<String> cbx = new JComboBox<>();
                cbx.addItem("启用");
                cbx.addItem("禁用");
                //  创建编辑器
                TableCellEditor tce = new DefaultCellEditor(cbx);
                // 下表为第7列的是用户状态,设置下拉
                table.getColumnModel().getColumn(Constants.USERSTATUS_COLUMN_SHOW).setCellEditor(tce);

                // 设置角色下拉列表
                JComboBox<String> roleCombobox = new JComboBox<>();
                for (ComboBoxDto cbd : roleComboboxList) {
                    if (!"-1".equals(cbd.getKey())) {
                        roleCombobox.addItem(cbd.getValue());
                    }
                }

                //  创建编辑器
                TableCellEditor roleTce = new DefaultCellEditor(roleCombobox);
                // 下表为第7列的是用户状态,设置下拉
                table.getColumnModel().getColumn(Constants.ROLE_COLUMN_SHOW).setCellEditor(roleTce);

                // 设置驾龄下拉列表
                JComboBox<String> drvingCombobox = new JComboBox<>();
                for (int i = 0; i < 49; i++) {
                    drvingCombobox.addItem(i + "");
                }
                //  创建编辑器
                TableCellEditor drvingTce = new DefaultCellEditor(drvingCombobox);
                table.getColumnModel().getColumn(6).setCellEditor(drvingTce);

                // 隐藏密码
                table.getColumnModel().getColumn(8).setMaxWidth(0);
                table.getColumnModel().getColumn(8).setMinWidth(0);
                break;
            case LINE:
                for (int i = 0; i < data.size(); i++) {
                    Vector<Object> lines = new Vector<>();
                    Line line = (Line) data.get(i);
                    lines.add(line.getLineCode());
                    lines.add(line.getLineName());
                    lines.add(line.getStatus());
                    lines.add(line.getStartLineTime());
                    lines.add(line.getDirection());
                    lines.add(line.getAllStation());
                    dataVector.add(lines);
                }
                // 设置线路状态下拉列表
                JComboBox<String> lineCbx = new JComboBox<>();
                lineCbx.addItem("启用");
                lineCbx.addItem("停运");
                tableModel.setDataVector(dataVector, moduleEnum.getColumnIdentifiers());
                //  创建编辑器
                TableCellEditor lineTce = new DefaultCellEditor(lineCbx);
                // 线路状态,设置下拉
                table.getColumnModel().getColumn(Constants.LINESTATUS_COLUMN_SHOW).setCellEditor(lineTce);

                // 设置线路方向下拉列表
                JComboBox<String> directionCbx = new JComboBox<>();
                directionCbx.addItem(Constants.CHOOSE);
                directionCbx.addItem("上行");
                directionCbx.addItem("下行");
                //  创建编辑器
                TableCellEditor directionTce = new DefaultCellEditor(directionCbx);
                // 线路方向,设置下拉
                table.getColumnModel().getColumn(Constants.LINEDIR_COLUMN_SHOW).setCellEditor(directionTce);

                // 包含站点编辑器
                JTextField containStationsField = new JTextField();
                table.getColumnModel().getColumn(Constants.LINESTATIONS_COLUMN_SHOW).setCellEditor(new DefaultCellEditor(containStationsField));

                table.getColumnModel().getColumn(Constants.LINE_START_DATE_SHOW).setCellEditor(dateEdit);

                containStationsField.addFocusListener(focusListener);
                break;
            case STATION:
                for (int i = 0; i < data.size(); i++) {
                    Vector<Object> stations = new Vector<>();
                    Station station = (Station) data.get(i);
                    stations.add(station.getStationCode());
                    stations.add(station.getStationName());
                    stations.add(station.getLongitude());
                    stations.add(station.getLatitude());
                    stations.add(station.getRegion());
                    stations.add(station.getStreet());
                    dataVector.add(stations);
                }
                tableModel.setDataVector(dataVector, moduleEnum.getColumnIdentifiers());
                break;
            case SCHEDULING:
                LineService lineService = ServiceFactory.getLineService();
                UserService userService = ServiceFactory.getUserService();
                StationService stationService = ServiceFactory.getStationService();
                for (int i = 0; i < data.size(); i++) {
                    Vector<Object> Scheduling = new Vector<>();
                    com.bus.dao.model.Scheduling scheduling = (com.bus.dao.model.Scheduling) data.get(i);
                    Scheduling.add(scheduling.getCode());

                    // 获取线路名称，展现在面板中
                    String lineName = lineService.findNameByCode(scheduling.getLineCode());
                    Scheduling.add(lineName);

                    Scheduling.add(scheduling.getBusCode());
                    Scheduling.add(scheduling.getTcNumber());
                    Scheduling.add(scheduling.getTcTime());

                    // 获取司机名称，展现在面板中
                    String userCode = scheduling.getUserCode();
                    String userName = userService.findUserNameByCode(userCode);
                    Scheduling.add(userName);

                    // 获取站点名称，展现在面板中
                    String stationCode = scheduling.getStartStation();
                    String stationName = stationService.findNameByCode(stationCode);
                    Scheduling.add(stationName);
                    stationCode = scheduling.getEndStation();
                    stationName = stationService.findNameByCode(stationCode);
                    Scheduling.add(stationName);

                    dataVector.add(Scheduling);
                }
                tableModel.setDataVector(dataVector, moduleEnum.getColumnIdentifiers());

                // 设线路名称为下拉列表
                JComboBox<String> lineNames = new JComboBox<>();
                Line lineCondition = new Line();
                lineCondition.setLineName("");
                lineCondition.setDirection("2");
                lineCondition.setStatus("2");
                List<Line> lines = lineService.find(lineCondition);
                for (Line line : lines) {
                    lineNames.addItem(line.getLineName());
                }
                //  创建编辑器
                TableCellEditor lineNamesTce = new DefaultCellEditor(lineNames);
                // 线路,设置下拉
                table.getColumnModel().getColumn(Constants.LINE_NAME_COLUMN_SHOW).setCellEditor(lineNamesTce);

                // 设车牌号栏为下拉列表
                JComboBox<String> busLicense = new JComboBox<>();
                BusService busService = ServiceFactory.getBusService();
                Bus buscCondition = new Bus();
                buscCondition.setBusLicense("");
                buscCondition.setBusStatus("2");
                List<Bus> buses = busService.find(buscCondition);
                for (Bus bus : buses) {
                    busLicense.addItem(bus.getBusLicense());
                }
                //  创建编辑器
                TableCellEditor busLicenseTce = new DefaultCellEditor(busLicense);
                // 车牌号,设置下拉
                table.getColumnModel().getColumn(Constants.BUS_NAME_COLUMN_SHOW).setCellEditor(busLicenseTce);

                // 设司机姓名栏为下拉列表
                JComboBox<String> userName = new JComboBox<>();
                User userCondition = new User();
                userCondition.setName("");
                userCondition.setRole(null);
                userCondition.setStatus(null);
                List<User> users = userService.find(userCondition);
                for (User user : users) {
                    userName.addItem(user.getName());
                }
                //  创建编辑器
                TableCellEditor userNameTce = new DefaultCellEditor(userName);
                // 司机姓名,设置下拉
                table.getColumnModel().getColumn(Constants.USER_NAME_COLUMN_SHOW).setCellEditor(userNameTce);
                // 设始发站点为下拉列表
                JComboBox<String> stations = new JComboBox<>();
                Station stationCondition = new Station();
                stationCondition.setStationName("");
                stationCondition.setRegion("");
                stationCondition.setStreet("");
                List<Station> startStations = stationService.find(stationCondition);
                for (Station station : startStations) {
                    stations.addItem(station.getStationName());
                }
                //  创建编辑器
                TableCellEditor stationTce = new DefaultCellEditor(stations);
                // 起始站名称,设置下拉
                table.getColumnModel().getColumn(Constants.START_STATION_COLUMN).setCellEditor(stationTce);
                // 终点站名称，设置下拉
                table.getColumnModel().getColumn(Constants.END_STATION_COLUMN).setCellEditor(stationTce);
                break;
            default:
                break;
        }
        // 设置列宽
        setColumnPreferredWidth(table, moduleEnum);

        // 表格排序
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tableModel);
        table.setRowSorter(sorter);

        return table;
    }

    /**
     * @param table      表格对象
     * @param moduleEnum 模块枚举,用于获取表格列宽
     * @Title: setColumnPreferredWidth
     * @Description: 设置列宽
     */
    private static void setColumnPreferredWidth(JTable table, ModuleEnum moduleEnum) {
        // 如果相关功能的表格列宽不为空,则设置对应列宽,否则用默认列宽
        if (moduleEnum.getPreferredWidth() != null) {
            for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setPreferredWidth(moduleEnum.getPreferredWidth().get(i));
            }
        }
    }

    /**
     * @param moduleEnum 模块
     * @param data       Vector类型的表格数据
     * @return List<Model>类型的表格数据
     * @Title: getTableDataListByModule
     * @Description: 根据模块获取List<Model>类型的表格数据
     */
    @SuppressWarnings("rawtypes")
    public static List<Object> getTableDataListByModule(ModuleEnum moduleEnum, Vector<Object> data) throws SQLException {
        List<Object> datas = new ArrayList<>();
        Vector obj = new Vector<>();
        for (Object t : data) {
            switch (moduleEnum) {
                case BUS:
                    obj = (Vector) t;
                    Bus bus = new Bus();
                    bus.setBusCode(obj.get(0) == null ? null : String.valueOf(obj.get(0)));
                    bus.setBusLicense(obj.get(1) == null ? null : String.valueOf(obj.get(1)));
                    bus.setBusType(obj.get(2) == null ? null : String.valueOf(obj.get(2)));
                    bus.setBusStatus(obj.get(3) == null ? null : String.valueOf(obj.get(3)));
                    bus.setStartTime(obj.get(4) == null ? null : String.valueOf(obj.get(4)));
                    datas.add(bus);
                    break;
                case USER:
                    obj = (Vector) t;
                    User user = new User();
                    user.setCode(obj.get(0) == null ? null : String.valueOf(obj.get(0)));
                    user.setLoginName(obj.get(1) == null ? null : String.valueOf(obj.get(1)));
                    user.setName(obj.get(2) == null ? null : String.valueOf(obj.get(2)));
                    user.setPhone(obj.get(3) == null ? null : String.valueOf(obj.get(3)));
                    user.setIdCard(obj.get(4) == null ? null : String.valueOf(obj.get(4)));
                    if (obj.get(5) == null || String.valueOf(obj.get(5)).length() == 0) {
                        user.setRole(null);
                    } else {
                        String roleText = String.valueOf(obj.get(5));
                        if ("管理员".equals(roleText)) {
                            roleText = "2";
                        } else if ("驾驶员".equals(roleText)) {
                            roleText = "1";
                        } else {
                            roleText = "3";
                        }
                        user.setRole(roleText);
                    }
                    user.setDriving(obj.get(6) == null ? 0 : Integer.parseInt(String.valueOf(obj.get(6))));
                    user.setStatus(obj.get(7) == null ? null : String.valueOf(obj.get(7)));
                    // 设置初始密码
                    user.setPassword(MD5Util.md5Encode("123456"));
                    datas.add(user);
                    break;
                case LINE:
                    obj = (Vector) t;
                    Line line = new Line();
                    line.setLineCode(obj.get(0) == null ? null : String.valueOf(obj.get(0)));
                    line.setLineName(obj.get(1) == null ? null : String.valueOf(obj.get(1)));
                    line.setStatus(obj.get(2) == null ? null : String.valueOf(obj.get(2)));
                    line.setStartLineTime(obj.get(3) == null ? null : String.valueOf(obj.get(3)));
                    line.setDirection(obj.get(4) == null ? null : String.valueOf(obj.get(4)));

                    if ("启用".equals(line.getStatus())) {
                        line.setStatus("1");
                    } else {
                        line.setStatus("0");
                    }
                    if ("上行".equals(line.getDirection())) {
                        line.setDirection("1");
                    } else {
                        line.setDirection("0");
                    }

                    if (obj.get(5) == null) {
                        line.setAllStation(null);
                    } else {
                        String allStation = String.valueOf(obj.get(5));
                        String[] allStationSplit = allStation.split(",");
                        String stationCodes = "";
                        for (String station : allStationSplit) {
                            String code = station;
                            if ("".equals(stationCodes)) {
                                stationCodes += code;
                            } else {
                                stationCodes += "," + code;
                            }
                        }
                        line.setAllStation(stationCodes);
                    }
                    datas.add(line);
                    break;
                case STATION:
                    obj = (Vector) t;
                    Station station = new Station();
                    station.setStationCode(obj.get(0) == null ? null : String.valueOf(obj.get(0)));
                    station.setStationName(obj.get(1) == null ? null : String.valueOf(obj.get(1)));
                    station.setLongitude(obj.get(2) == null ? null : String.valueOf(obj.get(2)));
                    station.setLatitude(obj.get(3) == null ? null : String.valueOf(obj.get(3)));
                    station.setRegion(obj.get(4) == null ? null : String.valueOf(obj.get(4)));
                    station.setStreet(obj.get(5) == null ? null : String.valueOf(obj.get(5)));
                    datas.add(station);
                    break;
                case SCHEDULING:
                    obj = (Vector) t;
                    Scheduling scheduling = new Scheduling();
                    scheduling.setCode(obj.get(0) == null ? null : String.valueOf(obj.get(0)));
                    String lineName = obj.get(1) == null ? "" : String.valueOf(obj.get(1));
                    scheduling.setLineName(lineName);
                    // 找到编号
                    LineService lineService = ServiceFactory.getLineService();
                    String lineCode = lineService.findCodeByName(lineName);
                    scheduling.setLineCode(lineCode);


                    scheduling.setBusCode(obj.get(2) == null ? null : String.valueOf(obj.get(2)));


                    scheduling.setTcNumber(obj.get(3) == null ? null : String.valueOf(obj.get(3)));
                    scheduling.setTcTime(obj.get(4) == null ? null : String.valueOf(obj.get(4)));

                    // 找到用户编号
                    String userName = obj.get(5) == null ? null : String.valueOf(obj.get(5));
                    scheduling.setUserName(userName);
                    UserService userService = ServiceFactory.getUserService();
                    String code = userService.findCodeByName(userName);
                    scheduling.setUserCode(code);

                    // 找到站点编号
                    String stationName = obj.get(6) == null ? "" : String.valueOf(obj.get(6));
                    StationService stationService = ServiceFactory.getStationService();
                    String stationCode = stationService.findCodeByName(stationName);
                    scheduling.setStartStation(stationCode);
                    stationName = obj.get(7) == null ? "" : String.valueOf(obj.get(7));
                    stationCode = stationService.findCodeByName(stationName);
                    scheduling.setEndStation(stationCode);
                    datas.add(scheduling);
                    break;
                default:
                    break;
            }
        }
        return datas;
    }

}
