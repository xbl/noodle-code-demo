package com.xbl.noodlecodedemo.vm;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.xbl.noodlecodedemo.UserListActivity;
import com.xbl.noodlecodedemo.db.UserDBHelper;
import com.xbl.noodlecodedemo.model.User;

public class UserVM extends AndroidViewModel {
    public User user;
    private Application application;
    private UserDBHelper helper;


    public UserVM(@NonNull Application application) {
        super(application);
        this.application = application;
        helper = new UserDBHelper(application);
        user = new User();
    }

    public void onInsert() {
        new Handler().post(() -> {
            if (helper.existByName(user.getName())) {
                Toast.makeText(application, "用户名已经存在！", Toast.LENGTH_SHORT).show();
                return ;
            }
            helper.inserUser(user);
            Toast.makeText(application, "插入成功！", Toast.LENGTH_SHORT).show();
        });
    }

    public void onRedirectToList() {
        Intent intent = new Intent(application, UserListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        application.startActivity(intent);
    }

    public void onDestroy() {
        /*
          保持数据库链接，数据库建立连接的开销比较大，所以尽量在 Activity 销毁之前关闭连接。
          https://developer.android.com/training/data-storage/sqlite.html#PersistingDbConnection
         */
        if (helper != null) {
            helper.close();
        }
    }
}
