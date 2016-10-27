package com.kiminonawa.mydiary.entries.entries;

import java.util.Date;

/**
 * Created by daxia on 2016/10/17.
 */

public class EntriesEntity {

    private long id;
    private Date createDate;
    private String title;
    private String summary;
    private boolean hasAttachment;


    public EntriesEntity(long id, Date createDate, String title, String summary, boolean hasAttachment) {
        this.id = id;
        this.createDate = createDate;
        this.title = title;
        this.summary = summary;
        this.hasAttachment = hasAttachment;
    }

    public long getId() {
        return id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public boolean hasAttachment() {
        return hasAttachment;
    }
}
