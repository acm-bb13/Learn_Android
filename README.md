## 关于Android学习

本项目用于我的Android项目学习，加油！



## 标题：新增提交模板

修改项：将模板加入到项目当中
备注：如果默认备注无效，可以尝试

```bash
git config commit.template ./commit.template
```



## 日志

### 9月13日 使用本地广播Demo

- 可以明确的知道发送的广播不会离开我们的程序，因此不必担心机密数据泄漏。
- 其他程序也无法将广播发送到我们程序内部，因此不用担心会有安全漏洞的隐患。
- 发送本地广播要比发送系统全局广播更加高效。
- 本地广播无法通过静态注册的方式接收，也不需要。

**😫疑问？同App不同Activity之间可以传递吗？（待解决）**

主要代码都在MainActivity.java里。



### 9月13日 有序广播demo

跟标准广播一致，只需要更改有序广播的发送方式：

```java
Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");

//新版本的广播需要指定包名
intent.setPackage(getPackageName());

//发送有序广播
sendOrderedBroadcast(intent , null);

//发送标准广播
//sendBroadcast(intent);
```



然后要注意，有序广播有优先级。

```java
<intent-filter  android:priority="100">
```





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



