package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/*
    继承一个AppCompatActivity的自定义基类
    让之后的所有活动都继承这个类
    这样，只需要在该类编写一个广播接收动作，在任何界面都会接收到
    其中，使用了onResume()方法和onPause()方法，保证只有在
    最前台的活动可以接受到该信息。
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    /*
        自定义了一个接收器继承自BroadcastReceiver
        当收到强制下线的广播消息时，弹出一个无法退出的弹框，强制下线
     */
    private ForceOfflineReceiver receiver;
    class ForceOfflineReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are force to be offline. Please try to login again.");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //当点击按钮时，终结所有活动管理中的活动
                    ActivityCollector.finishAll();
                    //重新启动登录活动
                    Intent intent = new Intent(context , LoginActivity.class);
                    context.startActivity(intent);
                }
            });
            builder.show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //当该活动在最前台时，注册监听器，接收强制下线的广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(String.valueOf(R.string.BCString));
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver , intentFilter);
    }

    @Override
    protected void onPause(){
        super.onPause();
        //当不在最前台后，注销注册的监听器
        if(receiver != null){
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

}
