package com.bus.dao;

import java.sql.SQLException;
import java.util.List;

import com.bus.dao.model.User;
/**
 * @author Administrator
 * */
public interface UserDao {

    /** 根据用户名查询密码 */
    String findUserPasswordByName(String userName) throws SQLException;

    /** 根据用户名查找其拥有的所有权限 */
    List<String> findAuthorityListByName(String userName) throws SQLException;

    /** 根据条件查询用户 */
    List<User> find(User Condition) throws SQLException;

    /** 修改密码 */
    void changePass(String userName, String pass) throws SQLException;

    /** 保存用户信息 */
    void save(User user) throws SQLException;

    /** 修改用户信息 */
    void update(User user) throws SQLException;

    /** 删除用户 */
    void dropUser(User user) throws SQLException;

    /** 通过用户名查找用户 */
    User findUserByName(String userName) throws SQLException;

    /** 通过用户编号查找用户 */
    String findUserNameByCode(String userCode) throws SQLException;

    /** 通过用户名查找编号 */
    String findCodeByName(String valueOf) throws SQLException;
}
