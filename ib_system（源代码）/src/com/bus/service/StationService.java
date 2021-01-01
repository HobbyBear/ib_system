package com.bus.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.bus.dao.model.Station;

public interface StationService {
	
	public String save(Map<String,List<Station>> dataMapList);

	public List<Station> find(Station condition) throws SQLException;

    String findNameByCode(String stationCode) throws SQLException;

    String findCodeByName(String valueOf) throws SQLException;
}
