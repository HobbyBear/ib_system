package com.bus.dao;

import java.sql.SQLException;
import java.util.List;

import com.bus.dao.model.Line;

public interface LineDao {
	
	List<Line> find(Line condition) throws SQLException;
	
	void updateLine(Line line) throws SQLException;
	
	int dropLine(Line line) throws SQLException;
	
	void saveLine(Line line) throws SQLException;

	String findNameByCode(String lineCode) throws SQLException;

    String findCodeByName(String lineName) throws SQLException;
}
