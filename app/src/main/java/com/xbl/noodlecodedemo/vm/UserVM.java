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
        try {
            userService = new UserService();
        } catch (Exception e) {
            e.printStackTrace();
        }
        user = new User();
    }

    public void onInsert() {
        Toast toast = Toast.makeText(application, "", Toast.LENGTH_SHORT);
        userService.insertUser(user, (object) -> {
            Boolean isSuccess = (Boolean) object;
            String tipsText = isSuccess ? "插入成功！": "用户名已经存在！";
            toast.setText(tipsText);
            toast.show();
        });
    }

    public void onRedirectToList() {
        Intent intent = new Intent(application, UserListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        application.startActivity(intent);
    }
}
