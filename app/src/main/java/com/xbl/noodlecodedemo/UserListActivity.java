package com.xbl.noodlecodedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import com.xbl.noodlecodedemo.db.UserDBHelper;
import com.xbl.noodlecodedemo.model.User;

import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private UserDBHelper helper;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);

        listView = (ListView) findViewById(R.id.list);

        helper = new UserDBHelper(this);
        new Handler().post(() -> {
            List<User> users = helper.queryUsers();
            listView.setAdapter(new UserListAdapter(UserListActivity.this, users));
        });
    }

    @Override
    protected void onDestroy() {
        if (helper != null) {
            helper.close();
        }
        super.onDestroy();
    }
}
