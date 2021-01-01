/**
 * @Title: SchedulingDialog.java
 * @Package com.bus.view
 * @author yuqingming
 * @date 2020年6月12日
 * @version V1.0
 */
package com.bus.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.bus.dao.model.Scheduling;
import com.bus.enums.BtnEnum;
import com.bus.enums.ImageEnum;

/**
 * @ClassName: SchedulingDialog
 * @Description: 排班计划窗口
 * @author yuqingming
 * @date 2020年6月12日
 * @since JDK 1.8
 */
public class SchedulingDialog extends JDialog {

    /**
     * @Fields serialVersionUID : 序列化版本id
     */
    private static final long serialVersionUID = 1L;

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
    private static final int WIDTH = 400;
    /**
     * @Fields HEIGHT : 窗口高度
     */
    private static final int HEIGHT = 300;
    /**
     * @Fields codeLabel : 排班计划编码Label
     */
    private JLabel codeLabel;

    public SchedulingDialog() {
        super();
    }

    /**
     * 创建一个新的实例 SchedulingDialog.
     *
     * @param frame 所属frame
     * @param modal 是否模态
     * @param actionListener 按钮动作监听
     * @param scheduling 排班计划
     * @param isWrite 是否写卡
     */
    public SchedulingDialog(JFrame frame, boolean modal, ActionListener actionListener, Scheduling scheduling, boolean isWrite) {
        super(frame, modal);

        JPanel centerPanel = new JPanel();
        codeLabel = new JLabel(scheduling.getCode());
        centerPanel.setLayout(new GridLayout(8, 2, 10, 10));
        JLabel codeTextLabel = new JLabel("排班编号:");
        codeTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        codeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        centerPanel.add(codeTextLabel);
        centerPanel.add(codeLabel);

        centerPanel.add(new JLabel("线路名称:", SwingConstants.RIGHT));
        centerPanel.add(new JLabel(scheduling.getLineName(), SwingConstants.LEFT));
        centerPanel.add(new JLabel("车辆信息:", SwingConstants.RIGHT));
        centerPanel.add(new JLabel(scheduling.getBusCode(), SwingConstants.LEFT));
        centerPanel.add(new JLabel("趟次:", SwingConstants.RIGHT));
        centerPanel.add(new JLabel(scheduling.getTcNumber(), SwingConstants.LEFT));
        centerPanel.add(new JLabel("每趟时间:", SwingConstants.RIGHT));
        centerPanel.add(new JLabel(scheduling.getTcTime(), SwingConstants.LEFT));
        centerPanel.add(new JLabel("司机姓名:", SwingConstants.RIGHT));
        centerPanel.add(new JLabel(scheduling.getUserName(), SwingConstants.LEFT));
        centerPanel.add(new JLabel("始发站点:", SwingConstants.RIGHT));
        centerPanel.add(new JLabel(scheduling.getStartStation(), SwingConstants.LEFT));
        centerPanel.add(new JLabel("终点站点:", SwingConstants.RIGHT));
        centerPanel.add(new JLabel(scheduling.getEndStation(), SwingConstants.LEFT));


        JPanel buttonPanel = new JPanel();
        JButton confirm = new JButton(BtnEnum.SCHEDULING_CONFIRM.getName());
        confirm.setActionCommand(BtnEnum.SCHEDULING_CONFIRM.getActionCommand());
        JButton close = new JButton(BtnEnum.DIALOG_CLOSE.getName());
        close.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SchedulingDialog.this.dispose();
            }
        });

        buttonPanel.add(close);

        if (isWrite) {
            buttonPanel.add(confirm);
            confirm.addActionListener(actionListener);
        }

        this.setLayout(new BorderLayout());
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setIconImage(ImageEnum.ICON_TITLE.getIcon().getImage());

        this.setSize(WIDTH, HEIGHT);
        this.setLocation((SCREEN_WIDTH - WIDTH) / 2, (SCREEN_HEIGHT - HEIGHT) / 2);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);

        this.setVisible(true);

    }

    /**
     * @Title: getCode
     * @Description: 获取排班计划编码
     * @return
     */
    public String getCode() {
        return codeLabel.getText();
    }

}
