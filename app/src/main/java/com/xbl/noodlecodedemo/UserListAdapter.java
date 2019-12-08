package com.xbl.noodlecodedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xbl.noodlecodedemo.model.User;

import java.util.List;
import java.util.Map;

public class UserListAdapter extends BaseAdapter {

    private List<User> users;
    private TextView nameTextView;
    private TextView ageTextView;

    /**
     * 用来布局
     */
    private LayoutInflater layoutInflater;

    public UserListAdapter(Context context, List<User> users) {
        layoutInflater = LayoutInflater.from(context);
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
        View view = layoutInflater.inflate(R.layout.list_item, null);
        nameTextView = view.findViewById(R.id.item_name);
        User item = (User)getItem(position);
        nameTextView.setText(item.getName());

        ageTextView = view.findViewById(R.id.item_age);
        ageTextView.setText(String.valueOf(item.getAge()));
        return view;
    }
}
