package com.waimai.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.waimai.model.Waimai_dingdan;

import java.util.ArrayList;
import java.util.List;

public class DingdanDAO {

    private DBOpenHelper helper;
    private SQLiteDatabase database;

    public DingdanDAO(Context context){
        helper = new DBOpenHelper(context);
        database = helper.getWritableDatabase();
    }

    public void add(Waimai_dingdan waimai_dingdan){
        database.execSQL("insert into waimai_dingdan(Id,status,shop,foodmoney,foodnum,buytime)values(?,?,?,?,?,?)",
                new Object[]{
                        waimai_dingdan.getId(),
                        waimai_dingdan.getStatus(),
                        waimai_dingdan.getShop(),
                        waimai_dingdan.getFoodmoney(),
                        waimai_dingdan.getFoodnum(),
                        waimai_dingdan.getBuytime()
        });
    }

    public void update(Waimai_dingdan waimai_dingdan){
        database.execSQL("update waimai_dingdan set " +
                        "status=?," +
                        "shop=?," +
                        "foodmoney=?," +
                        "foodnum=?," +
                        "buytime=? where Id=?",
                new Object[]{
                        waimai_dingdan.getStatus(),
                        waimai_dingdan.getShop(),
                        waimai_dingdan.getFoodmoney(),
                        waimai_dingdan.getFoodnum(),
                        waimai_dingdan.getBuytime()
                });
    }

    public Waimai_dingdan find(int id){
        Cursor cursor = database.rawQuery("select Id," +
                "status," +
                "shop," +
                "foodmoney," +
                "foodnum," +
                "buytime where Id=?",new String[]{
                String.valueOf(id)
        });
        if (cursor.moveToNext()){
            return new Waimai_dingdan(cursor.getInt(cursor.getColumnIndex("Id")),
                    cursor.getInt(cursor.getColumnIndex("status")),
                    cursor.getString(cursor.getColumnIndex("shop")),
                    cursor.getDouble(cursor.getColumnIndex("foodmoney")),
                    cursor.getInt(cursor.getColumnIndex("foodnum")),
                    cursor.getString(cursor.getColumnIndex("buytime")));
        }
        cursor.close();
        return null;
    }

    public long getCount(){
        Cursor cursor = database.rawQuery("select count(Id) from waimai_dingdan",null);
        if (cursor.moveToNext()){
            return cursor.getLong(0);
        }
        cursor.close();
        return 0;
    }

    public int getMaxId(){
        Cursor cursor = database.rawQuery("select max(Id) from waimai_dingdan",null);
        if (cursor.moveToLast()){
            return cursor.getInt(0);
        }
        cursor.close();
        return 0;
    }

    public List<Waimai_dingdan> getScrollData(int start, int count){
        List<Waimai_dingdan> dingdanLists = new ArrayList<Waimai_dingdan>();
        Cursor cursor = database.rawQuery("select * from waimai_dingdan limit ?,?",new String[]{String.valueOf(start),String.valueOf(count)});
        while(cursor.moveToNext()) {
            dingdanLists.add(new Waimai_dingdan(
                    cursor.getInt(cursor.getColumnIndex("Id")),
                    cursor.getInt(cursor.getColumnIndex("status")),
                    cursor.getString(cursor.getColumnIndex("shop")),
                    cursor.getDouble(cursor.getColumnIndex("foodmoney")),
                    cursor.getInt(cursor.getColumnIndex("foodnum")),
                    cursor.getString(cursor.getColumnIndex("buytime"))
            ));
        }
        cursor.close();
        return dingdanLists;
    }
}
