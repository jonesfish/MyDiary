package com.kiminonawa.mydiary.main.topic;

import com.kiminonawa.mydiary.R;

/**
 * Created by daxia on 2016/10/17.
 */

public class Memo implements ITopic {

    private String title;


    public Memo(String title){
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getType() {
        return TYPE_MEMO;
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_topic_memo;
    }
}
