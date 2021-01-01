/**
 * @Title: TabClickListener.java
 * @Package com.bus.view.listener
 * @author yuqingming
 * @date 2020年6月12日
 * @version V1.0
 */
package com.bus.view.listener;

import com.bus.enums.ModuleEnum;

import java.sql.SQLException;

/**
 * @ClassName: TabClickListener
 * @Description: 选项卡点击监听
 * @author yuqingming
 * @date 2020年6月12日
 * @since JDK 1.8
 */
public interface TabClickListener {
    /**
     * @Title: onTabClick
     * @Description: 选项卡点击回调
     * @param tabEnum 模块枚举
     */
    void onTabClick(ModuleEnum tabEnum) throws SQLException;
}
