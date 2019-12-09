package com.xbl.noodlecodedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.xbl.noodlecodedemo.databinding.ActivityMainBinding;
import com.xbl.noodlecodedemo.vm.UserVM;


public class MainActivity extends AppCompatActivity {

    private UserVM userVm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        userVm = ViewModelProviders.of(this).get(UserVM.class);
        binding.setUserVm(userVm);
    }

    @Override
    protected void onDestroy() {
        /**
         * 保持数据库链接，数据库建立连接的开销比较大，所以尽量在 Activity 销毁之前关闭连接。
         * https://developer.android.com/training/data-storage/sqlite.html#PersistingDbConnection
         */
        if (userVm != null) {
            userVm.onDestroy();
        }
        super.onDestroy();
    }
}
