package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.app.adapter.ZhuyeAdapter;
import com.example.app.datacontrol.LoginDataBaseHelper;
import com.example.app.model.News;

import java.util.ArrayList;
import java.util.List;

public class MainYeActivity extends Activity {
    ListView lv_news;
    List<News> newsList, newsList1;
    LoginDataBaseHelper dbHelper;
    News onenew;
    String uname,upassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ye);
        lv_news = (ListView) findViewById(R.id.lv_news);
        dbHelper=new LoginDataBaseHelper(this,1);
        dbHelper.getWritableDatabase();
        newsList=new ArrayList<News>();
        newsList=dbHelper.queryallNews();
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
        ZhuyeAdapter myAdpter=new ZhuyeAdapter(this,newsList1);
        lv_news.setAdapter(myAdpter);


        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        uname=bundle.getString("uname");
        upassword=bundle.getString("password");
        Log.i("用户名",uname+"-"+upassword);


        lv_news.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent=new Intent();
                //以对象方式传递数据
                Bundle bundle=new Bundle();
                News n;
                 n=newsList1.get(position);
                 //用bundle传递对象，其对象所属类必须实现Serializable接口
                bundle.putSerializable("twonew",n);
                intent.putExtras(bundle);
                intent.setClass(MainYeActivity.this,OneNewsActivity.class);
                startActivity(intent);
                }
            }) ;
        }
        public void personOnClick(View v){
             /* （1）Activity间的数据传递
         ①直接向intent对象中传入键值对，相当于Intent对象具有Map键值对功能。//1.通过putExtra方法直接由intent来传递数据
         ②定义一个Bundle对象，在该对象中加入键值对，然后将该对象加入intent中。//2.通过Bundle来捆绑数据，然后用intent来传递Bundle
         ③intent传递Object对象，被传递对象实现Parcelable接口，或者实现Serialiable接口。//以对象方式传递数据
         */

            //2.通过Bundle来捆绑数据，然后用intent来传递Bundle
            Intent intent=new Intent();
            Bundle bundle=new Bundle();
            bundle.putString("uname",uname);
            bundle.putString("password",upassword);
            intent.putExtras(bundle);
            intent.setClass(MainYeActivity.this,PersonXinxiActivity.class);
            startActivity(intent);
        }
}
