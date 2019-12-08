package com.xbl.noodlecodedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xbl.noodlecodedemo.db.UserDBHelper;
import com.xbl.noodlecodedemo.model.User;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText inputName;
    private EditText inputAge;
    private Button insertButton;
    private Button queryButton;
    private UserDBHelper helper;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new UserDBHelper(this);

        inputName = (EditText) findViewById(R.id.input_name);
        inputAge = (EditText) findViewById(R.id.input_age);
        insertButton = (Button) findViewById(R.id.insert_button);
        queryButton = (Button) findViewById(R.id.query_button);

        insertButton.setOnClickListener((view) -> {
            user = new User();
            user.setName(inputName.getText().toString());
            user.setAge(Integer.valueOf(inputAge.getText().toString()));
            helper.inserUser(user, (object) -> {
                Toast.makeText(this, "插入成功！", Toast.LENGTH_SHORT).show();
            });
        });

        queryButton.setOnClickListener((view) -> {
            Intent intent = new Intent(MainActivity.this, UserListActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onDestroy() {
        /**
         * 保持数据库链接，数据库建立连接的开销比较大，所以尽量在 Activity 销毁之前关闭连接。
         * https://developer.android.com/training/data-storage/sqlite.html#PersistingDbConnection
         */
        if (helper != null) {
            helper.close();
        }
        super.onDestroy();
    }
}
