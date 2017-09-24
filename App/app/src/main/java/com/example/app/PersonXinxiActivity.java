package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.app.datacontrol.LoginDataBaseHelper;
import com.example.app.model.User;

public class PersonXinxiActivity extends Activity {
    TextView tv_uname,tv_usex,tv_ubirthday,tv_ucity;
    LoginDataBaseHelper dbHelper;
    User user;
    String uname,upassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_xinxi);
        tv_uname= (TextView) findViewById(R.id.tv_uname);
        tv_usex= (TextView) findViewById(R.id.tv_usex);
        tv_ubirthday= (TextView) findViewById(R.id.tv_ubirthday);
        tv_ucity= (TextView) findViewById(R.id.tv_ucity);
        dbHelper=new LoginDataBaseHelper(this,1);
        dbHelper.getWritableDatabase();


        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        uname=bundle.getString("uname");
        upassword=bundle.getString("password");
        user=dbHelper.queryByuNameAnduPassword(uname,upassword);

        tv_uname.setText(uname);
        tv_usex.setText(user.getuSex());
        tv_ubirthday.setText(user.getuBirthday());
        tv_ucity.setText(user.getuCity());
    }

    public void changePwOnClick(View v){
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putString("uname",uname);
        intent.putExtras(bundle);
        intent.setClass(PersonXinxiActivity.this,ChangePwActivity.class);
        startActivity(intent);

    }

    public void cancelOnClick(View v){
        Intent intent=new Intent();
        intent.setClass(PersonXinxiActivity.this,WelcomeActivity.class);
        startActivity(intent);
    }

    public void changexinxiOnClick(View v){
        //2.通过Bundle来捆绑数据，然后用intent来传递Bundle
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putString("uname",uname);
        bundle.putString("password",upassword);
        intent.putExtras(bundle);
        intent.setClass(PersonXinxiActivity.this,ChangeXinxiActivity.class);
        startActivity(intent);
    }
}
