package com.xbl.noodlecodedemo;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.xbl.noodlecodedemo.db.UserDao;
import com.xbl.noodlecodedemo.model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class UserDaoTest {
    private UserDao userDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .build();
        userDao = db.userDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void should_insert_user_and_query_user_one() {
        long expectedId = 1;
        User expectedUser = new User();
        expectedUser.setUserId((int)expectedId);
        expectedUser.setName("hello");
        expectedUser.setAge(12);

        long resultId = userDao.insertUser(expectedUser);
        assertThat(resultId, is(expectedId));

        List<User> users = userDao.queryUsers();
        assertThat(users.size(), is(1));

        User resultUser = users.get(0);
        assertThat(resultUser, is(expectedUser));
    }
}
