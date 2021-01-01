package com.bus.dao.impl;

import java.sql.SQLException;
import java.util.*;

import com.bus.dao.RoleDao;
import com.bus.dao.MySqlDao;
import com.bus.dao.model.Role;
import com.bus.utils.JdbcUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

public class RoleDaoImpl implements RoleDao {

    @Override
    public List<Role> findAllRole() throws SQLException {
        List<Role> roles = new ArrayList<>();
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        // 查询role表，将每一行放在object数组中，多行返回一个list列表
        List<Object[]> lists = queryRunner.query(MySqlDao.findAllRole, new ArrayListHandler());
        // 遍历得到的结果集lists
        for (Object[] object : lists) {
            // 创建一个Role角色
            Role role = new Role();
            // 设置角色的编码以及名称
            role.setRoleCode(String.valueOf(object[0]));
            // 去掉[]
            role.setRoleName(String.valueOf(object[1]).substring(1, 4));
            // 将角色添加到roles集合里面
            roles.add(role);
        }
        return roles;
    }

}
