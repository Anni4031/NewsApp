package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.app.datacontrol.LoginDataBaseHelper;
import com.example.app.model.News;

public class OneNewsActivity extends Activity {
    TextView tv_newscontent,tv_newstype,tv_newstitle,tv_newsdate;
    LoginDataBaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_news);
        tv_newstitle= (TextView) findViewById(R.id.tv_newstitle);
        tv_newscontent= (TextView) findViewById(R.id.tv_newscontent);
        tv_newstype= (TextView) findViewById(R.id.tv_newstype);
        tv_newsdate= (TextView) findViewById(R.id.tv_newsdate);
        tv_newscontent.setMovementMethod(ScrollingMovementMethod.getInstance());//实现上下滑动

        dbHelper = new LoginDataBaseHelper(this, 1);
        dbHelper.getWritableDatabase();

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        News ns=(News) bundle.getSerializable("twonew");
        tv_newstitle.setText(ns.getnTitle());
        tv_newscontent.setText(ns.getnContent());
        tv_newstype.setText(ns.getnType());
        tv_newsdate.setText(ns.getnDate());
        tv_newscontent.scrollTo(0, 0);

    }
}
