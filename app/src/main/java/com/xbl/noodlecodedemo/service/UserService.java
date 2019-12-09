package com.xbl.noodlecodedemo.service;

import android.content.Context;
import android.os.Handler;

import com.xbl.noodlecodedemo.db.UserDBHelper;
import com.xbl.noodlecodedemo.model.User;


public class UserService {

    private Context context;
    private UserDBHelper helper;

    public UserService(Context context) {
        this.context = context;
        helper = new UserDBHelper(context);
    }

    public void insertUser(User user, Callback callback) {
        new Handler().post(() -> {
            if (helper.existByName(user.getName())) {
                callback.run(false);
                return;
            }
            helper.inserUser(user);
            callback.run(true);
        });
    }

    public void queryUsers(Callback callback) {
        new Handler().post(() -> {
            callback.run(helper.queryUsers());
        });
    }

    public void onDestroy() {
        if (helper != null) {
            helper.close();
        }
    }

    public interface Callback {
        public void run(Object object);
    }

}
