package com.example.demo13_;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 使用JPushSDK的思路总结:
 * 项目在集成SDK前一定要备份,否则出错了,项目就悲剧了.
 * 1.获取极光的APPKey
 * 2.下载SDK
 * 3.快速集成
 *  a.拷贝libs目录下的所有文件
 *  b.拷贝res目录下的所有文件
 *  c.配置build文件
 *  d.配置AndroidManifest清单文件
 *  e.AppCompatActivity初始化配置
 *  f.写代码
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
