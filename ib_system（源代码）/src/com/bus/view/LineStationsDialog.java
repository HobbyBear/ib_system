/**
 * @Title: LineStationsDialog.java
 * @Package com.bus.view
 * @author yuqingming
 * @date 2020年6月12日
 * @version V1.0
 */
package com.bus.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.bus.dao.model.Station;
import com.bus.enums.BtnEnum;
import com.bus.enums.ImageEnum;

/**
 * @ClassName: LineStationsDialog
 * @Description: 选择线路包含站点窗口
 * @author yuqingming
 * @date 2020年6月12日
 * @since JDK 1.8
 */
public class LineStationsDialog extends JDialog implements MouseListener, MouseMotionListener {

    /**
     * @Fields serialVersionUID : 序列化版本id
     */
    private static final long serialVersionUID = 1L;

    /**
     * @Fields xOld : 鼠标上次点击x坐标
     */
    private int xOld;
    /**
     * @Fields yOld : 鼠标上次点击y坐标
     */
    private int yOld;

    /**
     * @Fields SCREEN_WIDTH : 屏幕宽度
     */
    private final static int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    /**
     * @Fields SCREEN_HEIGHT : 屏幕高度
     */
    private final static int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    /**
     * @Fields WIDTH : 窗口宽度
     */
    private static final int WIDTH = 800;
    /**
     * @Fields HEIGHT : 窗口高度
     */
    private static final int HEIGHT = 600;
    /**
     * @Fields STATION_WIDTH : 站点button宽度
     */
    private static final int STATION_WIDTH = 100;
    /**
     * @Fields STATION_HEIGHT : 站点button高度
     */
    private static final int STATION_HEIGHT = 40;
    /**
     * @Fields LAYOUT_PADDING : 站点间距
     */
    private static final int LAYOUT_PADDING = 5;
    /**
     * @Fields PANEL_PADDING_BOTTOM : 站点底部内边距
     */
    private static final int PANEL_PADDING_BOTTOM = 30;
    /**
     * @Fields dataPanel : 未选择站点面板
     */
    private JPanel dataPanel;
    /**
     * @Fields selectedPanel : 已选择站点面板
     */
    private JPanel selectedPanel;
    /**
     * @Fields conditionStationName : 查询条件-站点名称
     */
    private SearchConditionPanel conditionStationName;
    /**
     * @Fields conditionRegion : 查询条件-所在区域
     */
    private SearchConditionPanel conditionRegion;
    /**
     * @Fields conditionStreet : 查询条件-所在街道
     */
    private SearchConditionPanel conditionStreet;
    /**
     * @Fields stationActionListener : 站点点击事件
     */
    private ActionListener stationActionListener;
    /**
     * @Fields searchButton : 查询按钮
     */
    private JButton searchButton;
    /**
     * @Fields currentSelectedSplit : 当前已选站点名称数组
     */
    private String[] currentSelectedSplit;

    public LineStationsDialog() {
        super();
    }

