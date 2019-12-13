package com.xbl.noodlecodedemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                "user_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "name TEXT NOT NULL, " +
                "age INTEGER NOT NULL);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean existByName(String name) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT user_id FROM " + UserDBHelper.TABLE_NAME + " WHERE name = ?";
        Cursor cursor = db.rawQuery( sql, new String[] { name });
        boolean exist = false;
        if (cursor.moveToNext()) {
            exist = true;
        }
        cursor.close();
        return exist;
    }

    public void inserUser(User user) {
        SQLiteDatabase db  = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("age", user.getAge());
        db.insert(TABLE_NAME, null, values);
    }

    public List<User> queryUsers() {
        SQLiteDatabase db = getReadableDatabase();
        List<User> users = new ArrayList<>();
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
        return users;
    }

    public interface Callback {
        public void run(Object object);
    }
}
