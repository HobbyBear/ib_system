/**  
 * @Title: SearchConditionPanel.java
 * @Package com.bus.view
 * @author yuqingming
 * @date 2020年6月12日
 * @version V1.0  
 */
package com.bus.view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bus.view.dto.ComboBoxDto;

/**
 * @ClassName: SearchConditionPanel
 * @Description: 查询条件控件
 * @author yuqingming
 * @date 2020年6月12日
 * @since JDK 1.8
 */
public class SearchConditionPanel extends JPanel{

	/**
	 * @Fields serialVersionUID : 序列化版本id
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @Fields conditionLabel : 查询条件文字
	 */
	private JLabel conditionLabel;
	
	/**
	 * @Fields conditionText : 查询条件文本框
	 */
	private JTextField conditionText;
	
	/**
	 * @Fields conditionCombobox : 查询条件下拉框
	 */
	private JComboBox<ComboBoxDto> conditionCombobox;
	
	private SearchConditionPanel(){
		conditionLabel = new JLabel();
		this.add(conditionLabel, BorderLayout.WEST);
	}
	
	/**
	 * 创建一个文本框类型的实例 SearchConditionPanel.
	 *
	 * @param text 查询条件文字
	 */
	public SearchConditionPanel(String text){
		this();
		this.conditionLabel.setText(text);
		this.conditionText = new JTextField();
		this.conditionText.setColumns(15);
		this.add(conditionText);
	}
	
	/**
	 * 创建一个下拉框类型的实例 SearchConditionPanel.
	 *
	 * @param text 查询条件文字
	 * @param datas 查询条件下拉框可选值
	 */
	public SearchConditionPanel(String text, List<ComboBoxDto> datas){
		this();
		this.conditionLabel.setText(text);
		this.conditionCombobox = new JComboBox<>();
		for (ComboBoxDto data : datas) {
			this.conditionCombobox.addItem(data);
		}
		this.add(conditionCombobox);
	}
	
	/**
	 * @Title: getCondition
	 * @Description: 获取查询条件
	 * @return
	 */
	public String getCondition(){
		if(conditionText != null){
			return conditionText.getText();
		}else if(conditionCombobox != null){
			ComboBoxDto dto =(ComboBoxDto) conditionCombobox.getSelectedItem();
			return dto.getKey();
		}
		return "";
	}
	
	/**
	 * @Title: reset
	 * @Description: 重置查询条件
	 */
	public void reset(){
		if(conditionText != null){
			conditionText.setText("");
		}else if(conditionCombobox != null){
			conditionCombobox.setSelectedIndex(0);
		}
	}

}
