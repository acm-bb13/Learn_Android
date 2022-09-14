package com.example.myapplication;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/*
    一个基础的活动管理类
    通过一个List，实现基本的增加和删除
    以及一个将所有活动终止的功能
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity : activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
