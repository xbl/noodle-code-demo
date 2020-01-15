package com.xbl.noodlecodedemo.db;

public final class DaoFactory {
    private static UserDao userDao;

    public static void setUserDao(UserDao mockUserDao) {
        userDao = mockUserDao;
    }

    public static UserDao getUserDao() throws Exception {
        if (userDao == null) {
            throw new Exception("请在 BasicApplication 中 set UserDao！");
        }
        return userDao;
    }
}
