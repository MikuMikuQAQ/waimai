package com.waimai.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final int version = 1;
    private static final String DBName = "account.db";

    public DBOpenHelper(Context context) {
        super(context, DBName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table waimai_user(Id integer primary key," +
                "username varchar(50)," +
                "password varchar(20)," +
                "balance decimal," +
                "integral integer)");
        sqLiteDatabase.execSQL("create table waimai_dingdan(Id integer primary key," +
                "status integer," +
                "shop varchar(255)," +
                "foodmoney decimal," +
                "foodnum integer," +
                "buytime varchar(30))");
        sqLiteDatabase.execSQL("create table waimai_place(Id integer primary key," +
                "receiptuser varchar(255)," +
                "receiptplace varchar(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
