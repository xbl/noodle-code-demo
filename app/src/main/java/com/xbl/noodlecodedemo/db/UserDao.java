package com.xbl.noodlecodedemo.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.xbl.noodlecodedemo.model.User;

import java.util.List;

@Dao
public interface UserDao {


    @Query("SELECT * FROM users")
    public List<User> queryUsers();

    @Insert
    public long insertUser(User user);

    @Query("SELECT count(*) FROM users WHERE name = :name")
    public int existByName(String name);

}
