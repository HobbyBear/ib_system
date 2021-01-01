package com.bus.service;

import java.sql.SQLException;
import java.util.List;

import com.bus.dao.model.Role;

public interface RoleService {
	
    List<Role> findAllRoles() throws SQLException;
}
