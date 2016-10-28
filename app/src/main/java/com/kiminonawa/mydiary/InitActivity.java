package com.kiminonawa.mydiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.kiminonawa.mydiary.main.MainActivity;

public class InitActivity extends Activity {


    private int initTime = 1000; // 3S

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        Handler initHandler = new Handler();
        initHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goMainPageIntent = new Intent(InitActivity.this, MainActivity.class);
                finish();
                InitActivity.this.startActivity(goMainPageIntent);
            }
        }, initTime);

    }
}
