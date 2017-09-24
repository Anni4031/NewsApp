package com.example.app.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.ShowUsersActivity;
import com.example.app.datacontrol.LoginDataBaseHelper;
import com.example.app.model.User;

import java.util.List;

/**
 * Created by lenovo on 2017/6/15.
 */

public class UsersAdapter extends BaseAdapter {
    List<User> mydata;
    Context mContext;

    public UsersAdapter(Context mContext,List<User> mydata) {
        this.mydata = mydata;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mydata.size();
    }//item的总行数

    @Override
    public Object getItem(int position) {
        return mydata.get(position);
    }//item对象

    @Override
    public long getItemId(int position) {
        return position ;
    }//item的id

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {//绘制每一个item
        View view = View.inflate(mContext, R.layout.usersitem, null);
        TextView tv_unameItem= (TextView) view.findViewById(R.id.tv_unameItem);
        TextView tv_upwItem = (TextView) view.findViewById(R.id.tv_upwItem);
        TextView tv_usexItem = (TextView) view.findViewById(R.id.tv_usexItem);
        TextView tv_ucityItem = (TextView) view.findViewById(R.id.tv_ucityItem);
        TextView tv_ubirthdayItem = (TextView) view.findViewById(R.id.tv_ubirthdayItem);
        LinearLayout ll_users=(LinearLayout) view.findViewById(R.id.ll_users);
        //News onenew = mydata.get(position);
        User oneuser=mydata.get(position);
        final String uname=oneuser.getuName();
        final String upw=oneuser.getuPassword();
        final String usex=oneuser.getuSex();
        final String ucity=oneuser.getuCity();
        final String ubirthday=oneuser.getuBirthday();
        tv_unameItem.setText("用户名:"+uname);
        tv_upwItem.setText("密码:"+upw);
        tv_usexItem.setText("性别:"+usex);
        tv_ucityItem.setText("城市名:"+ucity);
        tv_ubirthdayItem.setText("出生年月:"+ubirthday);
        ll_users.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(mContext).setTitle("删除")
                        .setMessage("你确定删除"+uname).setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        LoginDataBaseHelper dbHelper=new LoginDataBaseHelper(mContext,1);
                        dbHelper.getWritableDatabase();
                        dbHelper.deteleUserByuName(uname);
                        Intent intent=new Intent();
                        intent.setClass(mContext,ShowUsersActivity.class);
                        mContext.startActivity(intent);
                    }
                }).setNegativeButton("取消",null).setCancelable(false).show();
                return false;
            }

        });
        return view;
    }
}
