/**
 * @Title: MainView.java
 * @Package com.bus.view
 * @author yuqingming
 * @date 2020年6月8日
 * @version V1.0
 */
package com.bus.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.bus.constants.Constants;
import com.bus.dao.model.Bus;
import com.bus.dao.model.Line;
import com.bus.dao.model.Scheduling;
import com.bus.dao.model.Station;
import com.bus.dao.model.User;
import com.bus.enums.BtnEnum;
import com.bus.enums.ColorEnum;
import com.bus.enums.ImageEnum;
import com.bus.enums.ModuleEnum;
import com.bus.enums.TextEnum;
import com.bus.service.LineService;
import com.bus.service.StationService;
import com.bus.service.impl.ServiceFactory;
import com.bus.utils.TableModelUtil;
import com.bus.view.dto.ComboBoxDto;
import com.bus.view.listener.TabClickListener;

/**
 * @ClassName: Main
 * @Description: 主页面
 * @author yuqingming
 * @date 2020年6月8日
 * @since JDK 1.8
 */
public class MainView extends JFrame {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * @Fields currentModule : 当前所在的功能模块
     */
    private ModuleEnum currentModule;

    /**
     * @Fields table : 业务数据表格
     */
    private JTable table;

    /**
     * @Fields actionListener : 按钮动作监听
     */
    private ActionListener actionListener;

    /**
     * @Fields SCREEN_WIDTH : 屏幕宽度
     */
    private final static int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    /**
     * @Fields SCREEN_HEIGHT : 屏幕高度
     */
    private final static int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    /**
     * @Fields topPanel : 顶部面板
     */
    private JPanel topPanel;
    /**
     * @Fields topMenu : 顶部菜单
     */
    private JPanel topMenu;
    /**
     * @Fields topPrompt : 顶部欢迎面板
     */
    private JPanel topPrompt;
    /**
     * @Fields backgroundPanel : 背景面板
     */
    private JPanel backgroundPanel;
    /**
     * @Fields centerPanel : 中间内容面板
     */
    private JPanel centerPanel;

    /**
     * @Fields menuHome : 主页菜单
     */
    private JLabel menuHome;
    /**
     * @Fields menuBaseData : 基础数据菜单
     */
    private JLabel menuBaseData;
    /**
     * @Fields menuScheduling : 排班菜单
     */
    private JLabel menuScheduling;
    /**
     * @Fields menuUserManager : 用户菜单
     */
    private JLabel menuUserManager;

    /**
     * @Fields currentMenuLabel : 当前选择菜单
     */
    private JLabel currentMenuLabel;

    /**
     * @Fields currentTabbedPane : 当前页选项卡
     */
    private JTabbedPane currentTabbedPane;

    /**
     * @Fields currentScrollPane : 当前页滚动面板
     */
    private JScrollPane currentScrollPane;

    /**
     * @Fields tabClickListener : 选项卡点击监听
     */
    private TabClickListener tabClickListener;

    /**
     * @Fields conditionUserName : 用户查询条件-用户名
     */
    private SearchConditionPanel conditionUserName;
    /**
     * @Fields conditionUserRole : 用户查询条件-角色
     */
    private SearchConditionPanel conditionUserRole;
    /**
     * @Fields conditionUserStatus : 用户查询条件-状态
     */
    private SearchConditionPanel conditionUserStatus;

    /**
     * @Fields conditionBusLicense : 车辆查询条件-车牌号
     */
    private SearchConditionPanel conditionBusLicense;
    /**
     * @Fields conditionBusStatus : 车辆查询条件-车辆状态
     */
    private SearchConditionPanel conditionBusStatus;

    /**
     * @Fields conditionLineName : 线路查询条件-线路名称
     */
    private SearchConditionPanel conditionLineName;
    /**
     * @Fields conditionLineDirection : 线路查询条件-线路方向
     */
    private SearchConditionPanel conditionLineDirection;
    /**
     * @Fields conditionLineStatus : 线路查询条件-线路状态
     */
    private SearchConditionPanel conditionLineStatus;

    /**
     * @Fields conditionStationName : 站点查询条件-站点名称
     */
    private SearchConditionPanel conditionStationName;
    /**
     * @Fields conditionStationRegion : 站点查询条件-所在区域
     */
    private SearchConditionPanel conditionStationRegion;
    /**
     * @Fields conditionStationStreet : 站点查询条件-所在街道
     */
    private SearchConditionPanel conditionStationStreet;

