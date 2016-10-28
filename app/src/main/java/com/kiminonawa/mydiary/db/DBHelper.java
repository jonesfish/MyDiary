package com.kiminonawa.mydiary.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.kiminonawa.mydiary.db.DiaryStructure.DiaryEntry;
import static com.kiminonawa.mydiary.db.DiaryStructure.TopicEntry;

/**
 * Created by daxia on 2016/4/2.
 */
public class DBHelper extends SQLiteOpenHelper {

    /**
     * --------------
     * Version 1 by Daxia：
     * First DB
     */
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mydiary.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";
    private static final String FOREIGN = " FOREIGN KEY ";
    private static final String REFERENCES = " REFERENCES ";

    private static final String SQL_CREATE_TOPIC_ENTRIES =
            "CREATE TABLE " + TopicEntry.TABLE_NAME + " (" +
                    TopicEntry._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                    TopicEntry.COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    TopicEntry.COLUMN_TYPE + INTEGER_TYPE +
                    " )";

    private static final String SQL_CREATE_DIARY_ENTRIES =
            "CREATE TABLE " + DiaryEntry.TABLE_NAME + " (" +
                    DiaryEntry._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                    DiaryEntry.COLUMN_TIME + INTEGER_TYPE + COMMA_SEP +
                    DiaryEntry.COLUMN_TITLE + TEXT_TYPE + COMMA_SEP +
                    DiaryEntry.COLUMN_CONTENT + TEXT_TYPE + COMMA_SEP +
                    DiaryEntry.COLUMN_MOOD + INTEGER_TYPE + COMMA_SEP +
                    DiaryEntry.COLUMN_WEATHER + INTEGER_TYPE + COMMA_SEP +
                    DiaryEntry.COLUMN_ATTACHMENT + INTEGER_TYPE + COMMA_SEP +
                    DiaryEntry.COLUMN_TOPIC__ID + INTEGER_TYPE + COMMA_SEP +
                    FOREIGN + " (" + DiaryEntry.COLUMN_TOPIC__ID + ")" + REFERENCES + TopicEntry.TABLE_NAME + "(" + TopicEntry._ID + ")" +
                    " )";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TOPIC_ENTRIES);
        db.execSQL(SQL_CREATE_DIARY_ENTRIES);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.beginTransaction();
            boolean success = false;
            switch (oldVersion) {
                case 1: {
                    oldVersion++;
                    success = true;
                    break;
                }
            }

            //Check update success
            if (success) {
                db.setTransactionSuccessful();
            }
            db.endTransaction();
        } else {
            onCreate(db);
        }
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
