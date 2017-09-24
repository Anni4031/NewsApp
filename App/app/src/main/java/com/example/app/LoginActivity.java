package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.datacontrol.LoginDataBaseHelper;
import com.example.app.model.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoginActivity extends Activity {
    EditText et_username,et_password;
    Button btn_login;
    CheckBox cb_remember;
    LoginDataBaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_username= (EditText) findViewById(R.id.et_username);
        et_password= (EditText) findViewById(R.id.et_password);
        btn_login= (Button) findViewById(R.id.btn_login);
        cb_remember= (CheckBox) findViewById(R.id.cb_remember);
        dbHelper=new LoginDataBaseHelper(this,1);
        dbHelper.getWritableDatabase();
        FileInputStream fi=null;
        ObjectInputStream oi=null;
        try {
            fi=new FileInputStream("/data/data/com.example.app/login.txt");
            oi=new ObjectInputStream(fi);
            User user=null;
            user=(User)oi.readObject();
            et_username.setText(user.getuName());
            et_password.setText(user.getuPassword());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void RegisterOnClick(View view){
        Intent intent=new Intent();
        intent.setClass(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
    public void LclearOnClick(View view){
        et_username.setText("");
        et_password.setText("");
    }

    public void loginClick(View v) {
        String uname=et_username.getText().toString();
        String upw=et_password.getText().toString();
        User user=dbHelper.querydate(uname,upw);
        if(cb_remember.isChecked()) {
            if (user!=null) {
                //通过对象流存储数据到本地
                User user2 = new User();
                user2.setuName(et_username.getText().toString());
                user2.setuPassword(et_password.getText().toString());
                FileOutputStream fo = null;
                ObjectOutputStream oo = null;
                try {
                    fo = new FileOutputStream("/data/data/com.example.app/login.txt");
                    oo = new ObjectOutputStream(fo);
                    oo.writeObject(user2);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "写数据出错", Toast.LENGTH_LONG).show();
                } finally {
                    try {
                        if (oo != null) oo.close();
                        if (fo != null) fo.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
