package com.example.demo13_;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * data:2017/8/11
 * author:汉堡(Administrator)
 * function:
 */

public class MyAPP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.init(this);
    }
}
