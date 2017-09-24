package com.example.app;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.example.app.adapter.NewsAdapter;
import com.example.app.datacontrol.LoginDataBaseHelper;
import com.example.app.model.News;

import java.util.ArrayList;
import java.util.List;

public class ShowNewsActivity extends Activity {
    ListView lv_newslist;
    List<News> newsList, newsList1;
    LoginDataBaseHelper dbHelper;
    //News news;
    News onenew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);
        lv_newslist = (ListView) findViewById(R.id.lv_newslist);
        dbHelper=new LoginDataBaseHelper(this,1);
        dbHelper.getWritableDatabase();
        newsList=new ArrayList<News>();
        newsList=dbHelper.queryall();
        newsList1=new ArrayList<News>();
        for(int i=0;i<newsList.size();i++){
            onenew=newsList.get(i);
            onenew.setnId(onenew.getnId());
            onenew.setnTitle(onenew.getnTitle());
            onenew.setnContent(onenew.getnContent());
            onenew.setnType(onenew.getnType());
            onenew.setnDate(onenew.getnDate());
            newsList1.add(onenew);
            Log.i("shuju",onenew.getnTitle());
        }
        NewsAdapter myAdpter=new NewsAdapter(this,newsList1);
        lv_newslist.setAdapter(myAdpter);

    }
    public void addOnClick(View v){
        Intent intent = new Intent(ShowNewsActivity.this, AddNewsActivity.class);
        startActivity(intent);
    }

    public void UserOnClick(View v){
        Intent intent = new Intent(ShowNewsActivity.this, ShowUsersActivity.class);
        startActivity(intent);
    }

}