    /**
     * @Fields conditionSchedulingBusCode : 排班查询条件-车牌号
     */
    private SearchConditionPanel conditionSchedulingBusCode;
    /**
     * @Fields conditionSchedulingLineName : 排班查询条件-线路名称
     */
    private SearchConditionPanel conditionSchedulingLineName;
    /**
     * @Fields conditionSchedulingStartStation : 排班查询条件-始发站
     */
    private SearchConditionPanel conditionSchedulingStartStation;
    /**
     * @Fields conditionSchedulingEndStation : 排班查询条件-终点站
     */
    private SearchConditionPanel conditionSchedulingEndStation;

    /**
     * @Fields lineStationsDialog : 选择线路包含车站窗口
     */
    private LineStationsDialog lineStationsDialog;

    /**
     * @Fields containStations : 当前编辑的包含车站
     */
    private JTextField containStations;

    /**
     * @Fields changePassDialog : 更改密码窗口
     */
    private ChangePassDialog changePassDialog;

    /**
     * @Fields userName : 登陆后显示的用户名
     */
    private String userName = "";

    /**
     * @Fields userName : 密码
     */
    private String passWord = "";

    private String userCode = "";

    /**
     * @Fields authorityList : 登陆用户所具有的权限列表
     */
    private List<String> authorityList = null;

    /**
     * @Fields roleDtos : 系统中的角色列表
     */
    private List<ComboBoxDto> roleDtos;

    /**
     * 创建一个新的实例 MainView.
     *
     * @param actionListener
     */
    public MainView(ActionListener actionListener) {
        new WindowOpacity(this);
        this.actionListener = actionListener;
    }

    /**
     * @Title: initialize
     * @Description: 初始化窗体
     */
    public void initialize() {
        // 设置tab面板缩进
        UIManager.put("TabbedPane.tabAreaInsets", new javax.swing.plaf.InsetsUIResource(0, 0, 0, 0));
        initBackgroundPanel();
        this.setSize((int) (SCREEN_WIDTH * 0.6f), (int) (SCREEN_HEIGHT * 0.6f));
        this.setMinimumSize(new Dimension((int) (SCREEN_WIDTH * 0.4f), (int) (SCREEN_HEIGHT * 0.4f)));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(TextEnum.TITLE_MAIN.getName());
        this.setIconImage(ImageEnum.ICON_TITLE.getIcon().getImage());

        //用户查询条件
        conditionUserName = new SearchConditionPanel(TextEnum.LABEL_USERNAME.getName());
        List<ComboBoxDto> datas_user = new ArrayList<>();
        datas_user.add(new ComboBoxDto("2", "全部"));
        datas_user.add(new ComboBoxDto("1", "启用"));
        datas_user.add(new ComboBoxDto("0", "禁用"));

        conditionUserStatus = new SearchConditionPanel(TextEnum.LABLE_USERSTATUS.getName(), datas_user);
        conditionUserRole = new SearchConditionPanel(TextEnum.LABLE_USERROLE.getName(), roleDtos);

        // 车辆查询条件
        conditionBusLicense = new SearchConditionPanel(TextEnum.LABEL_BUSLICENSE.getName());
        List<ComboBoxDto> datas_bus = new ArrayList<>();
        datas_bus.add(new ComboBoxDto("2", "全部"));
        datas_bus.add(new ComboBoxDto("1", "启用"));
        datas_bus.add(new ComboBoxDto("0", "停运"));
        conditionBusStatus = new SearchConditionPanel(TextEnum.LABEL_BUSSTATUS.getName(), datas_bus);

        //线路查询条件
        conditionLineName = new SearchConditionPanel(TextEnum.LABEL_LINENAME.getName());
        List<ComboBoxDto> datas_line = new ArrayList<>();
        datas_line.add(new ComboBoxDto("2", "全部"));
        datas_line.add(new ComboBoxDto("1", "启用"));
        datas_line.add(new ComboBoxDto("0", "停运"));
        conditionLineStatus = new SearchConditionPanel(TextEnum.LABEL_LINESTATUS.getName(), datas_line);
        List<ComboBoxDto> datas2 = new ArrayList<>();
        datas2.add(new ComboBoxDto("2", "全部"));
        datas2.add(new ComboBoxDto("1", "上行"));
        datas2.add(new ComboBoxDto("0", "下行"));
        conditionLineDirection = new SearchConditionPanel(TextEnum.LABEL_LINEDIRECTION.getName(), datas2);

        //站点查询条件
        conditionStationName = new SearchConditionPanel(TextEnum.LABEL_STATIONNAME.getName());
        conditionStationRegion = new SearchConditionPanel(TextEnum.LABEL_STATIONREGION.getName());
        conditionStationStreet = new SearchConditionPanel(TextEnum.LABEL_STATIONSTREET.getName());

        // 排班查询条件
        conditionSchedulingBusCode = new SearchConditionPanel(TextEnum.LABEL_BUSLICENSE.getName());
        conditionSchedulingLineName = new SearchConditionPanel(TextEnum.LABEL_LINENAME.getName());
        conditionSchedulingStartStation = new SearchConditionPanel(TextEnum.LABEL_SCHEDULING_STARTSTATION.getName());
        conditionSchedulingEndStation = new SearchConditionPanel(TextEnum.LABEL_SCHEDULING_ENDSTATION.getName());
    }

