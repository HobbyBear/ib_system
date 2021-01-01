package com.bus.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bus.dao.MySqlDao;
import com.bus.dao.UserDao;
import com.bus.dao.model.User;
import com.bus.utils.JdbcUtils;
import com.bus.utils.MD5Util;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import jdk.nashorn.internal.scripts.JD;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 * @author Administrator
 */
public class UserDaoImpl implements UserDao {

    @Override
    public String findUserPasswordByName(String userName) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        String password = queryRunner.query(MySqlDao.findUserPasswordByName, new ScalarHandler<>(), userName);
        if (password != null) {
            return password;
        } else {
            return null;
        }
    }

    @Override
    public List<String> findAuthorityListByName(String userName) throws SQLException {
        List<String> authorities = new ArrayList<>();
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        List<Object[]> results = queryRunner.query(MySqlDao.findAuthorityListByName, new ArrayListHandler(), userName);
        for (Object[] object : results) {
            String string = String.valueOf(object[0]);
            if (string != null) {
                authorities.add(string);
            }
        }
        return authorities;
    }

    @Override
    public List<User> find(User condition) throws SQLException {
        List<User> users = new ArrayList<>();
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        List<Object[]> lists;
        // 全部查询
        if ("".equals(condition.getName()) && condition.getRole() == null && condition.getStatus() == null) {
            lists = queryRunner.query(MySqlDao.findAllUer, new ArrayListHandler());
        }
        // 根据用户名查询
        else if (!"".equals(condition.getName()) && condition.getRole() == null && condition.getStatus() == null) {
            lists = queryRunner.query(MySqlDao.findUerByName, new ArrayListHandler(), condition.getName());
        }
        // 根据角色查询
        else if ("".equals(condition.getName()) && condition.getRole() != null && condition.getStatus() == null) {
            lists = queryRunner.query(MySqlDao.findUserByRole, new ArrayListHandler(), condition.getRole());
        }
        // 根据状态查询
        else if ("".equals(condition.getName()) && condition.getRole() == null && condition.getStatus() != null) {
            if ("1".equals(condition.getStatus())) {
                lists = queryRunner.query(MySqlDao.findUserByStatus, new ArrayListHandler(), "启用");
            } else {
                lists = queryRunner.query(MySqlDao.findUserByStatus, new ArrayListHandler(), "禁用");
            }
        }
        // 根据用户名、角色查询
        else if (!"".equals(condition.getName()) && condition.getRole() != null && condition.getStatus() == null) {
            lists = queryRunner.query(MySqlDao.findUserByNameRole, new ArrayListHandler(), condition.getName(), condition.getRole());
        }

        // 根据用户名、状态查询
        else if (!"".equals(condition.getName()) && condition.getRole() == null && condition.getStatus() != null) {
            if ("1".equals(condition.getStatus())) {
                lists = queryRunner.query(MySqlDao.findUserByNameStatus, new ArrayListHandler(), condition.getName(), "启用");
            } else {
                lists = queryRunner.query(MySqlDao.findUserByNameStatus, new ArrayListHandler(), condition.getName(), "禁用");
            }
        }
        // 根据角色、状态查询
        else if ("".equals(condition.getName()) && condition.getRole() != null && condition.getStatus() != null) {
            if ("1".equals(condition.getStatus())) {
                lists = queryRunner.query(MySqlDao.findUserByRoleStatus, new ArrayListHandler(), condition.getRole(), "启用");
            } else {
                lists = queryRunner.query(MySqlDao.findUserByRoleStatus, new ArrayListHandler(), condition.getRole(), "禁用");
            }
        }
        // 根据用户名、角色、状态查询
        else {
            if ("1".equals(condition.getStatus())) {
                lists = queryRunner.query(MySqlDao.findUserByNameRoleStatus, new ArrayListHandler(), condition.getName(), condition.getRole(), "启用");
            } else {
                lists = queryRunner.query(MySqlDao.findUserByNameRoleStatus, new ArrayListHandler(), condition.getName(), condition.getRole(), "禁用");
            }
        }
        for (Object[] object : lists) {
            User user = new User();
            user.setCode(String.valueOf(object[0]));
            String role = String.valueOf(object[1]);
            user.setRole(role);
            user.setLoginName(String.valueOf(object[2]));
            user.setPassword(String.valueOf(object[3]));
            user.setName(String.valueOf(object[4]));
            user.setPhone(String.valueOf(object[5]));
            user.setIdCard(String.valueOf(object[6]));
            user.setDriving(Integer.valueOf(String.valueOf(object[7])));
            user.setStatus(String.valueOf(object[8]));
            users.add(user);
        }
        return users;
    }

    @Override
    public void changePass(String userName, String pass) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        int row = queryRunner.update(MySqlDao.changePass, pass, userName);
        System.out.println("【修改密码】改变了 " + row + " 行");
    }

    @Override
    public void save(User user) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        int row = queryRunner.update(MySqlDao.save, null, user.getRole(), user.getLoginName(), user.getPassword(), user.getName(), user.getPhone(), user.getIdCard(), user.getDriving(), user.getStatus());
        System.out.println("【添加用户】添加了 " + row + " 行");
    }

    @Override
    public void update(User user) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        int row = queryRunner.update(MySqlDao.update, user.getCode(), user.getRole(), user.getLoginName(), user.getPassword(), user.getName(), user.getPhone(), user.getIdCard(), user.getDriving(), user.getStatus(), user.getCode());
        System.out.println("【修改用户信息】修改了 " + row + " 行");
    }

    @Override
    public void dropUser(User user) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        int row = queryRunner.update(MySqlDao.dropUser, user.getCode());
        System.out.println("【删除用户】删除了 " + row + " 行");
    }

    @Override
    /** 根据登录名找用户 */
    public User findUserByName(String userName) throws SQLException {
        User user = new User();
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        List<Object[]> list = queryRunner.query(MySqlDao.findUserByName, new ArrayListHandler(), userName);
        Object[] object = list.get(0);
        user.setCode(String.valueOf(object[0]));
        user.setRole(String.valueOf(object[1]));
        user.setLoginName(String.valueOf(object[2]));
        user.setPassword(String.valueOf(object[3]));
        user.setName(String.valueOf(object[4]));
        user.setPhone(String.valueOf(object[5]));
        user.setIdCard(String.valueOf(object[6]));
        user.setDriving(Integer.valueOf(String.valueOf(object[7])));
        user.setStatus(String.valueOf(object[8]));
        return user;
    }

    @Override
    public String findUserNameByCode(String userCode) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        Object name = queryRunner.query(MySqlDao.findUserNameByCode, new ScalarHandler<>(), userCode);
        if (name != null) {
            return String.valueOf(name);
        }
        return "";
    }

    @Override
    public String findCodeByName(String userName) throws SQLException {
        ComboPooledDataSource comboPooledDataSource = JdbcUtils.dataSource;
        QueryRunner queryRunner = new QueryRunner(comboPooledDataSource);
        Object code = queryRunner.query(MySqlDao.findUserCodeByName, new ScalarHandler<>(), userName);
        if (code != null) {
            return String.valueOf(code);
        }
        return "";
    }

}