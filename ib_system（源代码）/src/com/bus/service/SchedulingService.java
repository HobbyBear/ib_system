package com.bus.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.bus.dao.model.Scheduling;

public interface SchedulingService {
	
	public String save(Map<String,List<Scheduling>> dataMapList);

	public List<Scheduling> find(Scheduling condition) throws SQLException;



}
