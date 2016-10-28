package com.kiminonawa.mydiary.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import static com.kiminonawa.mydiary.db.DiaryStructure.DiaryEntry;
import static com.kiminonawa.mydiary.db.DiaryStructure.TopicEntry;

/**
 * Created by daxia on 2016/4/2.
 */
public class DBManager {


    //TODO add SQLiteException
    private Context context;
    private SQLiteDatabase db;
    private DBHelper mDBHelper;

    public DBManager(Context context) {
        this.context = context;
    }

    /**
     * DB IO
     */

    public void opeDB() throws SQLiteException {
        mDBHelper = new DBHelper(context);
        // Gets the data repository in write mode
        db = mDBHelper.getWritableDatabase();
    }

    public void closeDB() {
        mDBHelper.close();
    }


    /**
     * Topic
     */

    public long insertTopic(String name, int type) {
        return db.insert(
                TopicEntry.TABLE_NAME,
                null,
                this.createTopicCV(name, type));
    }

    public Cursor selectTopic() {
        Cursor c = db.query(TopicEntry.TABLE_NAME, null, null, null, null, null,
                TopicEntry._ID + " DESC");
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    private ContentValues createTopicCV(String name, int type) {
        ContentValues values = new ContentValues();
        values.put(TopicEntry.COLUMN_NAME, name);
        values.put(TopicEntry.COLUMN_TYPE, type);
        return values;
    }


    /**
     * Diary
     */
    public long insetDiary(long time, String title, String content,
                           int mood, int weather, boolean attachment, long refTopicId) {
        return db.insert(
                DiaryEntry.TABLE_NAME,
                null,
                this.createDiaryCV(time, title, content,
                        mood, weather, attachment, refTopicId));
    }

    public long delDiary(long diaryId) {
        return db.delete(
                DiaryEntry.TABLE_NAME,
                DiaryEntry._ID + " = ?"
                , new String[]{String.valueOf(diaryId)});
    }


    public Cursor selectDiary(long topicId) {
        Cursor c = db.query(DiaryEntry.TABLE_NAME, null, DiaryEntry.COLUMN_REF_TOPIC__ID + " = ?", new String[]{String.valueOf(topicId)}, null, null,
                DiaryEntry.COLUMN_TIME + " DESC", null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectDiaryByDiaryId(long diaryId) {
        Cursor c = db.query(DiaryEntry.TABLE_NAME, null, DiaryEntry._ID + " = ?", new String[]{String.valueOf(diaryId)}, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    private ContentValues createDiaryCV(long time, String title, String content,
                                        int mood, int weather, boolean attachment, long refTopicId) {
        ContentValues values = new ContentValues();
        values.put(DiaryEntry.COLUMN_TITLE, title);
        values.put(DiaryEntry.COLUMN_CONTENT, content);
        values.put(DiaryEntry.COLUMN_TIME, time);
        values.put(DiaryEntry.COLUMN_MOOD, mood);
        values.put(DiaryEntry.COLUMN_WEATHER, weather);
        values.put(DiaryEntry.COLUMN_ATTACHMENT, attachment);
        values.put(DiaryEntry.COLUMN_REF_TOPIC__ID, refTopicId);
        return values;
    }

    /**
     * Debug
     */

    //For Debug
    public void showCursor(Cursor cursor) {
        for (int i = 0; i < cursor.getCount(); i++) {
            StringBuilder sb = new StringBuilder();
            int columnsQty = cursor.getColumnCount();
            for (int idx = 0; idx < columnsQty; ++idx) {
                sb.append(" " + idx + " = ");
                sb.append(cursor.getString(idx));
                if (idx < columnsQty - 1)
                    sb.append(" ; ");
            }
            Log.e("test", String.format("Row: %d, Values: %s", cursor.getPosition(), sb.toString()));
            cursor.moveToNext();
        }
        //Revert Cursor
        cursor.moveToFirst();
    }

}
