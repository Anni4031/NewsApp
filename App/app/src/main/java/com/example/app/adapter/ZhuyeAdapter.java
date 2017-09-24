package com.example.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.model.News;

import java.util.List;

/**
 * Created by lenovo on 2017/6/14.
 */

public class ZhuyeAdapter extends BaseAdapter {
    List<News> mydata;
    Context mContext;
    News twonew;
    public ZhuyeAdapter(Context mContext, List<News> mydata){
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
        twonew = mydata.get(position);
        tv_titleItem.setText(twonew.getnTitle());
        tv_contentItem.setText(twonew.getnContent().substring(0,15)+"...");
        tv_typeItem.setText(twonew.getnType());
        tv_dateItem.setText(twonew.getnDate());
        return view;
    }
}
