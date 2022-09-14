package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
    /*
        登录页面的活动，该类主要写了关于登录页面的逻辑
     */

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设定活动的页面为activity_login
        setContentView(R.layout.activity_login);

        //使用getDefaultSharedPreferences方法来获取SharedPreferences
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        //绑定当前的编辑框，用于获取用户输入的账号密码
        EditText accountEdit = (EditText) findViewById(R.id.account);
        EditText passwordEdit = (EditText) findViewById(R.id.password);

        //绑定复选框
        CheckBox rememberPass = (CheckBox) findViewById(R.id.remember_pass);
        boolean isRemember = pref.getBoolean("remember_password" , false);

        //绑定登录按钮
        Button login = (Button) findViewById(R.id.login);

        //如果用户之前设置了记住密码
        if(isRemember){
            //将账号密码读取并显示出来
            String account = pref.getString("account" , "");
            String password = pref.getString("password" , "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }

        //点击登录按钮时的事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取当前输入的账号密码，并进行判断
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if(account.equals("admin") && password.equals("123456")){

                    //检查用户是否勾选记住密码
                    editor = pref.edit();
                    if(rememberPass.isChecked()){
                        //保存账号密码
                        editor.putBoolean("remember_password" , true);
                        editor.putString("account" , account);
                        editor.putString("password" , password);
                    }else {
                        //清除编辑
                        editor.clear();
                    }
                    //应用保存
                    editor.apply();

                    //如果账号密码正确，跳转至主页面活动，并将当前登录页面活动销毁
                    Intent intent = new Intent(LoginActivity.this , MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    //密码输入错误时的提示
                    Toast.makeText(LoginActivity.this , "account or password is invalid" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}