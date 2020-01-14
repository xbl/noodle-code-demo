package com.xbl.noodlecodedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.xbl.noodlecodedemo.model.User;
import com.xbl.noodlecodedemo.service.UserService;

import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private ListView listView;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);

        listView = (ListView) findViewById(R.id.list);

        try {
            userService = new UserService();
        } catch (Exception e) {
            e.printStackTrace();
        }
        userService.queryUsers((users) -> {
            AppExecutors appExecutors = new AppExecutors();
            appExecutors.mainThread().execute(() -> {
                listView.setAdapter(new UserListAdapter(UserListActivity.this, (List<User>) users));
            });
        });
    }
}
