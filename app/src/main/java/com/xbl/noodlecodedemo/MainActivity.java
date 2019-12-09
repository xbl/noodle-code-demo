package com.xbl.noodlecodedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

import com.xbl.noodlecodedemo.db.UserDBHelper;


public class MainActivity extends AppCompatActivity {

    private EditText inputName;
    private EditText inputAge;
    private Button insertButton;
    private Button queryButton;
    private SQLiteOpenHelper helper;


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
            final SQLiteDatabase db  = helper.getWritableDatabase();
            final ContentValues values = new ContentValues();
            values.put("name", inputName.getText().toString());
            values.put("age", inputAge.getText().toString());

            new Handler().post(() -> {
                db.insert(UserDBHelper.TABLE_NAME, null, values);
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
