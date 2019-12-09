package com.xbl.noodlecodedemo.vm;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.xbl.noodlecodedemo.UserListActivity;
import com.xbl.noodlecodedemo.model.User;
import com.xbl.noodlecodedemo.service.UserService;

public class UserVM extends AndroidViewModel {
    public User user;
    private Application application;
    private UserService userService;


    public UserVM(@NonNull Application application) {
        super(application);
        this.application = application;
        userService = new UserService(application);
        user = new User();
    }

    public void onInsert() {
        userService.insertUser(user, (object) -> {
            Boolean isSuccess = (Boolean) object;
            String tipsText = isSuccess ? "插入成功！": "用户名已经存在！";
            Toast.makeText(application, tipsText, Toast.LENGTH_SHORT).show();
        });
    }

    public void onRedirectToList() {
        Intent intent = new Intent(application, UserListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        application.startActivity(intent);
    }

    public void onDestroy() {
        if (userService != null) {
            userService.onDestroy();
        }
    }
}
