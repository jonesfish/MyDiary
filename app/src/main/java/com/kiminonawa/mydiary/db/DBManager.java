package com.kiminonawa.mydiary.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import static com.kiminonawa.mydiary.db.DiaryStructure.DiaryEntry;

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


    public void opeDB() throws SQLiteException {
        mDBHelper = new DBHelper(context);
        // Gets the data repository in write mode
        db = mDBHelper.getWritableDatabase();
    }

    public void closeDB() {
        mDBHelper.close();
    }

    /**
     * Diary
     */

    public long insetDiary(long time, String title, String content,
                           int mood, int weather, boolean attachment) {
        return db.insert(
                DiaryEntry.TABLE_NAME,
                null,
                this.createDiaryCV(time, title, content,
                        mood, weather, attachment));
    }

    public Cursor selectDiary() {
        Cursor c = db.query(DiaryEntry.TABLE_NAME, null, null, null, null, null,
                DiaryEntry.COLUMN_TIME + " DESC");
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
                                        int mood, int weather, boolean attachment) {
        ContentValues values = new ContentValues();
        values.put(DiaryEntry.COLUMN_TITLE, title);
        values.put(DiaryEntry.COLUMN_CONTENT, content);
        values.put(DiaryEntry.COLUMN_TIME, time);
        values.put(DiaryEntry.COLUMN_MOOD, mood);
        values.put(DiaryEntry.COLUMN_WEATHER, weather);
        values.put(DiaryEntry.COLUMN_ATTACHMENT, attachment);
        return values;
    }


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
