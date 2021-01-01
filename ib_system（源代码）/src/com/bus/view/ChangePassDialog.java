/**
 * @Title: ChangePassDialog.java
 * @Package com.bus.view
 * @author yuqingming
 * @date 2020年6月12日
 * @version V1.0
 */
package com.bus.view;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import com.bus.enums.BtnEnum;
import com.bus.enums.ImageEnum;

/**
 * @ClassName: ChangePassDialog
 * @Description: 密码修改窗口
 * @author yuqingming
 * @date 2020年6月12日
 * @since JDK 1.8
 */
public class ChangePassDialog extends JDialog {

    /**
     * @Fields serialVersionUID : 序列化id
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
     * @Fields oldPassword : 旧密码密码框
     */
    private JPasswordField oldPassword;
    /**
     * @Fields newPassword : 新密码密码框
     */
    private JPasswordField newPassword;
    /**
     * @Fields confirmPass : 确认密码密码框
     */
    private JPasswordField confirmPass;

    /**
     * 创建一个新的实例 ChangePassDialog.
     *
     * @param frame 所属Frame
     * @param modal 是否模态
     * @param actionListener 按钮动作监听
     */
    public ChangePassDialog(JFrame frame, boolean modal, ActionListener actionListener) {
        super(frame, modal);

        oldPassword = new JPasswordField();
        newPassword = new JPasswordField();
        confirmPass = new JPasswordField();

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(null);
        JLabel oldPassLabel = new JLabel("旧密码:", SwingConstants.RIGHT);
        JLabel newPassLabel = new JLabel("新密码:", SwingConstants.RIGHT);
        JLabel confirmLabel = new JLabel("确认:", SwingConstants.RIGHT);

        oldPassLabel.setBounds(50, 50, 100, 35);
        oldPassword.setBounds(170, 50, 150, 35);
        newPassLabel.setBounds(50, 100, 100, 35);
        newPassword.setBounds(170, 100, 150, 35);
        confirmLabel.setBounds(50, 150, 100, 35);
        confirmPass.setBounds(170, 150, 150, 35);

        centerPanel.add(oldPassLabel);
        centerPanel.add(oldPassword);
        centerPanel.add(newPassLabel);
        centerPanel.add(newPassword);
        centerPanel.add(confirmLabel);
        centerPanel.add(confirmPass);


        JPanel buttonPanel = new JPanel();
        // 确认按钮
        JButton confirm = new JButton(BtnEnum.USER_CHANGE_PASS_CONFIRM.getName());
        confirm.setActionCommand(BtnEnum.USER_CHANGE_PASS_CONFIRM.getActionCommand());

        // 关闭按钮
        JButton close = new JButton(BtnEnum.DIALOG_CLOSE.getName());
        close.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ChangePassDialog.this.dispose();
            }
        });

        buttonPanel.add(close);
        buttonPanel.add(confirm);
        confirm.addActionListener(actionListener);

        this.setLayout(new BorderLayout());
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setTitle("修改密码");
        this.setIconImage(ImageEnum.ICON_TITLE.getIcon().getImage());
        this.setSize(WIDTH, HEIGHT);
        this.setLocation((SCREEN_WIDTH - WIDTH) / 2, (SCREEN_HEIGHT - HEIGHT) / 2);
        this.setResizable(false);
    }

    /**
     * @Title: getOldPassword
     * @Description: 获取旧密码
     * @return 旧密码值
     */
    public String getOldPassword() {
        return new String(oldPassword.getPassword());
    }

    /**
     * @Title: getConfirmPassword
     * @Description: 获取确认密码
     * @return 确认密码值
     */
    public String getConfirmPassword() {
        return new String(confirmPass.getPassword());
    }

    /**
     * @Title: getNewPassword
     * @Description: 获取新密码
     * @return 新密码值
     */
    public String getNewPassword() {
        return new String(newPassword.getPassword());
    }

}