    /**
     * 创建一个新的实例 LineStationsDialog.
     *
     * @param frame 所属窗口
     * @param modal 是否模态
     * @param actionListener 按钮点击监听
     * @param currentSelected 当前已选站点
     */
    public LineStationsDialog(JFrame frame, boolean modal, ActionListener actionListener, String currentSelected) {
        super(frame, modal);
        searchButton = new JButton(BtnEnum.LINE_STATION_SEARCH.getName());
        searchButton.setActionCommand(BtnEnum.LINE_STATION_SEARCH.getActionCommand());
        searchButton.addActionListener(actionListener);
        conditionStationName = new SearchConditionPanel("站点名称");
        conditionRegion = new SearchConditionPanel("所属区域");
        conditionStreet = new SearchConditionPanel("所在街道");

        JPanel conditionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, LAYOUT_PADDING, LAYOUT_PADDING));

        conditionPanel.add(conditionStationName);
        conditionPanel.add(conditionRegion);
        conditionPanel.add(conditionStreet);
        conditionPanel.add(searchButton);

        JButton confirm = new JButton(BtnEnum.LINE_STATION_CONFIRM.getName());
        confirm.setActionCommand(BtnEnum.LINE_STATION_CONFIRM.getActionCommand());
        conditionPanel.add(confirm);
        confirm.addActionListener(actionListener);

        JPanel stationPanel = new JPanel();

        this.setLayout(new BorderLayout());

        this.add(conditionPanel, BorderLayout.NORTH);
        this.add(stationPanel, BorderLayout.CENTER);

        stationPanel.setLayout(new GridLayout(2, 1));
        dataPanel = new JPanel();
        selectedPanel = new JPanel();
        dataPanel.setLayout(new FlowLayout(FlowLayout.LEFT, LAYOUT_PADDING, LAYOUT_PADDING));
        selectedPanel.setLayout(new FlowLayout(FlowLayout.LEFT, LAYOUT_PADDING, LAYOUT_PADDING));


        JScrollPane dataSp = new JScrollPane(dataPanel);
        JScrollPane selectedSp = new JScrollPane(selectedPanel);

        dataSp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        selectedSp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        stationPanel.add(dataSp);
        stationPanel.add(selectedSp);

        if (currentSelected != null && !"".equals(currentSelected.trim())) {
            currentSelectedSplit = currentSelected.split(",");
        } else {
            currentSelectedSplit = new String[0];
        }

        if (currentSelectedSplit != null) {
            for (String stationName : currentSelectedSplit) {
                JButton button = new JButton(stationName);
                button.setToolTipText(stationName);
                button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
                button.setForeground(Color.WHITE);
                moveStationToSelected(button);
            }
        }

        stationActionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                dataPanel.remove(button);
                dataPanel.updateUI();
                moveStationToSelected(button);
            }
        };

        this.setIconImage(ImageEnum.ICON_TITLE.getIcon().getImage());

        this.setSize(WIDTH, HEIGHT);
        this.setLocation((SCREEN_WIDTH - WIDTH) / 2, (SCREEN_HEIGHT - HEIGHT) / 2);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
    }

    /**
     * @Title: autoPanelHeight
     * @Description: 根据站点数量重新设置面板高度，自适应
     * @param panel 目标面板
     * @param dataSize 站点数量
     */
    private void autoPanelHeight(JPanel panel, int dataSize) {
        panel.setPreferredSize(new Dimension(WIDTH, dataSize / (WIDTH / (STATION_WIDTH + LAYOUT_PADDING + 1)) * (STATION_HEIGHT + LAYOUT_PADDING + 1) + PANEL_PADDING_BOTTOM));
    }

    /**
     * @Title: setStationList
     * @Description: 放置站点查询结果，过滤已选择站点
     * @param dataList 查询结果站点集合
     */
    public void setStationList(List<Station> dataList) {
        dataPanel.removeAll();
        List<String> selected = Arrays.asList(currentSelectedSplit);
        // 删除已选中
        Iterator<Station> iter = dataList.iterator();
        while (iter.hasNext()) {
            String stationName = iter.next().getStationName();
            if (selected.contains(stationName)) {
                iter.remove();
            }
        }
        autoPanelHeight(dataPanel, dataList.size());
        for (int i = 0; i < dataList.size(); i++) {
            JButton button = new JButton(dataList.get(i).getStationName());
            button.setToolTipText(button.getText());
            button.setToolTipText(button.getText());
            button.setPreferredSize(new Dimension(STATION_WIDTH, STATION_HEIGHT));
            button.setForeground(Color.BLACK);
            dataPanel.add(button);
            button.addActionListener(stationActionListener);
        }
        dataPanel.updateUI();
    }

    /**
     * @Title: moveStationToSelected
     * @Description: 将站点从未选择移动到已选择
     * @param button 要移动的站点
     */
    private void moveStationToSelected(JButton button) {
        button.setPreferredSize(new Dimension(STATION_WIDTH, STATION_HEIGHT));
        button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
        button.setForeground(Color.WHITE);
        selectedPanel.add(button);
        selectedPanel.updateUI();
        int selectedChildSize = selectedPanel.getComponentCount();
        int dataChildSize = dataPanel.getComponentCount();
        autoPanelHeight(dataPanel, dataChildSize);
        autoPanelHeight(selectedPanel, selectedChildSize);
        ActionListener[] actionListeners = button.getActionListeners();
        if (actionListeners != null) {
            for (ActionListener actionListener : actionListeners) {
                button.removeActionListener(actionListener);
            }
        }
        button.addMouseListener(this);
        button.addMouseMotionListener(this);
        currentSelectedSplit = this.getSelectedStations().split(",");
    }

    /**
     * @Title: moveStationToData
     * @Description: 将站点从已选择移动到未选择
     * @param button 要移动的站点
     */
    private void moveStationToData(JButton button) {
        button.addActionListener(stationActionListener);
        button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
        button.setForeground(Color.BLACK);
        selectedPanel.remove(button);
        button.removeMouseListener(this);
        selectedPanel.updateUI();
        dataPanel.add(button);
        dataPanel.updateUI();
        int selectedChildSize = selectedPanel.getComponentCount();
        int dataChildSize = dataPanel.getComponentCount();
        autoPanelHeight(dataPanel, dataChildSize);
        autoPanelHeight(selectedPanel, selectedChildSize);
        currentSelectedSplit = this.getSelectedStations().split(",");
    }

    /* (非 Javadoc)
     *
     *
     * @param e
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // 记录鼠标按下时的屏幕坐标
        xOld = e.getXOnScreen();
        yOld = e.getYOnScreen();
    }

    /* (非 Javadoc)
     *
     *
     * @param e
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        JButton current = (JButton) e.getSource();
        // 获取鼠标当前在屏幕中的坐标
        int xOnScreen = e.getXOnScreen();
        int yOnScreen = e.getYOnScreen();
        // 获取鼠标移动的距离(当前屏幕坐标-上次屏幕坐标)
        int xx = xOnScreen - xOld;
        int yy = yOnScreen - yOld;
        // 记录上次鼠标在屏幕中的坐标
        xOld = xOnScreen;
        yOld = yOnScreen;
        // 获取鼠标实际在窗口中的坐标
        Point poiint = current.getLocation();
        // 计算控件实际应该设置的位置(窗口中的坐标+移动距离)
        int realX = poiint.x + xx;
        int realY = poiint.y + yy;
        // 修改控件位置(让控件跟随鼠标拖动移动)
        current.setLocation(realX, realY);
    }

    /* (非 Javadoc)
     *
     *
     * @param e
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // 获取选中面板中所有站点
        Component[] components = selectedPanel.getComponents();
        List<Component> list = Arrays.asList(components);
        // 对站点进行排序
        Collections.sort(list, new Comparator<Component>() {

            @Override
            public int compare(Component o1, Component o2) {
                if (Math.abs(o1.getY() - o2.getY()) > o1.getHeight() / 2) {
                    // 如果两个站点的y坐标之差大于站点高度的一半,则证明当前比较的两个站点不在同一行,所以增加y的权重,y*100
                    return (o1.getY() * 100 + o1.getX()) - (o2.getY() * 100 + o2.getX());
                } else {
                    // 否则,证明当前比较的两个站点在同一行,直接比较x坐标大小
                    return o1.getX() - o2.getX();
                }
            }

        });
        // 删除原有站点
        selectedPanel.removeAll();
        // 重新添加排序后的站点
        for (Component component : list) {
            selectedPanel.add(component);
        }
        // 更新UI
        selectedPanel.updateUI();

    }

    /**
     * @Title: getSelectedStations
     * @Description: 获取所选站点集合
     * @return
     */
    public String getSelectedStations() {
        Component[] components = selectedPanel.getComponents();
        StringBuilder builder = new StringBuilder();
        for (Component component : components) {
            JButton button = (JButton) component;
            if (builder.length() == 0) {
                builder.append(button.getText());
            } else {
                builder.append(",");
                builder.append(button.getText());
            }
        }
        return builder.toString();
    }

    /**
     * @Title: getSearchCondition
     * @Description: 获取站点查询条件
     * @return
     */
    public Station getSearchCondition() {
        Station station = new Station();
        station.setStationName(conditionStationName.getCondition());
        station.setRegion(conditionRegion.getCondition());
        station.setStreet(conditionStreet.getCondition());
        return station;
    }

    /* (非 Javadoc)
     *
     *
     * @param e
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        if (e.getButton() == MouseEvent.BUTTON3) {
            moveStationToData(button);
        }
    }

    /* (非 Javadoc)
     *
     *
     * @param e
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /* (非 Javadoc)
     *
     *
     * @param e
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /* (非 Javadoc)
     *
     *
     * @param e
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
