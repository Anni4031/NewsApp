package com.example.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends Activity {
    EditText et_adminname,et_adminpassword;
    Button btn_adminlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        et_adminname= (EditText) findViewById(R.id.et_adminname);
        et_adminpassword= (EditText) findViewById(R.id.et_adminpassword);
        btn_adminlogin= (Button) findViewById(R.id.btn_adminlogin);
    }

    public void AclearOnClick(View view){
        et_adminname.setText("");
        et_adminpassword.setText("");
    }

    public void AloginClick(View v) {
        String aName="admin";
        String aPw="123456";
        if ((et_adminname.getText().toString()).equals(aName)&&(et_adminpassword.getText().toString()).equals(aPw)){
            Intent intent=new Intent();
            intent.setClass(AdminActivity.this,ShowNewsActivity.class);
            startActivity(intent);
            Toast.makeText(this, "登陆成功", Toast.LENGTH_LONG).show();
        }else {
            new  AlertDialog.Builder(AdminActivity.this)
                    .setTitle("登录失败" )
                    .setMessage("管理员名错误或密码错误!" )
                    .setPositiveButton("确定" ,  null )
                    .setCancelable(false)
                    .show();
        }
    }

}
