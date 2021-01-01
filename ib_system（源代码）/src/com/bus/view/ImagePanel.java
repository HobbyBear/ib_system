/**
 * @Title: ImagePanel.java
 * @Package com.bus.view
 * @author yuqingming
 * @date 2020年6月12日
 * @version V1.0
 */
package com.bus.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

/**
 * @ClassName: ImagePanel
 * @Description: 图片面板
 * @author yuqingming
 * @date 2020年6月13日
 * @since JDK 1.8
 */
public class ImagePanel extends JPanel {

    /**
     * @Fields serialVersionUID : 序列化版本ID
     */
    private static final long serialVersionUID = 1L;
    /**
     * @Fields image : 创建图像变量
     */
    private Image image;

    public ImagePanel(Image image) {
        // 初始化图像变量
        this.image = image;
        // 获取当前屏幕宽高
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(width, height);
    }

    @Override
    public void paintComponent(Graphics g) {
        // 继承父类方法
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
