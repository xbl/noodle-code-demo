package com.xbl.noodlecodedemo.service;

import android.content.Context;

import com.xbl.noodlecodedemo.AppExecutors;
import com.xbl.noodlecodedemo.BasicApplication;
import com.xbl.noodlecodedemo.db.DaoFactory;
import com.xbl.noodlecodedemo.db.UserDao;
import com.xbl.noodlecodedemo.model.User;


public class UserService {

    private UserDao userDao;

    public UserService() throws Exception {
        userDao = DaoFactory.getUserDao();
    }

    public void insertUser(User user, Callback callback) {
        AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(() -> {
            if (userDao.existByName(user.getName()) > 0) {
                callback.run(false);
                return;
            }
            userDao.insertUser(user);
            callback.run(true);
        });
    }

    public void queryUsers(Callback callback) {
        AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(() -> {
            callback.run(userDao.queryUsers());
        });
    }

    public interface Callback {
        public void run(Object object);
    }

}
