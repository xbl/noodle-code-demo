package com.xbl.noodlecodedemo.service;

import android.content.Context;

import com.xbl.noodlecodedemo.AppExecutors;
import com.xbl.noodlecodedemo.BasicApplication;
import com.xbl.noodlecodedemo.db.DaoFactory;
import com.xbl.noodlecodedemo.db.UserDao;
import com.xbl.noodlecodedemo.model.User;

import java.util.List;


public class UserService {

    private UserDao userDao;

    public UserService() throws Exception {
        userDao = DaoFactory.getUserDao();
    }

    public boolean insertUser(User user) {
        if (userDao.existByName(user.getName()) > 0) {
            return false;
        }
        userDao.insertUser(user);
        return true;
    }

    public List<User> queryUsers() {
        return userDao.queryUsers();
    }

    public interface Callback {
        public void run(Object object);
    }

}
