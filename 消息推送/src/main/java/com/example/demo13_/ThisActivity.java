package com.example.demo13_;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ThisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this);
        String url = getIntent().getStringExtra("url");

    }
}
