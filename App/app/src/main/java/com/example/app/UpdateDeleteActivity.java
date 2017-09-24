package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.app.datacontrol.LoginDataBaseHelper;
import com.example.app.model.News;

import java.util.Calendar;

public class UpdateDeleteActivity extends Activity {
    TextView et_shownewscontent,et_shownewstitle;
    Spinner sp_showtype;
    LoginDataBaseHelper dbHelper;
    String oldtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        et_shownewstitle= (EditText) findViewById(R.id.et_shownewstitle);
        et_shownewscontent= (EditText) findViewById(R.id.et_shownewscontent);
        sp_showtype = (Spinner) findViewById(R.id.sp_showtype);
        et_shownewscontent.setMovementMethod(ScrollingMovementMethod.getInstance());

        dbHelper = new LoginDataBaseHelper(this, 1);
        dbHelper.getWritableDatabase();

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String ncontent=bundle.getString("content");
        oldtitle=bundle.getString("title");
        et_shownewstitle.setText(oldtitle);
        et_shownewscontent.setText(ncontent);
        et_shownewscontent.scrollTo(0, 0);
    }

    public void updateOnClick(View v) {
        String ntitle = et_shownewstitle.getText().toString();
        String ncontent = et_shownewscontent.getText().toString();
        String ntype = sp_showtype.getSelectedItem().toString();
        if (ntitle.length() < 3 && ncontent.length() < 15) {
            et_shownewstitle.setError("标题不能小于三个字符");
            et_shownewscontent.setError("内容不能小于十五个字符");
        } else if (ntitle == null && ncontent == null) {
            et_shownewstitle.setError("标题不能为空");
            et_shownewscontent.setError("内容不能为空");
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
            dbHelper.updateDataBynId(news,oldtitle);
            Intent intent = new Intent();
            intent.setClass(UpdateDeleteActivity.this, ShowNewsActivity.class);
            startActivity(intent);
        }
    }

    public  void updateclearonClick(View v)
    {
        et_shownewscontent.setText("");
        et_shownewstitle.setText("");
    }
}
