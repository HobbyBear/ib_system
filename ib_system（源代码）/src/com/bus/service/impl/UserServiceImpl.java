package com.bus.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.bus.constants.Constants;
import com.bus.dao.DaoFactory;
import com.bus.dao.UserDao;
import com.bus.dao.model.User;
import com.bus.service.UserService;
import com.bus.utils.MD5Util;

public class UserServiceImpl implements UserService {

    @Override
    public String login(User user) throws SQLException {
        UserDao dao = DaoFactory.getUserDao();
        String password = dao.findUserPasswordByName(user.getLoginName());
        if (password == null) {
            return Constants.MS0010202;
        } else if (!password.equals(user.getPassword())) {
            return Constants.MS0010203;
        } else {
            return Constants.MS0010201;
        }
    }

    @Override
    public List<String> getUserAuthorityList(String userName) throws SQLException {
        UserDao dao = DaoFactory.getUserDao();
        return dao.findAuthorityListByName(userName);
    }

    @Override
    public String save(Map<String, List<User>> dataMapList) {
        try {
            UserDao dao = DaoFactory.getUserDao();
            List<User> deleteList = dataMapList.get("DELETE");
            if (deleteList != null && deleteList.size() > 0) {
                for (User user : deleteList) {
                    dao.dropUser(user);
                }
            }

            List<User> updateList = dataMapList.get("UPDATE");
            if (updateList != null && updateList.size() > 0) {
                for (User user : updateList) {
                    dao.update(user);
                }
            }

            List<User> saveList = dataMapList.get("SAVE");
            if (saveList != null && saveList.size() > 0) {
                for (User user : saveList) {
                    dao.save(user);
                }
            }

        } catch (Exception e) {
            return "操作失败";
        }
        return "操作成功";
    }

    @Override
    public List<User> find(User condition) throws SQLException {
        UserDao dao = DaoFactory.getUserDao();
        return dao.find(condition);
    }

    @Override
    public void changePass(String userName, String pass) throws SQLException {
        UserDao dao = DaoFactory.getUserDao();
        dao.changePass(userName, pass);

    }

    @Override
    public User findUserByName(String userName) throws SQLException {
        UserDao dao = DaoFactory.getUserDao();
        return dao.findUserByName(userName);
    }

    @Override
    public String findUserNameByCode(String userCode) throws SQLException {
        UserDao dao = DaoFactory.getUserDao();
        return dao.findUserNameByCode(userCode);
    }

    @Override
    public String findCodeByName(String valueOf) throws SQLException {
        UserDao dao = DaoFactory.getUserDao();
        return dao.findCodeByName(valueOf);
    }
}
