package com.bus.dao;

import java.sql.SQLException;
import java.util.List;

import com.bus.dao.model.Role;

public interface RoleDao {

	List<Role> findAllRole() throws SQLException;
}
