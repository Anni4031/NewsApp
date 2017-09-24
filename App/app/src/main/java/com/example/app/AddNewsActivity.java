package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.app.datacontrol.LoginDataBaseHelper;
import com.example.app.model.News;
import com.example.app.model.User;

import java.util.Calendar;

public class AddNewsActivity extends Activity {
    EditText et_newstitle,et_newscontent;
    LoginDataBaseHelper dbHelper;
    Spinner sp_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        et_newstitle= (EditText) findViewById(R.id.et_newstitle);
        et_newscontent= (EditText) findViewById(R.id.et_newscontent);
        sp_type = (Spinner) findViewById(R.id.sp_type);
        String[] typeArray = getResources().getStringArray(R.array.typeArray);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, typeArray);
        sp_type.setAdapter(typeAdapter);

        dbHelper = new LoginDataBaseHelper(this, 1);
        dbHelper.getWritableDatabase();
    }

    public void addclearOnClick(View v){
        et_newscontent.setText("");
        et_newstitle.setText("");
    }

    public void addonenewOnClick(View v){
        String ntitle=et_newstitle.getText().toString();
        String ncontent=et_newscontent.getText().toString();
        String ntype=sp_type.getSelectedItem().toString();
        if (ntitle.length() < 3&& ncontent.length()<15){
            et_newstitle.setError("标题不能小于三个字符");
            et_newscontent.setError("内容不能小于十五个字符");
        } else if(ntitle==null&&ncontent==null){
            et_newstitle.setError("标题不能为空");
            et_newscontent.setError("内容不能为空");
        } else {
            Calendar calendar = Calendar.getInstance();
            int year, month, day;
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            String ndate = year + "-" + (month + 1) + "-" + day;
            News news = new News();
            news.setnTitle(ntitle);
            news.setnType(ntype);
            news.setnContent(ncontent);
            news.setnDate(ndate);
            dbHelper.addNewsData(news);
            Intent intent = new Intent();
            intent.setClass(AddNewsActivity.this, ShowNewsActivity.class);
            startActivity(intent);
        }
    }
}
