package com.example.app;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.app.datacontrol.LoginDataBaseHelper;
import com.example.app.model.User;

import java.util.Calendar;

public class RegisterActivity extends Activity implements CompoundButton.OnCheckedChangeListener  {
    RadioGroup rg_sex;
    RadioButton rb_male;
    RadioButton rb_female;
    EditText et_name;
    EditText et_pw;
    EditText et_repw;
    Button btn_regist;
    Button btn_clear;
    EditText et_birthday;
    Button btn_date;
    Spinner sp_city;
    CheckBox cb_agree;
    LoginDataBaseHelper dbHelper;
    int index=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        rg_sex = (RadioGroup) findViewById(R.id.rg_sex);
        rb_female = (RadioButton) findViewById(R.id.rb_female);
        rb_male = (RadioButton) findViewById(R.id.rb_male);
        et_name = (EditText) findViewById(R.id.et_name);
        et_pw = (EditText) findViewById(R.id.et_pw);
        et_repw = (EditText) findViewById(R.id.et_repw);
        btn_regist = (Button) findViewById(R.id.btn_regist);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        et_birthday= (EditText) findViewById(R.id.et_birthday);
        btn_date= (Button) findViewById(R.id.btn_date);
        sp_city = (Spinner) findViewById(R.id.sp_city);
        cb_agree = (CheckBox) findViewById(R.id.cb_agree);

        String[] cityArray = getResources().getStringArray(R.array.cityArray);
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, cityArray);
        sp_city.setAdapter(cityAdapter);
        dbHelper=new LoginDataBaseHelper(this,1);
        dbHelper.getWritableDatabase();
    }
    public void addonClick(View view)
    {
        String uname = et_name.getText().toString();
        String nospsceUserName = uname.replace(" ","");
        String upassword=et_pw.getText().toString();
        if (uname.length() > nospsceUserName.length())
            et_name.setError("用户名内不能有空格");
        else if (uname.length() < 3)
            et_name.setError("用户名不能小于三个字符");
        else if (uname.length() > 10)
            et_name.setError("用户名不能大于十个字符");
        else if(upassword.length()<3&&et_repw.getText().toString().length()<3){
            et_repw.setError("密码不能小于三个字符");
        }
        else if (!upassword.equals(et_repw.getText().toString()))
            et_repw.setError("两次密码必须一致");
        else if(uname==null&&upassword==null)
            et_name.setError(null);
        else {
            if(cb_agree.isChecked()){
                String ucity=sp_city.getSelectedItem().toString();
                String usex="";
                if(rb_female.isChecked()) usex="女";
                else if(rb_male.isChecked())usex="男";
                String ubirthday=et_birthday.getText().toString();
                User user=new User();
                user.setuName(uname);
                user.setuPassword(upassword);
                user.setuCity(ucity);
                user.setuSex(usex);
                user.setuBirthday(ubirthday);
                dbHelper.addData(user);
                Intent intent=new Intent();
                intent.setClass(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }
    }
    public void clearonClick(View view) {
        et_name.setText("");
        et_pw.setText("");
        et_repw.setText("");
        et_birthday.setText("");
    }
    public void dateOnClick(View v){
        Calendar calendar= Calendar.getInstance();
        int year,month,day;
        year=calendar.get(Calendar.YEAR)-20;
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog.OnDateSetListener onDateSetListener=new DatePickerDialog.OnDateSetListener(){
            public void onDateSet(DatePicker view, int year, int month, int dayofMonth){
                et_birthday.setText(year+"-"+(month+1)+"-"+dayofMonth);
            }
        };
        DatePickerDialog datePickerDialog=new DatePickerDialog(this,onDateSetListener,year,month,day);
        datePickerDialog.show();
    }
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
            cb_agree.setEnabled(true);
        else
            cb_agree.setEnabled(false);
    }
}
