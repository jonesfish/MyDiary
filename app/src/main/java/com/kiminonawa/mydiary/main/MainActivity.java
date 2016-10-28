package com.kiminonawa.mydiary.main;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.kiminonawa.mydiary.R;
import com.kiminonawa.mydiary.db.DBManager;
import com.kiminonawa.mydiary.main.topic.Contacts;
import com.kiminonawa.mydiary.main.topic.Diary;
import com.kiminonawa.mydiary.main.topic.ITopic;
import com.kiminonawa.mydiary.main.topic.Memo;
import com.kiminonawa.mydiary.shared.ViewTools;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogCreateTopic.TopicCreatedCallback {


    /**
     * popup
     */
    private PopupWindow mPopupWindow;
    private ImageView IV_main_popup_add, IV_main_popup_delete;
    /**
     * RecyclerView
     */
    private RecyclerView RecyclerView_topic;
    private MainTopicAdapter mainTopicAdapter;
    private List<ITopic> topicList;

    /**
     * DB
     */
    private DBManager dbManager;

    /**
     * Ui
     */
    private RelativeLayout RL_main_bottom_bar;
    private ImageView IV_main_setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RL_main_bottom_bar = (RelativeLayout) findViewById(R.id.RL_main_bottom_bar);

        IV_main_setting = (ImageView) findViewById(R.id.IV_main_setting);
        IV_main_setting.setOnClickListener(this);

        RecyclerView_topic = (RecyclerView) findViewById(R.id.RecyclerView_topic);

        topicList = new ArrayList<>();
        dbManager = new DBManager(MainActivity.this);

        loadTopic();
        initPopupWindow();
        //Init topic adapter
        initTopicAdapter();
    }

    private void loadTopic() {
        topicList.clear();
        dbManager.opeDB();
        Cursor topicCursor = dbManager.selectTopic();
        for (int i = 0; i < topicCursor.getCount(); i++) {
            switch (topicCursor.getInt(2)) {
                case ITopic.TYPE_CONTACTS:
                    topicList.add(
                            new Contacts(topicCursor.getLong(0), topicCursor.getString(1)));
                    break;
                case ITopic.TYPE_DIARY:
                    topicList.add(
                            new Diary(topicCursor.getLong(0), topicCursor.getString(1)));
                    break;
                case ITopic.TYPE_MEMO:
                    topicList.add(
                            new Memo(topicCursor.getLong(0), topicCursor.getString(1)));
                    break;
            }
            topicCursor.moveToNext();
        }
        topicCursor.close();
        dbManager.closeDB();
    }

    private void initPopupWindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popup_main, null);
        mPopupWindow = new PopupWindow(view);
        mPopupWindow.setWidth(ViewTools.dpToPixel(getResources(), 80));
        mPopupWindow.setHeight(ViewTools.dpToPixel(getResources(), 100));
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        IV_main_popup_add = (ImageView) view.findViewById(R.id.IV_main_popup_add);
        IV_main_popup_delete = (ImageView) view.findViewById(R.id.IV_main_popup_delete);
        IV_main_popup_add.setOnClickListener(this);
        IV_main_popup_delete.setOnClickListener(this);
    }

    private void initTopicAdapter() {
        //Init topic adapter
        LinearLayoutManager lmr = new LinearLayoutManager(this);
        RecyclerView_topic.setLayoutManager(lmr);
        mainTopicAdapter = new MainTopicAdapter(this, topicList);
        RecyclerView_topic.setAdapter(mainTopicAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.IV_main_setting:
                mPopupWindow.showAsDropDown(IV_main_setting, RL_main_bottom_bar.getWidth(), 0);
                break;
            case R.id.IV_main_popup_add:
                DialogCreateTopic dialogCreateTopic = new DialogCreateTopic();
                dialogCreateTopic.setCallBack(this);
                dialogCreateTopic.show(getSupportFragmentManager(), "dialogCreateTopic");
                mPopupWindow.dismiss();
                break;
            case R.id.IV_main_popup_delete:
                break;
        }
    }

    @Override
    public void TopicCreated() {
        loadTopic();
        mainTopicAdapter.notifyDataSetChanged();
    }
}