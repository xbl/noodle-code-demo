package com.xbl.noodlecodedemo;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.xbl.noodlecodedemo.databinding.ListItemBinding;
import com.xbl.noodlecodedemo.model.User;
import com.xbl.noodlecodedemo.vm.UserVM;

import java.util.List;

public class UserListAdapter extends BaseAdapter {

    private List<User> users;

    /**
     * 用来布局
     */
    private LayoutInflater layoutInflater;
    private Context context;

    public UserListAdapter(Context context, List<User> users) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(users.get(position).getUserId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false);
        User user = (User)getItem(position);
        UserVM userVm = new UserVM((Application) this.context.getApplicationContext());
        userVm.user = user;
        binding.setUserVm(userVm);
        return binding.getRoot();
    }
}
