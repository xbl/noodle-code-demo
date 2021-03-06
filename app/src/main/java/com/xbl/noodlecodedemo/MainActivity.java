package com.xbl.noodlecodedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xbl.noodlecodedemo.db.UserDBHelper;


public class MainActivity extends AppCompatActivity {

    private EditText inputName;
    private EditText inputAge;
    private Button insertButton;
    private Button queryButton;
    private UserDBHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new UserDBHelper(this);

        inputName = (EditText) findViewById(R.id.input_name);
        inputAge = (EditText) findViewById(R.id.input_age);
        insertButton = (Button) findViewById(R.id.insert_button);
        queryButton = (Button) findViewById(R.id.query_button);

        insertButton.setOnClickListener((v) -> {
            SQLiteDatabase db  = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            String name = inputName.getText().toString();
            values.put("name", name);
            values.put("age", inputAge.getText().toString());

            new Handler().post(() -> {
                String sql = "SELECT user_id FROM " + UserDBHelper.TABLE_NAME + " WHERE name = ?";
                Cursor cursor = db.rawQuery( sql, new String[] { name });
                if (cursor.moveToNext()) {
                    Toast.makeText(this, "用户名已经存在！", Toast.LENGTH_SHORT).show();
                    return ;
                }
                db.insert(UserDBHelper.TABLE_NAME, null, values);
                Toast.makeText(this, "插入成功！", Toast.LENGTH_SHORT).show();
            });
        });

        queryButton.setOnClickListener((v) -> {
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
