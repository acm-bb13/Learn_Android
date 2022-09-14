package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    /*
        本活动主要是通过两个按钮，将数据保存，并以log的形式读取后输出
     */

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置first_layout为当前布局
        setContentView(R.layout.first_layout);

        //写入按钮
        Button saveData = (Button) findViewById(R.id.save_data);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data" , MODE_PRIVATE).edit();
                editor.putString("name" , "Tom");
                editor.putInt("age" , 28);
                editor.putBoolean("married" , true);
                editor.apply();
            }
        });

        //读取按钮
        Button restoreData = (Button) findViewById(R.id.restore_data);
        restoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("data" , MODE_PRIVATE);
                String name = pref.getString("name" , "");
                int age = pref.getInt("age" ,0);
                boolean married = pref.getBoolean("married" , false);
                Log.d(TAG, "onClick: name is " + name);
                Log.d(TAG, "onClick: age is " + age);
                Log.d(TAG, "onClick: married is " + married);
            }
        });

    }

    @Override
    protected void onDestroy() {
        //当本活动销毁时
        super.onDestroy();

    }

}