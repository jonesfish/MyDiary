package com.kiminonawa.mydiary.db;

import android.provider.BaseColumns;

/**
 * Created by daxia on 2016/4/9.
 */
public class DiaryStructure {


    public static abstract class DiaryEntry implements BaseColumns {
        public static final String TABLE_NAME = "diary_entry";
        public static final String COLUMN_TITLE = "diary_count";
        public static final String COLUMN_CONTENT = "diary_content";
        public static final String COLUMN_TIME = "diary_time";
        public static final String COLUMN_MOOD = "diary_mood";
        public static final String COLUMN_WEATHER = "diary_weather";
        public static final String COLUMN_ATTACHMENT = "diary_attachment";

    }

}
