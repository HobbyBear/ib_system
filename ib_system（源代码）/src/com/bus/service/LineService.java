package com.bus.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.bus.dao.model.Line;

public interface LineService {

	public String save(Map<String,List<Line>> dataMapList);

	public List<Line> find(Line condition) throws SQLException;

	public String findNameByCode(String code) throws SQLException;

    String findCodeByName(String lineName) throws SQLException;
}
