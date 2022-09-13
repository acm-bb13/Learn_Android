package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

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

    private static final String TAG = "MainActivity";

    //广播管理器
    private LocalBroadcastManager localBroadcastManager;

    //自定义接收器
    private LocalReceiver localReceiver;

    //intent过滤器
    private IntentFilter intentFilter;

    //自定义广播字串
    private static final String broadcastString = "com.example.broadcasttest.LOCAL_BROADCAST";

    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //当收到信号时

            //弹出Toast消息
            Toast.makeText(context, "receiver local broadcast", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置first_layout为当前布局
        setContentView(R.layout.first_layout);

        //获取实例
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        //添加按钮逻辑，发送广播
        Button button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建intent发送broadcastString广播
                Intent intent = new Intent(broadcastString);

                //Android新机制
                intent.setPackage(getPackageName());

                //发送标准广播
                localBroadcastManager.sendBroadcast(intent);
            }
        });

        //创建新的广播过滤器和自定义本地广播类，发送broadcastString信号。
        intentFilter = new IntentFilter();
        intentFilter.addAction(broadcastString);
        localReceiver = new LocalReceiver();

        //注册本地广播监听器
        localBroadcastManager.registerReceiver(localReceiver , intentFilter);

    }


    @Override
    protected void onDestroy() {
        //当本活动销毁时
        super.onDestroy();

        //注销注册的监听器
        localBroadcastManager.unregisterReceiver(localReceiver);
    }
}