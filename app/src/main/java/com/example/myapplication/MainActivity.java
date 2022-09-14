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

public class MainActivity extends BaseActivity {
    /*
        用户登录后的页面活动，该活动就只需要实现一个发送强制下线广播功能即可
     */

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置first_layout为当前布局
        setContentView(R.layout.first_layout);

        //设置一个按钮动作，当用户点击按钮时，发送一个强制下线的标准广播
        Button forceOffline = (Button) findViewById(R.id.force_offline);
        forceOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(String.valueOf(R.string.BCString));
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        //当本活动销毁时
        super.onDestroy();
    }
}