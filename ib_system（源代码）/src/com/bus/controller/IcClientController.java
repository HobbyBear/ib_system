/**
 * @Title: IcClientController.java
 * @Package com.bus.controller
 * @author yuqingming
 * @date 2020年6月8日
 * @version V1.0
 */
package com.bus.controller;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.bus.dao.model.*;
import com.bus.service.*;
import org.apache.commons.lang3.StringUtils;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.bus.constants.Constants;
import com.bus.enums.BtnEnum;
import com.bus.enums.ModuleEnum;
import com.bus.service.impl.ServiceFactory;
import com.bus.utils.CheckDataUtil;
import com.bus.utils.MD5Util;
import com.bus.utils.RegexUtil;
import com.bus.utils.TableModelUtil;
import com.bus.view.ChangePassDialog;
import com.bus.view.LoginView;
import com.bus.view.MainView;
import com.bus.view.dto.ComboBoxDto;
import com.bus.view.listener.CustomerTableModelListener;
import com.bus.view.listener.TabClickListener;

/**
 * @author yuqingming
 * @ClassName: IcClientController
 * @Description: 控制层
 * @date 2020年6月13日
 * @since JDK 1.8
 */
public class IcClientController implements ActionListener, TabClickListener {

    /**
     * @Fields loginView : 登录页面
     */
    private LoginView loginView;

    /**
     * @Fields mainView : 主页面
     */
    private MainView mainView;

    /**
     * @Fields currentModule : 当前模块
     */
    private ModuleEnum currentModule;

    /**
     * @Fields changePassDialog : 修改密码窗口
     */
    private ChangePassDialog changePassDialog;

