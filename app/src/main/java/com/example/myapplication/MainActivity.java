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
        本活动主要实现了将文本框中的内容保存，并在活动重新打开时读取出来
     */

    private static final String TAG = "MainActivity";

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置first_layout为当前布局
        setContentView(R.layout.first_layout);

        //绑定文本框
        editText = (EditText) findViewById(R.id.edit);

        //读取数据
        String inputText = load();
        if(!TextUtils.isEmpty(inputText)){
            //当读取到的数据不为空时，将文本框中的文字替换为读取的内容
            editText.setText(inputText);
            editText.setSelection(inputText.length());

            //提示读取成功
            Toast.makeText(this , "Restoring succeeded" , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        //当本活动销毁时
        super.onDestroy();

        //当活动被销毁时，保存数据
        String inputText = editText.getText().toString();
        save(inputText);
    }


    public void save(String inputText){
        //保存数据，通过Java传统方式
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try{
            out = openFileOutput("data" , Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (writer != null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public String load(){
        //读取数据，通过Java传统方式
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();

        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine())!= null){
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader != null){
                try {
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}