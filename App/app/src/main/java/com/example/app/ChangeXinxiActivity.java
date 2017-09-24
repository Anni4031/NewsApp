package com.example.app;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.app.datacontrol.LoginDataBaseHelper;
import com.example.app.model.User;

import java.util.Calendar;

public class ChangeXinxiActivity extends Activity {
    String uname,upassword;
    TextView tv_xinxiname;
    EditText et_xinxibirthday;
    RadioGroup rg_xinxisex;
    RadioButton rb_xinximale;
    RadioButton rb_xinxifemale;
    Button btn_xinxidate;
    Spinner sp_xinxicity;
    LoginDataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_xinxi);
        tv_xinxiname= (TextView) findViewById(R.id.tv_xinxiname);
        et_xinxibirthday= (EditText) findViewById(R.id.et_xinxibirthday);
        rg_xinxisex= (RadioGroup) findViewById(R.id.rg_xinxisex);
        rb_xinxifemale= (RadioButton) findViewById(R.id.rb_xinxifemale);
        rb_xinximale= (RadioButton) findViewById(R.id.rb_xinximale);
        sp_xinxicity= (Spinner) findViewById(R.id.sp_xinxicity);
        btn_xinxidate= (Button) findViewById(R.id.btn_xinxidate);

        String[] cityArray = getResources().getStringArray(R.array.cityArray);
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, cityArray);
        sp_xinxicity.setAdapter(cityAdapter);
        dbHelper=new LoginDataBaseHelper(this,1);
        dbHelper.getWritableDatabase();

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        uname=bundle.getString("uname");
        upassword=bundle.getString("password");
        tv_xinxiname.setText(uname);
    }

    public  void changexinxiClick(View v){
        String ubirthday = et_xinxibirthday.getText().toString();
        String ucity=sp_xinxicity.getSelectedItem().toString();
        String usex="";
        if(rb_xinxifemale.isChecked()) usex="女";
        else if(rb_xinximale.isChecked())usex="男";
        User user=new User();
        user.setuCity(ucity);
        user.setuSex(usex);
        user.setuBirthday(ubirthday);
        dbHelper.updateUserByuName(user,uname);
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putString("uname",uname);
        bundle.putString("password",upassword);
        intent.putExtras(bundle);
        intent.setClass(ChangeXinxiActivity.this, PersonXinxiActivity.class);
        startActivity(intent);

    }
    public  void clearxinxiClick(View v){
        et_xinxibirthday.setText("");

    }

    public void xinxidateOnClick(View v){
        Calendar calendar= Calendar.getInstance();
        int year,month,day;
        year=calendar.get(Calendar.YEAR)-20;
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog.OnDateSetListener onDateSetListener=new DatePickerDialog.OnDateSetListener(){
            public void onDateSet(DatePicker view, int year, int month, int dayofMonth){
                et_xinxibirthday.setText(year+"-"+(month+1)+"-"+dayofMonth);
            }
        };
        DatePickerDialog datePickerDialog=new DatePickerDialog(this,onDateSetListener,year,month,day);
        datePickerDialog.show();
    }

}
