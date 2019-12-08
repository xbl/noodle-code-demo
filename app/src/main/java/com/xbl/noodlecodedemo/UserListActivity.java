package com.xbl.noodlecodedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.xbl.noodlecodedemo.db.UserDBHelper;
import com.xbl.noodlecodedemo.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserListActivity extends AppCompatActivity {

    private UserDBHelper helper;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);

        listView = (ListView) findViewById(R.id.list);

        helper = new UserDBHelper(this);

        helper.queryUsers((list) -> {
            listView.setAdapter(new UserListAdapter(UserListActivity.this, (List<User>) list));
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
