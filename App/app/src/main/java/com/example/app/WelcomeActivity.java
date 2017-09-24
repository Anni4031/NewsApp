package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends Activity {
    Button btn_userlogin,btn_adminlogin;
    //通过handler实现线程之间的通信
    Handler myhandler=new Handler(){
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //根据接收到消息的编号，处理相关的UI操作
            if(msg.what==0x110){
                btn_userlogin.setEnabled(true);
                btn_adminlogin.setEnabled(true);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        btn_userlogin= (Button) findViewById(R.id.btn_userlogin);
        btn_adminlogin= (Button) findViewById(R.id.btn_adminlogin);
        MyThread onthread=new MyThread();
        onthread.start();//启动子线程
    }
    public class MyThread extends Thread{
        public void run(){
            super.run();
            try{
                for(int i=3;i>=0;i--){
                    Thread.sleep(1000);//线程休眠1秒
                }
                //在子线程中发送消息
                myhandler.sendEmptyMessage(0x110);
            }catch (InterruptedException e){
                e.printStackTrace();}
        }
    }
    public void userOnClick(View view){
            //显式意图
            Intent intent=new Intent();//创建一个意图对象
        //setClass函数的第一个参数是一个Context对象
        //Context是一个类，Activity是Context类的子类，也就是说，所有的Activity对象，都可以向上转型为Context对象
        // setClass函数的第二个参数是一个Class对象，在当前场景下，应该传入需要被启动的Activity类的class对象
            intent.setClass(WelcomeActivity.this,LoginActivity.class);
             startActivity(intent);
    }
    public void adminOnClick(View view){
        Intent intent=new Intent();
        intent.setClass(WelcomeActivity.this,AdminActivity.class);
        startActivity(intent);
    }
}
