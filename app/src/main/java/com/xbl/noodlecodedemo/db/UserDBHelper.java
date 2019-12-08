package com.xbl.noodlecodedemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;

import androidx.annotation.Nullable;


import com.xbl.noodlecodedemo.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDBHelper extends SQLiteOpenHelper {

    public final static String TABLE_NAME = "users";

    public UserDBHelper(@Nullable Context context) {
        super(context, "database-name", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +" ( " +
                "user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "age INTEGER NOT NULL);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void inserUser(User user, Callback callback) {
        final SQLiteDatabase db  = getWritableDatabase();
        final ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("age", user.getAge());

        new Handler().post(() -> {
            db.insert(TABLE_NAME, null, values);
            callback.run(null);
        });
    }

    public void queryUsers(Callback callback) {
        final SQLiteDatabase db = getReadableDatabase();
        final List<User> users = new ArrayList<>();
        new Handler().post(() -> {
            Cursor cursor = db.query(TABLE_NAME,
                    new String[] {
                            "user_id",
                            "name",
                            "age"
                    },
                    null, null, null, null, null
                    );
            while (cursor.moveToNext()) {
                User map = new User();
                map.setUserId(cursor.getInt(cursor.getColumnIndex("user_id")));
                map.setName(cursor.getString(cursor.getColumnIndex("name")));
                map.setAge(cursor.getInt(cursor.getColumnIndex("age")));
                users.add(map);
            }

            cursor.close();
            callback.run(users);
        });
    }

    public interface Callback {
        public void run(Object object);
    }
}
