## 关于Android学习

本项目用于我的Android项目学习，加油！



## 提交模板

将模板加入到项目当中

```bash
git config commit.template ./commit.template
```



## 日志

### 9月14日 从SharedPreferences中写入读取数据

```bash
git checkout v0.6
```

从SharedPreferences保存的数据，是以键值对的形式保存的，并且支持多种类型的数据，存储的是字符串，那么读出来的也将是字符串。

本次主要是实现了关于SharedPreferences的写入读取



### 9月14日 文件存储Demo

```bash
git checkout v0.5
```

比较传统的Java的存储文件方式。只适合存储一些简单的数据或者二进制数据。

注意这里指定文件名不可以包含路径，因为所有的文件都是默认存储到

```
/data/data/<package name>/files/
```

目录下的。



### 9月14日 实现强制下线功能Demo

```bash
git checkout v0.4
```

这个Demo的基本原理。

该活动为了实现所有页面收到广播后都会弹出强制下线的对话框，

编写了一个基类让所有的活动都继承它，然后再在这个基类活动里，

写了一个监听器接收广播，只有当该活动为最前台时才会生效。

然后写了一个管理类ActivityCollector，用来记录所有创建的活动并

提供了一个将所有活动结束的方法。



### 9月13日 使用本地广播Demo

```bash
git checkout v0.3
```

- 可以明确的知道发送的广播不会离开我们的程序，因此不必担心机密数据泄漏。
- 其他程序也无法将广播发送到我们程序内部，因此不用担心会有安全漏洞的隐患。
- 发送本地广播要比发送系统全局广播更加高效。
- 本地广播无法通过静态注册的方式接收，也不需要。

**😫疑问？同App不同Activity之间可以传递吗？（待解决）**

主要代码都在MainActivity.java里。



### 9月13日 有序广播demo

```bash
git checkout v0.2
```

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

```bash
git checkout v0.1
```

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



