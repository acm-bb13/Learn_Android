package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_BOOK =
            "create table Book ( " +
                    "id integer primary key autoincrement , " +
                    "author text , " +
                    "price real , " +
                    "pages integer , " +
                    "name text ) ";

    public static final String CREATE_CATEGORY =
            "create table Category ( " +
                    "id integer primary key autoincrement , " +
                    "category_name text , " +
                    "category_code integer ) ";

    public static final String DROP_TABLE_BOOK = "drop table if exists Book ";
    public static final String DROP_TABLE_CATEGORY = "drop table if exists Category ";

    public Context mContext;

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_BOOK);
        onCreate(db);
    }
}
