package com.example.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.app.adapter.UsersAdapter;
import com.example.app.datacontrol.LoginDataBaseHelper;
import com.example.app.model.User;

import java.util.ArrayList;
import java.util.List;

public class ShowUsersActivity extends Activity {
    ListView lv_userslist;
    List<User> usersList, usersList1;;
    LoginDataBaseHelper dbHelper;
    User oneuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);
        lv_userslist = (ListView) findViewById(R.id.lv_userslist);
        dbHelper=new LoginDataBaseHelper(this,1);
        dbHelper.getWritableDatabase();
        usersList=new ArrayList<User>();
        usersList=dbHelper.queryallUsers();
        usersList1=new ArrayList<User>();
        for(int i=0;i<usersList.size();i++){
            oneuser=usersList.get(i);
            oneuser.setuId(oneuser.getuId());
            oneuser.setuName(oneuser.getuName());
            oneuser.setuPassword(oneuser.getuPassword());
            oneuser.setuSex(oneuser.getuSex());
            oneuser.setuCity(oneuser.getuCity());
            usersList1.add(oneuser);
        }

        UsersAdapter myAdpter=new UsersAdapter(this,usersList1);
        lv_userslist.setAdapter(myAdpter);
    }
}