    /**
     * 初始化背景面板
     * @Title: initBackgroundPanel
     * @Description: 初始化背景面板
     */
    public void initBackgroundPanel() {

        backgroundPanel = new JPanel(new BorderLayout());
        initTop();
        initCenterPanel();

        backgroundPanel.add(topPanel, BorderLayout.NORTH);
        backgroundPanel.add(centerPanel, BorderLayout.CENTER);

        this.add(backgroundPanel);
    }

    /**
     *
     * @Title: initCenterPanel
     * @Description: TODO(初始化主页以及创建主页内容)
     */
    public void initCenterPanel() {
        centerPanel = new JPanel(new BorderLayout());
        menuHome.setText("首页");
        menuHome.setForeground(ColorEnum.MENU_ON.getColor());
        Font font = menuHome.getFont();
        Font newFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
        menuHome.setFont(newFont);
        creatHome();
        centerPanel.setOpaque(false);
    }

    /**
     * @Title: creatHome
     * @Description: 创建主页内容
     */
    public void creatHome() {
        centerPanel.removeAll();
        final ImagePanel centerBackground = new ImagePanel(ImageEnum.HOME_BG.getIcon().getImage());
        centerPanel.add(centerBackground, BorderLayout.CENTER);

        centerBackground.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentResized(final ComponentEvent e1) {
                centerBackground.removeAll();
                centerBackground.setLayout(null);
                JPanel centerImagePanel = new JPanel();
                int width = centerPanel.getWidth() / 2;
                int height = centerPanel.getHeight() / 2;

                centerImagePanel.setBounds(width / 2, height / 2, width, height);
                centerImagePanel.setOpaque(false);

                centerImagePanel.setLayout(new GridLayout(0, 3, 20, 20));
                // 六个功能panel
                JPanel userManagerPanel = createMainButton(ModuleEnum.USER.getName(), ModuleEnum.USER.name(), ColorEnum.MAIN_USER_PANEL.getColor());
                JPanel linePanel = createMainButton(ModuleEnum.LINE.getName(), ModuleEnum.LINE.name(), ColorEnum.MAIN_LINE_PANEL.getColor());
                JPanel busPanel = createMainButton(ModuleEnum.BUS.getName(), ModuleEnum.BUS.name(), ColorEnum.MAIN_BUS_PANEL.getColor());
                JPanel stationPanel = createMainButton(ModuleEnum.STATION.getName(), ModuleEnum.STATION.name(), ColorEnum.MAIN_STATION_PANEL.getColor());
                JPanel schedulingPanel = createMainButton(ModuleEnum.SCHEDULING.getName(), ModuleEnum.SCHEDULING.name(), ColorEnum.MAIN_SCHEDULING_PANEL.getColor());
                JPanel changePassPanel = createMainButton(BtnEnum.USER_CHANGE_PASS.getName(), BtnEnum.USER_CHANGE_PASS.name(), ColorEnum.MAIN_CHANGE_PASS_PANEL.getColor());
                centerImagePanel.add(busPanel);
                centerImagePanel.add(stationPanel);
                centerImagePanel.add(linePanel);
                centerImagePanel.add(schedulingPanel);
                centerImagePanel.add(userManagerPanel);
                centerImagePanel.add(changePassPanel);
                centerBackground.add(centerImagePanel);
                centerBackground.updateUI();
                // panel的点击事件
                userManagerPanel.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // 验证登录用户是否有此模块权限，如果没有，提示
                        System.out.println("点击了：员工信息管理");
                        if (authorityList.contains(((JPanel) e.getSource()).getName())) {
                            try {
                                createUserManagerDataTab();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            topPanel.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(MainView.this, "无权限！", Constants.PROMPT, JOptionPane.PLAIN_MESSAGE);
                            return;
                        }
                    }

                });
                linePanel.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // 验证登录用户是否有此模块权限，如果没有，提示
                        System.out.println("点击了：线路信息管理");
                        if (authorityList.contains(((JPanel) e.getSource()).getName())) {
                            createBaseDataTab();
                            topPanel.setVisible(true);
                            currentTabbedPane.setSelectedIndex(2);
                        } else {
                            JOptionPane.showMessageDialog(MainView.this, "无权限！", Constants.PROMPT, JOptionPane.PLAIN_MESSAGE);
                            return;
                        }
                    }

                });
                busPanel.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // 验证登录用户是否有此模块权限，如果没有，提示
                        System.out.println("点击了：车辆信息管理");
                        if (authorityList.contains(((JPanel) e.getSource()).getName())) {
                            createBaseDataTab();
                            topPanel.setVisible(true);
                            currentTabbedPane.setSelectedIndex(0);
                        } else {
                            JOptionPane.showMessageDialog(MainView.this, "无权限！", Constants.PROMPT, JOptionPane.PLAIN_MESSAGE);
                            return;
                        }
                    }
                });
                stationPanel.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // 验证登录用户是否有此模块权限，如果没有，提示
                        System.out.println("点击了：站点信息管理");
                        if (authorityList.contains(((JPanel) e.getSource()).getName())) {
                            createBaseDataTab();
                            topPanel.setVisible(true);
                            currentTabbedPane.setSelectedIndex(1);
                        } else {
                            JOptionPane.showMessageDialog(MainView.this, "无权限！", Constants.PROMPT, JOptionPane.PLAIN_MESSAGE);
                            return;
                        }
                    }
                });
                schedulingPanel.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // 验证登录用户是否有此模块权限，如果没有，提示
                        System.out.println("点击了：排班信息管理");
                        if (authorityList.contains(((JPanel) e.getSource()).getName())) {
                            try {
                                createSchedulingDataTab();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            topPanel.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(MainView.this, "无权限！", Constants.PROMPT, JOptionPane.PLAIN_MESSAGE);
                            return;
                        }
                    }

                });
                changePassPanel.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // 验证登录用户是否有此模块权限，如果没有，提示
                        System.out.println("点击了：修改密码");
                        if (authorityList.contains(((JPanel) e.getSource()).getName())) {
                            changePassDialog = new ChangePassDialog(MainView.this, true, actionListener);
                            changePassDialog.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(MainView.this, "无权限！", Constants.PROMPT, JOptionPane.PLAIN_MESSAGE);
                            return;
                        }
                    }
                });
            }
        });

    }

    /**
     * 初始化顶部面板
     * @Title: initTop
     * @Description: 初始化顶部面板
     */
    public void initTop() {

        initTopMenu();
        initTopPrompt();

        topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, 40));
        topPanel.setVisible(false);

        topPanel.add(topMenu, BorderLayout.WEST);
        topPanel.add(topPrompt, BorderLayout.EAST);
    }

    /**
     * @Title: initTopMenu
     * @Description: 初始化顶部菜单
     */
    public void initTopMenu() {
        topMenu = new JPanel();
        topMenu.setPreferredSize(new Dimension(500, 40));
        topMenu.setOpaque(false);

        menuHome = createMenuLabel(menuHome, "首页", "home", topMenu);
        menuBaseData = createMenuLabel(menuBaseData, "基础数据", "base_data", topMenu);
        menuScheduling = createMenuLabel(menuScheduling, "排班管理", "scheduling", topMenu);
        menuUserManager = createMenuLabel(menuUserManager, "用户管理", "user_manager", topMenu);
        currentMenuLabel = menuHome;
    }

    /**
     * 初始化顶部欢迎
     * @Title: initTopPrompt
     * @Description: 初始化顶部欢迎
     */
    public void initTopPrompt() {
        // 头部显示登陆人
        Icon icon = new ImageIcon("img/male.png");
        JLabel label = new JLabel(icon);
        label.setText("欢迎您，" + userName);
        topPrompt = new JPanel();
        topPrompt.setPreferredSize(new Dimension(180, 40));
        topPrompt.setOpaque(false);
        topPrompt.add(label);

    }

    /**
     *
     * @Title: createMenuLabel
     * @Description: 创建菜单
     * @param label 菜单
     * @param text 菜单文字
     * @param name 菜单名称
     * @param who 添加目标
     * @return 添加的label
     */
    public JLabel createMenuLabel(JLabel label, String text, String name, final JPanel who) {
        Icon icon = new ImageIcon("img/menu_" + name + ".png");
        label = new JLabel(icon);
        label.setName(name);
        label.setText(text);
        Font font = label.getFont();
        Font newFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
        label.setFont(newFont);

        label.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() != currentMenuLabel) {
                    JLabel current = (JLabel) e.getSource();
                    current.setForeground(ColorEnum.MENU_OUT.getColor());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JLabel current = (JLabel) e.getSource();
                current.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                current.setForeground(ColorEnum.MENU_ON.getColor());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                currentMenuLabel.setForeground(ColorEnum.MENU_OUT.getColor());
                currentMenuLabel = (JLabel) e.getSource();
                currentMenuLabel.setForeground(ColorEnum.MENU_ON.getColor());
                if (e.getSource() == menuHome) {
                    creatHome();
                    topPanel.setVisible(false);
                } else {
                    topPanel.setVisible(true);
                }

                if (e.getSource() == menuHome) {
                    creatHome();
                } else if (e.getSource() == menuBaseData) {
                    if (authorityList.contains(menuBaseData.getName())) {
                        createBaseDataTab();
                        currentTabbedPane.setSelectedIndex(0);
                    } else {
                        JOptionPane.showMessageDialog(MainView.this, "无权限！", Constants.PROMPT, JOptionPane.PLAIN_MESSAGE);
                        return;
                    }
                } else if (e.getSource() == menuScheduling) {
                    if (authorityList.contains(menuScheduling.getName())) {
                        try {
                            createSchedulingDataTab();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(MainView.this, "无权限！", Constants.PROMPT, JOptionPane.PLAIN_MESSAGE);
                        return;
                    }
                } else if (e.getSource() == menuUserManager) {
                    if (authorityList.contains(menuUserManager.getName())) {
                        try {
                            createUserManagerDataTab();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(MainView.this, "无权限！", Constants.PROMPT, JOptionPane.PLAIN_MESSAGE);
                        return;
                    }
                }

            }
        });
        who.setLayout(new GridLayout(1, 4));
        who.add(label);
        return label;
    }

    /**
     * @Title: createMainButton
     * @Description: 创建主页面功能panel
     * @param text 文字
     * @param bgColor 背景颜色
     * @return 创建的面板对象
     */
    private JPanel createMainButton(String text, String name, Color bgColor) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setForeground(new Color(230, 230, 230));
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        panel.add(label);
        panel.setBackground(bgColor);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JPanel panel = (JPanel) e.getSource();
                panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        panel.setName(name.toLowerCase() + "_home_menu");
        return panel;
    }

    /**
     * @Title: createBaseDataTab
     * @Description: 创建基础数据面板
     */
    public void createBaseDataTab() {
        centerPanel.removeAll();
        // 设置tab标题位置
        currentTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        // 设置tab布局
        currentTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        final JScrollPane busSp = new JScrollPane();
        busSp.setAutoscrolls(true);
        final JScrollPane stationSp = new JScrollPane();
        stationSp.setAutoscrolls(true);
        final JScrollPane lineSp = new JScrollPane();
        lineSp.setAutoscrolls(true);
        // 添加车辆管理面板
        currentTabbedPane.addTab(ModuleEnum.BUS.getName(),
                createTabContent(busSp, new BtnEnum[]{BtnEnum.ADD, BtnEnum.DEL, BtnEnum.SAVE},
                        new SearchConditionPanel[]{conditionBusLicense, conditionBusStatus}, ModuleEnum.BUS));
        // 添加站点管理面板
        currentTabbedPane.addTab(ModuleEnum.STATION.getName(),
                createTabContent(stationSp, new BtnEnum[]{BtnEnum.ADD, BtnEnum.DEL, BtnEnum.SAVE},
                        new SearchConditionPanel[]{conditionStationName, conditionStationRegion, conditionStationStreet}, ModuleEnum.STATION));
        // 添加线路管理面板
        currentTabbedPane.addTab(ModuleEnum.LINE.getName(),
                createTabContent(lineSp, new BtnEnum[]{BtnEnum.ADD, BtnEnum.DEL, BtnEnum.SAVE},
                        new SearchConditionPanel[]{conditionLineName, conditionLineDirection, conditionLineStatus}, ModuleEnum.LINE));
        currentTabbedPane.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
                int selectedIndex = tabbedPane.getSelectedIndex();
                switch (selectedIndex) {
                    case 0:
                        currentModule = ModuleEnum.BUS;
                        currentScrollPane = busSp;
                        break;
                    case 1:
                        currentModule = ModuleEnum.STATION;
                        currentScrollPane = stationSp;
                        break;
                    case 2:
                        currentModule = ModuleEnum.LINE;
                        currentScrollPane = lineSp;
                        break;
                    default:
                        return;
                }
                try {
                    tabClickListener.onTabClick(currentModule);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        centerPanel.add(currentTabbedPane, BorderLayout.CENTER);
        currentTabbedPane.setSelectedIndex(-1);
        centerPanel.updateUI();
    }

    /**
     * @Title: createSchedulingDataTab
     * @Description: 创建排班数据面板
     */
    public void createSchedulingDataTab() throws SQLException {
        centerPanel.removeAll();
        // 设置tab标题位置
        currentTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        // 设置tab布局
        currentTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        final JScrollPane schedulingSp = new JScrollPane();
        schedulingSp.setAutoscrolls(true);
        // 添加排班管理面板
        currentTabbedPane.addTab(ModuleEnum.SCHEDULING.getName(),
                createTabContent(schedulingSp,
                        new BtnEnum[]{BtnEnum.ADD, BtnEnum.DEL, BtnEnum.SAVE},
                        new SearchConditionPanel[]{conditionSchedulingBusCode, conditionSchedulingLineName, conditionSchedulingStartStation, conditionSchedulingEndStation}, ModuleEnum.SCHEDULING));
        centerPanel.add(currentTabbedPane, BorderLayout.CENTER);
        currentScrollPane = schedulingSp;
        currentModule = ModuleEnum.SCHEDULING;
        tabClickListener.onTabClick(currentModule);
        centerPanel.updateUI();
    }

    /**
     * @Title: createUserManagerDataTab
     * @Description: 创建用户管理面板
     */
    public void createUserManagerDataTab() throws SQLException {
        centerPanel.removeAll();
        // 设置tab标题位置
        currentTabbedPane = new JTabbedPane(JTabbedPane.TOP);
        // 设置tab布局
        currentTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        final JScrollPane userManagerSp = new JScrollPane();
        userManagerSp.setAutoscrolls(true);
        // 添加排班管理面板
        currentTabbedPane.addTab(ModuleEnum.USER.getName(),
                createTabContent(userManagerSp, new BtnEnum[]{BtnEnum.ADD, BtnEnum.DEL, BtnEnum.SAVE},
                        new SearchConditionPanel[]{conditionUserName, conditionUserRole, conditionUserStatus}, ModuleEnum.USER));
        centerPanel.add(currentTabbedPane, BorderLayout.CENTER);
        currentScrollPane = userManagerSp;
        currentModule = ModuleEnum.USER;
        // 调试1
        tabClickListener.onTabClick(currentModule);
        centerPanel.updateUI();

    }

    /**
     * @Title: createTabContent
     * @Description: 创建tab页内容面板
     * @param sp 滚动面板
     * @param buttons 功能按钮
     * @param conditions 查询条件
     * @param //isClick 加载页面按钮是否可点击
     * @return 创建的tab页内容面板
     */
    private JPanel createTabContent(JScrollPane sp, BtnEnum[] buttons, SearchConditionPanel[] conditions, ModuleEnum moduleEnum) {
        // tab页面板
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        // 上方查询条件和操作按钮面板
        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new BorderLayout());
        // 按钮面板
        JPanel buttonPanel = new JPanel();
        // 查询条件面板
        JPanel conditionPanel = new JPanel();
        toolbarPanel.add(buttonPanel, BorderLayout.WEST);
        toolbarPanel.add(conditionPanel, BorderLayout.EAST);
        // 添加操作按钮
        for (BtnEnum btnEnum : buttons) {
            JButton jButton = new JButton(btnEnum.getName());
            if (authorityList.contains(moduleEnum.name().toLowerCase() + "_" + btnEnum.getActionCommand())) {
                jButton.setActionCommand(btnEnum.getActionCommand());
            } else {
                jButton.setActionCommand(Constants.NO_AUTHORITY_COMMAND);
            }
            jButton.addActionListener(actionListener);
            buttonPanel.add(jButton);
        }
        // 添加查询条件
        for (SearchConditionPanel condition : conditions) {
            conditionPanel.add(condition);
        }
        JButton searchButton = new JButton(BtnEnum.SEARCH.getName());
        conditionPanel.add(searchButton);
        searchButton.setActionCommand(BtnEnum.SEARCH.getActionCommand());
        searchButton.addActionListener(actionListener);

        contentPanel.add(toolbarPanel, BorderLayout.NORTH);
        contentPanel.add(sp, BorderLayout.CENTER);
        return contentPanel;
    }

    /**
     * @Title: changeTable
     * @Description: 改变jscrollPane中的table
     * @param moduleEnum 菜单枚举
     */
    public void changeTable(List<?> data, ModuleEnum moduleEnum) throws SQLException {
        currentModule = moduleEnum;
        // 声明并实例化默认的模型及表格
        table = TableModelUtil.getTableByModule(moduleEnum, data, new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {

            }

            @Override
            public void focusGained(FocusEvent e) {
                containStations = (JTextField) e.getSource();
                // 包含线路单元格获取焦点时如果有选中行打开弹窗
                if (table.getSelectedRows() != null && table.getSelectedRows().length != 0) {
                    String currentStations = (String) table.getValueAt(table.getSelectedRow(), Constants.LINESTATIONS_COLUMN_SHOW);
                    lineStationsDialog = new LineStationsDialog(MainView.this, true, actionListener, currentStations);
                    lineStationsDialog.setVisible(true);
                } else {
                    // 没有选中行则结束编辑
                    table.getCellEditor().stopCellEditing();
                }
                table.clearSelection();
            }
        }, this.roleDtos);
        // 设置table(如果不用构造方式添加table,则需要用此方式添加.add()不好用)
        currentScrollPane.setViewportView(table);

    }

    /**
     * @Title: getSearchCondition
     * @Description: 获取查询条件方法
     * @param moduleEnum 当前模块名
     * @return 含查询条件的实体
     */
    public Object getSearchCondition(ModuleEnum moduleEnum) throws SQLException {
        switch (moduleEnum) {
            case USER:
                User user = new User();
                user.setName(conditionUserName.getCondition());
                user.setRole(conditionUserRole.getCondition());
                user.setStatus(conditionUserStatus.getCondition());
                return user;
            case BUS:
                Bus bus = new Bus();
                String busLicense = conditionBusLicense.getCondition();
                String busStatus = conditionBusStatus.getCondition();
                bus.setBusLicense(busLicense);
                bus.setBusStatus(busStatus);
                return bus;
            case LINE:
                Line line = new Line();
                String lineName = conditionLineName.getCondition();
                String lineStatus = conditionLineStatus.getCondition();
                String lineDirection = conditionLineDirection.getCondition();
                line.setLineName(lineName);
                line.setStatus(lineStatus);
                line.setDirection(lineDirection);
                return line;
            case STATION:
                Station station = new Station();
                station.setStationName(conditionStationName.getCondition());
                station.setRegion(conditionStationRegion.getCondition());
                station.setStreet(conditionStationStreet.getCondition());
                return station;
            case SCHEDULING:
                Scheduling scheduling = new Scheduling();
                scheduling.setBusCode(conditionSchedulingBusCode.getCondition());
                scheduling.setLineName(conditionSchedulingLineName.getCondition());
                LineService lineService = ServiceFactory.getLineService();
                String lineCode = lineService.findCodeByName(scheduling.getLineName());
                scheduling.setLineCode(lineCode);
                scheduling.setStartStation(conditionSchedulingStartStation.getCondition());
                scheduling.setEndStation(conditionSchedulingEndStation.getCondition());
                StationService stationService = ServiceFactory.getStationService();
                String stationCode = stationService.findCodeByName(scheduling.getStartStation());
                scheduling.setStartStation(stationCode);
                stationCode = stationService.findCodeByName(scheduling.getEndStation());
                scheduling.setEndStation(stationCode);
                return scheduling;
            default:
                return null;
        }
    }

    /**
     * @Title: resetSearchCondition
     * @Description: 重置查询条件
     */
    public void resetSearchCondition() {
        //用户查询条件
        conditionUserName.reset();
        conditionUserStatus.reset();
        conditionUserRole.reset();
        // 车辆查询条件
        conditionBusLicense.reset();
        conditionBusStatus.reset();
        //线路查询条件
        conditionLineName.reset();
        conditionLineStatus.reset();
        conditionLineDirection.reset();
        //站点查询条件
        conditionStationName.reset();
        conditionStationRegion.reset();
        conditionStationStreet.reset();
        //站点查询条件
        conditionSchedulingBusCode.reset();
        conditionSchedulingLineName.reset();
        conditionSchedulingStartStation.reset();
        conditionSchedulingEndStation.reset();
        // 排班查询条
        conditionSchedulingBusCode.reset();
        conditionSchedulingLineName.reset();
        conditionSchedulingStartStation.reset();
        conditionSchedulingEndStation.reset();
    }

    /**
     * @Title: getTableSelectedRow
     * @Description: 获取表格中选中的行
     * @param moduleEnum 模块
     * @param tableModel 表格model对象
     * @return 选中行数据
     */
    @SuppressWarnings("rawtypes")
    public Object getTableSelectedRow(ModuleEnum moduleEnum, DefaultTableModel tableModel) {
        int[] selected = table.getSelectedRows();
        if (selected == null || selected.length == 0) {
            JOptionPane.showMessageDialog(this, "请选择一条数据再进行写卡操作！", Constants.PROMPT, JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (selected.length > 1) {
            JOptionPane.showMessageDialog(this, "只能选择一条数据！", Constants.PROMPT, JOptionPane.ERROR_MESSAGE);
            return null;
        }
        int selectedRowIndex = selected[0];
        switch (moduleEnum) {
            case SCHEDULING:
                Scheduling selectedScheduling = new Scheduling();
                Vector dataVector = tableModel.getDataVector();
                Vector rowVector = (Vector) dataVector.get(selectedRowIndex);
                selectedScheduling.setCode(rowVector.get(0) == null ? null : String.valueOf(rowVector.get(0)));
                selectedScheduling.setLineName(rowVector.get(1) == null ? null : String.valueOf(rowVector.get(1)));
                selectedScheduling.setBusCode(rowVector.get(2) == null ? null : String.valueOf(rowVector.get(2)));
                selectedScheduling.setTcNumber(rowVector.get(3) == null ? null : String.valueOf(rowVector.get(3)));
                selectedScheduling.setTcTime(rowVector.get(4) == null ? null : String.valueOf(rowVector.get(4)));
                selectedScheduling.setUserName(rowVector.get(5) == null ? null : String.valueOf(rowVector.get(5)));
                selectedScheduling.setStartStation(rowVector.get(6) == null ? null : String.valueOf(rowVector.get(6)));
                selectedScheduling.setEndStation(rowVector.get(7) == null ? null : String.valueOf(rowVector.get(7)));
                selectedScheduling.setLineCode(rowVector.get(8) == null ? null : String.valueOf(rowVector.get(8)));
                selectedScheduling.setUserCode(rowVector.get(9) == null ? null : String.valueOf(rowVector.get(9)));
                return selectedScheduling;
            default:
                return null;
        }
    }

    /**
     * @Title: setCurrentSelectedStations
     * @Description: 设置当前编辑的线路-包含车站
     * @param result
     */
    public void setCurrentSelectedStations(String result) {
        containStations.setText(lineStationsDialog.getSelectedStations());
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public ModuleEnum getCurrentModule() {
        return currentModule;
    }

    public TabClickListener getTabClickListener() {
        return tabClickListener;
    }

    public void setTabClickListener(TabClickListener tabClickListener) {
        this.tabClickListener = tabClickListener;
    }

    public LineStationsDialog getLineStationsDialog() {
        return lineStationsDialog;
    }

    public ChangePassDialog getChangePassDialog() {
        return changePassDialog;
    }

    public List<String> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<String> authorityList) {
        this.authorityList = authorityList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public List<ComboBoxDto> getRoleDtos() {
        return roleDtos;
    }

    public void setRoleDtos(List<ComboBoxDto> roleDtos) {
        this.roleDtos = roleDtos;
    }
}
