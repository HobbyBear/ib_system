package com.bus.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.bus.dao.DaoFactory;
import com.bus.dao.RoleDao;
import com.bus.dao.model.Role;
import com.bus.service.RoleService;

public class RoleServiceImpl implements RoleService {

    @Override
    public List<Role> findAllRoles() throws SQLException {
        RoleDao dao = DaoFactory.getRoleDao();
        return dao.findAllRole();
    }


}
