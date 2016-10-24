package com.kiminonawa.mydiary.entries.entries;

import java.util.Date;

/**
 * Created by daxia on 2016/10/17.
 */

public class EntriesEntity {

    private Date createDate;
    private String title;
    private String summary;
    private boolean hasAttachment;


    public EntriesEntity(Date createDate, String title, String summary, boolean hasAttachment) {
        this.createDate = createDate;
        this.title = title;
        this.summary = summary;
        this.hasAttachment = hasAttachment;
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
