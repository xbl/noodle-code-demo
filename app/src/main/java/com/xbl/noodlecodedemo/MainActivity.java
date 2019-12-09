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
}