    public IcClientController() {
        super();
        loginView = new LoginView(this);
        loginView.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 如果点击的是导航按钮
        if (e.getSource() instanceof JButton) {
            // 获取按钮对象
            JButton button = (JButton) e.getSource();
            // 获取按钮身上的文字
            String btnCommand = button.getActionCommand();
            // 判断当前按钮是否有权限，如果无权限，则提示
            if (Constants.NO_AUTHORITY_COMMAND.equals(btnCommand)) {
                JOptionPane.showMessageDialog(loginView, "无权限！", Constants.PROMPT, JOptionPane.PLAIN_MESSAGE);
                return;
            }
            // 遍历按钮，添加事件
            for (BtnEnum btnEnum : BtnEnum.values()) {
                if (btnEnum.getActionCommand().equals(btnCommand)) {
                    try {
                        clickOperate(btnEnum);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void onTabClick(ModuleEnum tabEnum) throws SQLException {
        // 重置查询条件
        mainView.resetSearchCondition();
        currentModule = tabEnum;
        doSearch();
    }

    /**
     * @param btnEnum 按钮
     * @Title: clickOperate
     * @Description: 点击按钮分发请求
     */
    private void clickOperate(BtnEnum btnEnum) throws SQLException {
        JTable table = null;
        DefaultTableModel tableModel = null;
        if (mainView != null) {
            table = mainView.getTable();
            if (table != null) {
                tableModel = (DefaultTableModel) table.getModel();
            }
        }
        switch (btnEnum) {
            // 登录
            case LOGIN:
                this.doLogin();
                break;
            // 添加
            case ADD:
                this.doAdd(tableModel, table);
                break;
            // 删除
            case DEL:
                this.doDelete(tableModel, table);
                break;
            // 保存
            case SAVE:
                this.doSave(tableModel, table);
                break;
            // 查询
            case SEARCH:
                this.doSearch();
                break;
            // 线路包含站点窗口查询
            case LINE_STATION_SEARCH:
                this.doLineStationSearch();
                break;
            // 线路包含站点窗口确认
            case LINE_STATION_CONFIRM:
                this.doLineStationConfirm();
                break;
            // 修改密码确认
            case USER_CHANGE_PASS_CONFIRM:
                this.userChangePassConfirm();
                break;
            default:
                break;
        }
    }

    /**
     * @Title: loginBtn
     * @Description: 登录逻辑
     */
    private void doLogin() throws SQLException {
        // 验证数据，如果用户名密码格式正确，发送登录请求
        if (CheckDataUtil.checkLoginData(loginView)) {
            String userName = loginView.getTxUsername().getText();
            String password = MD5Util.md5Encode(loginView.getTxPassword().getText());
            User user = new User();
            user.setLoginName(userName);
            user.setPassword(password);
            UserService service = ServiceFactory.getUserService();
            String result = service.login(user);
            // 登录成功
            if (Constants.MS0010201.equals(result)) {
                RoleService roleService = ServiceFactory.getRoleService();
                List<Role> roles = roleService.findAllRoles();
                List<ComboBoxDto> rolesDto = new ArrayList<>();
                rolesDto.add(new ComboBoxDto("-1", "全部"));
                if (roles != null && roles.size() > 0) {
                    for (Role r : roles) {
                        ComboBoxDto dto = new ComboBoxDto();
                        dto.setKey(r.getRoleCode());
                        dto.setValue(r.getRoleName());
                        rolesDto.add(dto);
                    }
                }
                // 设置窗体样式
                mainView = new MainView(this);
                // 根据登录名查找用户名
                User user1 = service.findUserByName(userName);
                // 设置用户名在主界面，显示“欢迎您”
                mainView.setUserName(user1.getName());
                mainView.setPassWord(password);
                mainView.setRoleDtos(rolesDto);
                mainView.initialize();
                mainView.setVisible(true);
                mainView.setTabClickListener(this);
                mainView.setAuthorityList(service.getUserAuthorityList(userName));
                // 关闭登录界面
                loginView.dispose();
                return;
            }
            // 密码错误
            if (Constants.MS0010203.equals(result)) {
                JOptionPane.showMessageDialog(loginView, Constants.MS0010203_MSG, Constants.PROMPT,
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            // 用户不存在或已被禁用
            if (Constants.MS0010202.equals(result)) {
                JOptionPane.showMessageDialog(loginView, Constants.MS0010202_MSG, Constants.PROMPT,
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    /**
     * @return
     * @Title: doAdd
     * @Description: 数据添加
     */
    @SuppressWarnings("rawtypes")
    private void doAdd(DefaultTableModel tableModel, JTable table) throws SQLException {
        if (table.getCellEditor() == null) {
            // 表格数据
            Vector vertor = tableModel.getDataVector();
            // 验证已存在数据
            Boolean canSave = CheckDataUtil.checkTableData(vertor, currentModule, table);
            String emptyRow[] = null;
            // 判断当前是哪个表格，新添加的默认值不同
            switch (currentModule) {
                case USER:
                    emptyRow = new String[]{"", "", "", "", "", "", "0", "启用"};
                    break;
                case BUS:
                    emptyRow = new String[]{"", "", "", "启用", ""};
                    break;
                case LINE:
                    emptyRow = new String[]{"", "", "启用", "", "请选择"};
                    break;
                default:
                    break;
            }
            // 如果已存在数据验证都通过，则才能添加一行
            if (canSave) {
                tableModel.addRow(emptyRow);
            }
        }
    }

    /**
     * @return
     * @Title: doDelete
     * @Description: 数据删除
     */
    private void doDelete(DefaultTableModel tableModel, JTable table) {
        // 为保证删除正确,需要逆序删除选中行
        CustomerTableModelListener ctl = (CustomerTableModelListener) tableModel.getTableModelListeners()[0];
        for (int i = table.getSelectedRows().length; i > 0; i--) {
            String code = (String) table.getValueAt(table.getSelectedRows()[i - 1], 0);
            if (code != null && code.length() > 0) {
                switch (currentModule) {
                    case STATION:
                        Station station = new Station();
                        station.setStationCode(code);
                        ctl.getChangeMapList().get("DELETE").add(station);
                        break;
                    case USER:
                        User user = new User();
                        user.setCode(code);
                        ctl.getChangeMapList().get("DELETE").add(user);
                        break;
                    case BUS:
                        Bus bus = new Bus();
                        bus.setBusCode(code);
                        ctl.getChangeMapList().get("DELETE").add(bus);
                        break;
                    case LINE:
                        Line line = new Line();
                        line.setLineCode(code);
                        ctl.getChangeMapList().get("DELETE").add(line);
                        break;
                    case SCHEDULING:
                        Scheduling scheduling = new Scheduling();
                        scheduling.setCode(code);
                        ctl.getChangeMapList().get("DELETE").add(scheduling);
                        break;
                    default:
                        break;
                }
            }
            tableModel.removeRow(table.getSelectedRows()[i - 1]);
        }
    }

    /**
     * @return
     * @Title: doSave
     * @Description: 数据保存
     */
    private void doSave(DefaultTableModel tableModel, JTable table) throws SQLException {
        // 如果表格中有显示在编辑的，则停止编辑
        if (table.getCellEditor() != null) {
            table.getCellEditor().stopCellEditing();
        }
        // 表格数据
        @SuppressWarnings("rawtypes")
        Vector vertor = tableModel.getDataVector();
        // 判断数据是否都验证通过
        Boolean canSave = CheckDataUtil.checkTableData(vertor, currentModule, table);
        if (canSave) {
            // 表格数据
            @SuppressWarnings("unchecked")
            List<Object> dataList = TableModelUtil.getTableDataListByModule(currentModule, vertor);
            CustomerTableModelListener ctl = (CustomerTableModelListener) tableModel.getTableModelListeners()[0];
            Map<String, List<Object>> changeDataList = ctl.getChangeMapList();
            String message = "";
            // 判断当前是哪个表格，发送不同请求码
            switch (currentModule) {
                case USER:
                    // 保存用户请求
                    UserService userService = ServiceFactory.getUserService();
                    Map<String, List<User>> userMap = new HashMap<String, List<User>>();
                    userMap.put("SAVE", new ArrayList<User>());
                    userMap.put("DELETE", new ArrayList<User>());
                    userMap.put("UPDATE", new ArrayList<User>());

                    for (Object obj : dataList) {
                        User tempUser = (User) obj;
                        if (tempUser.getCode() == null || tempUser.getCode().length() == 0) {
                            userMap.get("SAVE").add(tempUser);
                        }
                    }

                    if (changeDataList.get("DELETE").size() > 0) {
                        for (Object obj : changeDataList.get("DELETE")) {
                            userMap.get("DELETE").add((User) obj);
                        }
                    }
                    if (changeDataList.get("UPDATE").size() > 0) {
                        for (Object obj : changeDataList.get("UPDATE")) {
                            userMap.get("UPDATE").add((User) obj);
                        }
                    }
                    message = userService.save(userMap);
                    break;
                case BUS:
                    // 保存车辆请求
                    BusService busService = ServiceFactory.getBusService();
                    Map<String, List<Bus>> busMap = new HashMap<String, List<Bus>>();
                    busMap.put("SAVE", new ArrayList<Bus>());
                    busMap.put("DELETE", new ArrayList<Bus>());
                    busMap.put("UPDATE", new ArrayList<Bus>());
                    for (Object obj : dataList) {
                        Bus tempBus = (Bus) obj;
                        if (tempBus.getBusCode() == null || tempBus.getBusCode().length() == 0) {
                            busMap.get("SAVE").add(tempBus);
                        }
                    }
                    if (changeDataList.get("DELETE").size() > 0) {
                        for (Object obj : changeDataList.get("DELETE")) {
                            busMap.get("DELETE").add((Bus) obj);
                        }
                    }
                    if (changeDataList.get("UPDATE").size() > 0) {
                        for (Object obj : changeDataList.get("UPDATE")) {
                            busMap.get("UPDATE").add((Bus) obj);
                        }
                    }
                    message = busService.save(busMap);
                    break;
                case LINE:
                    // 保存线路请求
                    LineService lineService = ServiceFactory.getLineService();
                    Map<String, List<Line>> lineMap = new HashMap<String, List<Line>>();
                    lineMap.put("SAVE", new ArrayList<Line>());
                    lineMap.put("DELETE", new ArrayList<Line>());
                    lineMap.put("UPDATE", new ArrayList<Line>());
                    for (Object obj : dataList) {
                        Line tempLine = (Line) obj;
                        if (tempLine.getLineCode() == null || tempLine.getLineCode().length() == 0) {
                            lineMap.get("SAVE").add(tempLine);
                        }
                    }
                    if (changeDataList.get("DELETE").size() > 0) {
                        for (Object obj : changeDataList.get("DELETE")) {
                            lineMap.get("DELETE").add((Line) obj);
                        }
                    }
                    if (changeDataList.get("UPDATE").size() > 0) {
                        for (Object obj : changeDataList.get("UPDATE")) {
                            lineMap.get("UPDATE").add((Line) obj);
                        }
                    }
                    message = lineService.save(lineMap);
                    break;
                case STATION:
                    // 保存站点请求
                    StationService stationService = ServiceFactory.getStationService();
                    Map<String, List<Station>> stationMap = new HashMap<String, List<Station>>();
                    stationMap.put("SAVE", new ArrayList<Station>());
                    stationMap.put("DELETE", new ArrayList<Station>());
                    stationMap.put("UPDATE", new ArrayList<Station>());
                    for (Object obj : dataList) {
                        Station tempStation = (Station) obj;
                        if (tempStation.getStationCode() == null || tempStation.getStationCode().length() == 0) {
                            stationMap.get("SAVE").add(tempStation);
                        }
                    }
                    if (changeDataList.get("DELETE").size() > 0) {
                        for (Object obj : changeDataList.get("DELETE")) {
                            stationMap.get("DELETE").add((Station) obj);
                        }
                    }
                    if (changeDataList.get("UPDATE").size() > 0) {
                        for (Object obj : changeDataList.get("UPDATE")) {
                            stationMap.get("UPDATE").add((Station) obj);
                        }
                    }
                    message = stationService.save(stationMap);
                    break;
                case SCHEDULING:
                    SchedulingService schedulingService = ServiceFactory.getSchedulingService();
                    Map<String, List<Scheduling>> schedulingMap = new HashMap<String, List<Scheduling>>();
                    schedulingMap.put("SAVE", new ArrayList<Scheduling>());
                    schedulingMap.put("DELETE", new ArrayList<Scheduling>());
                    schedulingMap.put("UPDATE", new ArrayList<Scheduling>());
                    for (Object obj : dataList) {
                        Scheduling tempScheduling = (Scheduling) obj;
                        if (tempScheduling.getCode() == null || tempScheduling.getCode().length() == 0) {
                            schedulingMap.get("SAVE").add(tempScheduling);
                        }
                    }
                    if (changeDataList.get("DELETE").size() > 0) {
                        for (Object obj : changeDataList.get("DELETE")) {
                            schedulingMap.get("DELETE").add((Scheduling) obj);
                        }
                    }
                    if (changeDataList.get("UPDATE").size() > 0) {
                        for (Object obj : changeDataList.get("UPDATE")) {
                            schedulingMap.get("UPDATE").add((Scheduling) obj);
                        }
                    }
                    message = schedulingService.save(schedulingMap);
                    break;
                default:
                    break;
            }

            JOptionPane.showMessageDialog(loginView, message, Constants.PROMPT,
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    private void doSearch() throws SQLException {
        // 判断当前点击按钮的是哪个页面，判断走哪个请求code
        switch (currentModule) {
            case USER:
                // 获取查询条件
                User userCondition = (User) mainView.getSearchCondition(currentModule);
                if ("-1".equals(userCondition.getRole())) {
                    userCondition.setRole(null);
                }
                if ("2".equals(userCondition.getStatus())) {
                    userCondition.setStatus(null);
                }
                // 查询用户请求
                UserService userService = ServiceFactory.getUserService();
                List<User> userResult = userService.find(userCondition);
                mainView.changeTable(userResult, currentModule);
                break;
            case BUS:
                // 获取查询条件
                Bus busCondition = (Bus) mainView.getSearchCondition(currentModule);
                // 查询车辆请求
                BusService busService = ServiceFactory.getBusService();
                List<Bus> result = busService.find(busCondition);
                mainView.changeTable(result, currentModule);
                break;
            case LINE:
                // 获取查询条件
                Line lineCondition = (Line) mainView.getSearchCondition(currentModule);
                // 查询线路请求
                LineService lineService = ServiceFactory.getLineService();
                List<Line> lineResult = lineService.find(lineCondition);
                mainView.changeTable(lineResult, currentModule);
                break;
            case STATION:
                // 获取查询条件
                Station stationCondition = (Station) mainView.getSearchCondition(currentModule);
                // 查询站点请求
                StationService stationService = ServiceFactory.getStationService();
                List<Station> stationResult = stationService.find(stationCondition);
                mainView.changeTable(stationResult, currentModule);
                break;
            case SCHEDULING:
                Scheduling schedulingCondition = (Scheduling) mainView.getSearchCondition(currentModule);
                SchedulingService schedulingService = ServiceFactory.getSchedulingService();
                List<Scheduling> schedulingResut = schedulingService.find(schedulingCondition);
                mainView.changeTable(schedulingResut, currentModule);
                break;
            default:
                break;
        }
    }

    private void doLineStationSearch() throws SQLException {
        // 获取查询条件
        Station conditionStation = mainView.getLineStationsDialog().getSearchCondition();
        List<Station> list = ServiceFactory.getStationService().find(conditionStation);
        mainView.getLineStationsDialog().setStationList(list);
    }

    private void doLineStationConfirm() {
        String stationResult = mainView.getLineStationsDialog().getSelectedStations();
        mainView.setCurrentSelectedStations(stationResult);
        mainView.getLineStationsDialog().dispose();
    }

    private void userChangePassConfirm() throws SQLException {
        // 修改密码
        changePassDialog = mainView.getChangePassDialog();

        String oldPassword = changePassDialog.getOldPassword();
        String confirmPassword = changePassDialog.getConfirmPassword();
        String newPassword = changePassDialog.getNewPassword();
        String userName = mainView.getUserName();

        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(loginView, "两次密码不一致，请修改！", Constants.PROMPT, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!MD5Util.md5Encode(oldPassword).equals(mainView.getPassWord())) {
            JOptionPane.showMessageDialog(loginView, "旧密码错误，请修改！", Constants.PROMPT, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (StringUtils.isBlank(newPassword)) {
            JOptionPane.showMessageDialog(loginView, "密码不能为空，请修改！", Constants.PROMPT, JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (RegexUtil.checkReg(newPassword, RegexUtil.REG_PASSWORD)) {
            JOptionPane.showMessageDialog(loginView, "密码格式错误！！5-16位非空字符", Constants.PROMPT, JOptionPane.ERROR_MESSAGE);
            return;
        }
        // 请求修改密码
        UserService userService = ServiceFactory.getUserService();
        userService.changePass(userName, MD5Util.md5Encode(newPassword));
        mainView.setPassWord(MD5Util.md5Encode(newPassword));
        JOptionPane.showMessageDialog(mainView, "密码修改成功", Constants.PROMPT, JOptionPane.PLAIN_MESSAGE);
        changePassDialog.dispose();
    }

    static {
        /* UIManager中UI字体相关的key */
        String[] defaultFont = new String[]{"Table.font", "TableHeader.font", "CheckBox.font", "Tree.font",
                "Viewport.font", "ProgressBar.font", "RadioButtonMenuItem.font", "ToolBar.font", "ColorChooser.font",
                "ToggleButton.font", "Panel.font", "TextArea.font", "Menu.font", "TableHeader.font"
                // ,"TextField.font"
                , "OptionPane.font", "MenuBar.font", "Button.font", "Label.font", "PasswordField.font",
                "ScrollPane.font", "MenuItem.font", "ToolTip.font", "List.font", "EditorPane.font", "Table.font",
                "TabbedPane.font", "RadioButton.font", "CheckBoxMenuItem.font", "TextPane.font", "PopupMenu.font",
                "TitledBorder.font", "ComboBox.font"};
        // 调整默认字体
        for (int i = 0; i < defaultFont.length; i++) {
            UIManager.put(defaultFont[i], new Font("微软雅黑", Font.PLAIN, 14));
        }
        try {
            UIManager.put("RootPane.setupButtonVisible", false);
            // 设置本属性将改变窗口边框样式定义
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //new IcClientController();
    }
}
