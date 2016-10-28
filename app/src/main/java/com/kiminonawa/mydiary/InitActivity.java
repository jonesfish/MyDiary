package com.kiminonawa.mydiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.kiminonawa.mydiary.db.DBManager;
import com.kiminonawa.mydiary.main.MainActivity;
import com.kiminonawa.mydiary.main.topic.ITopic;
import com.kiminonawa.mydiary.shared.SPFManager;

public class InitActivity extends Activity {


    private int initTime = 3000; // 3S

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        loadSampleData();
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

    private void loadSampleData() {
        if (SPFManager.getFirstRun(InitActivity.this)) {

            DBManager dbManager = new DBManager(InitActivity.this);
            dbManager.opeDB();

            //Insert sample topic
            dbManager.insertTopic("禁止事項", ITopic.TYPE_MEMO);
            dbManager.insertTopic("お知らせ", ITopic.TYPE_MEMO);
            dbManager.insertTopic("DIARY", ITopic.TYPE_DIARY);
            dbManager.insertTopic("電話番号", ITopic.TYPE_CONTACTS);


            //Insert sample diary

            dbManager.insetDiary(1475665800000L, "東京生活3❤",
                    "There are many coffee shop in Tokyo!",
                    0, 0, true, 3);
            dbManager.insetDiary(1475241600000L, "No Title",
                    "My name is TAKI , I am a man!",
                    0, 0, true, 3);
            dbManager.insetDiary(1475144400000L, "東京生活2",
                    "Today is second day , I like tokyo!",
                    0, 0, false, 3);

            dbManager.closeDB();
            SPFManager.setFirstRun(InitActivity.this, false);
        }
    }
}
