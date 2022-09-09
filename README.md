## 关于Android学习

本项目用于我的Android项目学习，加油！



## 标题：新增提交模板

修改项：将模板加入到项目当中
备注：如果默认备注无效，可以尝试

```bash
git config commit.template ./commit.template
```



## 日志



### 9月9日 广播效果成功更新

可以注意到，Android新版本对广播进行了限制。新版本的广播需要指定包名。

```java
Button button3 = findViewById(R.id.button_3);
button3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
        //新版本的广播需要指定包名
        intent.setPackage(getPackageName());
        sendBroadcast(intent);
    }
});
```



