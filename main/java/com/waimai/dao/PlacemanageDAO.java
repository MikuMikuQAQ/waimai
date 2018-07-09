package com.waimai.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.waimai.model.Waimai_placemanage;

import java.util.ArrayList;
import java.util.List;

public class PlacemanageDAO {
    private DBOpenHelper helper;
    private SQLiteDatabase database;

    public PlacemanageDAO(Context context){
        helper = new DBOpenHelper(context);
        database = helper.getWritableDatabase();
    }

    public void add(Waimai_placemanage waimai_placemanage){
        database.execSQL("insert into waimai_place(" +
                "Id,receiptuser,receiptplace) values(?,?,?)",
                new Object[]{
                waimai_placemanage.getId(),
                        waimai_placemanage.getReceiptuser(),
                        waimai_placemanage.getReceiptplace()
                });
    }

    public void update(Waimai_placemanage waimai_placemanage){
        database.execSQL("update waimai_place set receiptuser=?," +
                "receiptplace=? where Id=?",new Object[]{
                waimai_placemanage.getReceiptuser(),
                waimai_placemanage.getReceiptplace(),
                waimai_placemanage.getId()
        });
    }

    public Waimai_placemanage find(int id){
        Cursor cursor = database.rawQuery("select Id," +
                "receiptuser," +
                "receiptplace from waimai_place where Id=?",null);
        if (cursor.moveToNext()){
            return new Waimai_placemanage(cursor.getInt(cursor.getColumnIndex("Id")),
                    cursor.getString(cursor.getColumnIndex("receiptuser")),
                    cursor.getString(cursor.getColumnIndex("receiptplace")));
        }
        cursor.close();
        return null;
    }

    public long getCount(){
        Cursor cursor = database.rawQuery("select count(Id) from waimai_place",null);
        if (cursor.moveToNext()){
            return cursor.getLong(0);
        }
        cursor.close();
        return 0;
    }

    public int getMaxId(){
        Cursor cursor = database.rawQuery("select max(Id) from waimai_place",null);
        if (cursor.moveToLast()){
            return cursor.getInt(0);
        }
        cursor.close();
        return 0;
    }

    public List<Waimai_placemanage> getScrollData(int start,int count){
        List<Waimai_placemanage> placemanageLists = new ArrayList<Waimai_placemanage>();
        Cursor cursor = database.rawQuery("select * from waimai_place limit ?,?",new String[]{String.valueOf(start),String.valueOf(count)});
        while(cursor.moveToNext()) {
            placemanageLists.add(new Waimai_placemanage(cursor.getInt(cursor.getColumnIndex("Id")),
                    cursor.getString(cursor.getColumnIndex("receiptuser")),
                    cursor.getString(cursor.getColumnIndex("receiptplace"))));
        }
        cursor.close();
        return placemanageLists;
    }

    public void delete(int position){
        if (position > 0){
            database.execSQL("delete from waimai_place where Id=?",new String[]{String.valueOf(position)});
        }
    }
}
