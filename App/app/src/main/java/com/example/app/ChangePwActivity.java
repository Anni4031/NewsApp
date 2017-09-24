package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.datacontrol.LoginDataBaseHelper;
import com.example.app.model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ChangePwActivity extends Activity {
    EditText et_changepw,et_changerepw;
    LoginDataBaseHelper dbHelper;
    String uName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pw);
        et_changepw= (EditText) findViewById(R.id.et_changepw);
        et_changerepw= (EditText) findViewById(R.id.et_changerepw);
        dbHelper = new LoginDataBaseHelper(this, 1);
        dbHelper.getWritableDatabase();
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        uName=bundle.getString("uname");
    }

    public void CclearClick(View v){
        et_changepw.setText("");
    }

    public void ChangePwClick(View v){

        String pw=et_changepw.getText().toString();
        if(pw.equals(et_changerepw.getText().toString())){
            User user=new User();
            user.setuPassword(pw);
            dbHelper.updatePwByuName(user,uName);
            Intent intent = new Intent();
            intent.setClass(ChangePwActivity.this, LoginActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "两次密码不一致！", Toast.LENGTH_LONG).show();
        }


    }
}
