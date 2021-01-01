package com.bus.dao;

import java.sql.SQLException;
import java.util.List;

import com.bus.dao.model.Scheduling;


public interface SchedulingDao {

	List<Scheduling> find(Scheduling condition) throws SQLException;
	
	void updateScheduling (Scheduling scheduling) throws SQLException;
	
	void dropScheduling (Scheduling scheduling) throws SQLException;
	
	void saveScheduling (Scheduling scheduling) throws SQLException;
}
