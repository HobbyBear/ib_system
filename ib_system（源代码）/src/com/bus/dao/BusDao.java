package com.bus.dao;

import java.sql.SQLException;
import java.util.List;

import com.bus.dao.model.Bus;

public interface BusDao {

	List<Bus> find(Bus condition) throws SQLException;
	
	void updateBus(Bus bus) throws SQLException;
	
	void dropBus(Bus bus) throws SQLException;
	
	void saveBus(Bus bus) throws SQLException;
}
