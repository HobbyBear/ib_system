/**
 * @Title: WindowOpacity.java
 * @Package com.bus.utils
 * @author yuqingming
 * @date 2020年6月12日
 * @version V1.0
 */
package com.bus.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

import com.sun.awt.AWTUtilities;

/**
 * @ClassName: WindowOpacity
 * @Description: 窗口淡入淡出工具类
 * @author yuqingming
 * @date 2020年6月12日
 * @since JDK 1.8
 */
public class WindowOpacity {

    public WindowOpacity(final JFrame jframe) {
        // 窗口设置淡入淡出代码段
        AWTUtilities.setWindowOpacity(jframe, 0f);
        ActionListener lisener = new ActionListener() {
            // 透明度
            float alpha = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (alpha < 0.9) {
                    AWTUtilities.setWindowOpacity(jframe, alpha += 0.1);
                } else {
                    AWTUtilities.setWindowOpacity(jframe, 1);
                    Timer source = (Timer) e.getSource();
                    source.stop();
                }
            }
        };
        // 设置线程控制
        new Timer(50, lisener).start();
    }
}
