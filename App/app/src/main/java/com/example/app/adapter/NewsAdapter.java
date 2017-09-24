package com.example.app.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.app.R;
import com.example.app.ShowNewsActivity;
import com.example.app.UpdateDeleteActivity;
import com.example.app.datacontrol.LoginDataBaseHelper;
import com.example.app.model.News;
import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */

public class NewsAdapter extends BaseAdapter {
    List<News> mydata;
    Context mContext;

    //final int position;
    public NewsAdapter(Context mContext,List<News> mydata){
        this.mydata = mydata ;
        this.mContext = mContext ;
    }

    @Override
    public int getCount() {
        return mydata.size();
    }//item的总行数

    @Override
    public Object getItem(int position) {
        return mydata.get(position);
    }//item对象

    @Override
    public long getItemId(int position) {
        return position ;
    }//item的id

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {//绘制每一个item
        View view = View.inflate(mContext, R.layout.newsitem, null);
        TextView tv_titleItem = (TextView) view.findViewById(R.id.tv_titleItem);
        TextView tv_contentItem = (TextView) view.findViewById(R.id.tv_contentItem);
        TextView tv_typeItem = (TextView) view.findViewById(R.id.tv_typeItem);
        TextView tv_dateItem = (TextView) view.findViewById(R.id.tv_dateItem);
        LinearLayout ll_news=(LinearLayout) view.findViewById(R.id.ll_news);
        News onenew = mydata.get(position);
        tv_titleItem.setText(onenew.getnTitle());
        String partcontent=onenew.getnContent().substring(0,15)+"...";
        final String title=onenew.getnTitle();
        final String content=onenew.getnContent();
        tv_contentItem.setText(partcontent);
        tv_typeItem.setText(onenew.getnType());
        tv_dateItem.setText(onenew.getnDate());
        ll_news.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(mContext).setTitle("删除")
                        .setMessage("你确定删除该条数据").setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        LoginDataBaseHelper dbHelper=new LoginDataBaseHelper(mContext,1);
                        dbHelper.getWritableDatabase();
                        dbHelper.deteleDataBynTitle(title);
                        Intent intent=new Intent();
                        intent.setClass(mContext,ShowNewsActivity.class);
                        mContext.startActivity(intent);
                    }
                }).setNegativeButton("取消",null)
                        .setCancelable(false).show();
                return false;
            }

        });

        ll_news.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                //以对象方式传递数据
                Bundle bundle=new Bundle();
                //用bundle传递对象，其对象所属类必须实现Serializable接口
                bundle.putString("title",title);
                bundle.putString("content",content);
                intent.putExtras(bundle);
                intent.setClass(mContext,UpdateDeleteActivity.class);
                mContext.startActivity(intent);
            }
        });
        return view;
    }
}
