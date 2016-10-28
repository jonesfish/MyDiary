package com.kiminonawa.mydiary.main.topic;

import com.kiminonawa.mydiary.R;

/**
 * Created by daxia on 2016/10/17.
 */

public class Entries implements ITopic {

    private String title;
    private long id;

    public Entries(long id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getType() {
        return TYPE_ENTRIES;
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_topic_diary;
    }
}
