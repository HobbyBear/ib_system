/**
 * @Title: LoginView.java
 * @Package com.bus.view
 * @author yuqingming
 * @date 2020年6月8日
 * @version V1.0
 */
package com.bus.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.bus.enums.BtnEnum;
import com.bus.enums.ImageEnum;
import com.bus.enums.TextEnum;
import com.sun.awt.AWTUtilities;

/**
 * @ClassName: Login
 * @Description: TODO(为用户登录提供界面)
 * @author yuqingming
 * @date 2020年6月8日
 * @since JDK 1.8
 */
public class LoginView extends JFrame {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    // 页面名字组
    private JTextField txUsername;

    // 页面密码组
    private JPasswordField txPassword;

    // 登陆按钮
    private JButton btnNewButton;

    // 清空按钮
    private JButton btnClear;

    // 登陆时返回的消息代码
    private String messageCode;

    private ActionListener actionListener;

    private int xOld;
    private int yOld;

    /**
     * Create the application.
     */
    public LoginView(ActionListener actionListener) {
        new WindowOpacity(this);
        this.actionListener = actionListener;
    }

    public void start() {
        initialize();
        this.setVisible(true);
    }

    //初始化窗体
    private void initialize() {
        // 设置窗体大小
        this.setSize(800, 600);

        // 设置是否可以改变大小
        this.setResizable(false);

        // 设定标题
        this.setTitle(TextEnum.TITLE_LOGIN.getName());

        // 设置居中
        this.setLocationRelativeTo(null);

        //用户单击窗口的关闭按钮时程序执行的操作
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //窗体布局为空
        this.getContentPane().setLayout(null);

        // 实例化一个文本框用来输入用户名
        txUsername = new JTextField();
        txUsername.setBounds(312, 246, 200, 33);
        txUsername.setToolTipText("请输入用户名");
        txUsername.setOpaque(false);
        txUsername.setBorder(BorderFactory.createEmptyBorder());

        // 实例化一个文本框用来输入密码
        txPassword = new JPasswordField();
        txPassword.setEchoChar('*');
        txPassword.setBounds(312, 300, 200, 33);
        txPassword.setColumns(10);
        txPassword.setToolTipText("请输入密码");
        txPassword.setOpaque(false);
        txPassword.setBorder(BorderFactory.createEmptyBorder());

        btnClear = new JButton();
        btnClear.setBounds(300, 360, 80, 35);
        btnClear.setText(BtnEnum.CLEAR.getName());
        btnClear.setForeground(Color.white);
        btnClear.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        btnClear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                txUsername.setText("");
                txPassword.setText("");
            }
        });

        // 实例化登陆按钮
        btnNewButton = new JButton();
        btnNewButton.setBounds(420, 360, 80, 35);
        btnNewButton.setText(BtnEnum.LOGIN.getName());
        btnNewButton.setActionCommand(BtnEnum.LOGIN.getActionCommand());
        btnNewButton.setForeground(Color.white);
        btnNewButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));

        JLabel bgLabel = new JLabel(ImageEnum.LOGIN_BG.getIcon());
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
        //设置背景标签的位置
        bgLabel.setBounds(0, 0, ImageEnum.LOGIN_BG.getIcon().getIconWidth(), ImageEnum.LOGIN_BG.getIcon().getIconHeight());
        Container contentPane = this.getContentPane();
        // 这里选择绝对布局管理器，对于边界布局管理器，放入控件后，无法显示背景图片；因为将整个面板都填充满了；
        contentPane.setLayout(null);
        ((JPanel) contentPane).setOpaque(false);

        final JLabel closeLabel = new JLabel(ImageEnum.HEADER_EXIT.getIcon());
        closeLabel.setBounds(760, 10, 30, 30);
        closeLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                closeLabel.setIcon(ImageEnum.HEADER_EXIT_ON.getIcon());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeLabel.setIcon(ImageEnum.HEADER_EXIT.getIcon());
            }

        });
        this.getContentPane().add(closeLabel);

        // 将以上组件加入到窗体中
        this.getContentPane().add(txUsername);
        this.getContentPane().add(txPassword);
        this.getContentPane().add(btnClear);
        this.getContentPane().add(btnNewButton);

        // 将登陆按钮加监听
        btnNewButton.addActionListener(actionListener);
        this.setIconImage(ImageEnum.ICON_TITLE.getIcon().getImage());

        this.setUndecorated(true);
        AWTUtilities.setWindowOpaque(this, false);
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // 记录鼠标按下时的坐标
                xOld = e.getX();
                yOld = e.getY();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xx = xOnScreen - xOld;
                int yy = yOnScreen - yOld;
                // 设置拖拽后，窗口的位置
                LoginView.this.setLocation(xx, yy);
            }
        });

    }

    public JTextField getTxUsername() {
        return txUsername;
    }

    public void setTxUsername(JTextField txUsername) {
        this.txUsername = txUsername;
    }

    public JTextField getTxPassword() {
        return txPassword;
    }

    public void setTxPassword(JPasswordField txPassword) {
        this.txPassword = txPassword;
    }

    public JButton getBtnNewButton() {
        return btnNewButton;
    }

    public void setBtnNewButton(JButton btnNewButton) {
        this.btnNewButton = btnNewButton;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
}
