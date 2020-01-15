package com.xbl.noodlecodedemo;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.xbl.noodlecodedemo.db.DaoFactory;
import com.xbl.noodlecodedemo.model.User;
import com.xbl.noodlecodedemo.service.UserService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class UserServiceTest {
    private AppDatabase db;
    private UserService userService;

    @Before
    public void createDb() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .build();
        DaoFactory.setUserDao(db.userDao());
        userService = new UserService();
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

        boolean isSuccess = userService.insertUser(expectedUser);
        assertThat(isSuccess, is(true));

        List<User> users = userService.queryUsers();
        User resultUser = users.get(0);
        assertThat(resultUser, is(expectedUser));
    }
}
