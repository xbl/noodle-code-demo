package com.xbl.noodlecodedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import com.xbl.noodlecodedemo.db.UserDBHelper;

import java.util.ArrayList;
import java.util.HashMap;
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

        new Handler().post(() -> {
            List<Map<String, Object>> users = new ArrayList<>();
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cursor = db.query(UserDBHelper.TABLE_NAME,
                    new String[] {
                            "user_id",
                            "name",
                            "age"
                    },
                    null, null, null, null, null
                    );
            while (cursor.moveToNext()) {
                Map<String, Object> map = new HashMap<>();
                map.put("user_id", cursor.getInt(cursor.getColumnIndex("user_id")));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                map.put("name", name);
                map.put("age", cursor.getInt(cursor.getColumnIndex("age")));
                users.add(map);
            }

            cursor.close();
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
