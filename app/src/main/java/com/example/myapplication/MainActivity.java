package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    private static final String TAG = "MainActivity";

    class NetworkChangeReceiver extends BroadcastReceiver{

        //每当网络状态发生变化时，onReceive方法就会得到执行
        @Override
        public void onReceive(Context context, Intent intent) {

            //获取当前网络状态
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            //根据网络状态弹出Toast提示框
            if(networkInfo != null && networkInfo.isAvailable()){
                Toast.makeText(context , "网络正常" , Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context , "网络异常" , Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {

        //当本活动销毁时
        super.onDestroy();

        //动态注册的广播接收器一定都要取消注册才行，取消注册操作
        unregisterReceiver(networkChangeReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        //当网络发生变化时，系统发出的正是一条值为android.net.conn.CONNECTIVITY_CHANGE的广播。
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        //一个网络变化接收器
        networkChangeReceiver = new NetworkChangeReceiver();
        //将接收器和过滤器进行注册，之后就会接受到系统发出的“android.net.conn.CONNECTIVITY_CHANGE”广播了。
        registerReceiver(networkChangeReceiver , intentFilter);

        //添加按钮逻辑，发送广播
        Button button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
                //新版本的广播需要指定包名
                intent.setPackage(getPackageName());

                //发送有序广播
                sendOrderedBroadcast(intent , null);

                //发送标准广播
                //sendBroadcast(intent);
            }
        });
    }

}