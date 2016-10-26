package com.kiminonawa.mydiary.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kiminonawa.mydiary.R;
import com.kiminonawa.mydiary.main.topic.Contacts;
import com.kiminonawa.mydiary.main.topic.Entries;
import com.kiminonawa.mydiary.main.topic.ITopic;
import com.kiminonawa.mydiary.main.topic.Memo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView RecyclerView_topic;
    private MainTopicAdapter mainTopicAdapter;
    private List<ITopic> topicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView_topic = (RecyclerView) findViewById(R.id.RecyclerView_topic);

        topicList = new ArrayList<>();
        //Test data
        topicList.add(new Contacts("Important call number"));
        topicList.add(new Entries("Taki"));
        topicList.add(new Memo("Notice Memo"));
        topicList.add(new Memo("Notice Memo2"));
        topicList.add(new Memo("Notice Memo3"));
        topicList.add(new Memo("Notice Memo4"));
        topicList.add(new Memo("Notice Memo5"));
        topicList.add(new Memo("Notice Memo6"));

        //Init topic adapter
        LinearLayoutManager lmr = new LinearLayoutManager(this);
        RecyclerView_topic.setLayoutManager(lmr);
        mainTopicAdapter = new MainTopicAdapter(this, topicList);
        RecyclerView_topic.setAdapter(mainTopicAdapter);
    }
}
