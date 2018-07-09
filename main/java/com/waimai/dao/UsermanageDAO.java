package com.waimai.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.waimai.model.Waimai_usermanage;

public class UsermanageDAO {

    private DBOpenHelper helper;
    private SQLiteDatabase database;

    public UsermanageDAO(Context context){
        helper = new DBOpenHelper(context);
        database = helper.getWritableDatabase();
    }

    public void add(Waimai_usermanage waimai_usermanage){
        database.execSQL("insert into waimai_user(" +
                "Id,username,password,balance,integral)" +
                "values(?,?,?,?,?)",new Object[]{
                waimai_usermanage.getId(),
                waimai_usermanage.getUsername(),
                waimai_usermanage.getPassword(),
                waimai_usermanage.getBalance(),
                waimai_usermanage.getIntegral()
        });
    }

    public void update(Waimai_usermanage waimai_usermanage){
        database.execSQL("update waimai_user set username=?," +
                "password=?," +
                "balance=?," +
                "integral=? where Id=?",new Object[]{
                waimai_usermanage.getUsername(),
                waimai_usermanage.getPassword(),
                waimai_usermanage.getBalance(),
                waimai_usermanage.getIntegral(),
                waimai_usermanage.getId()
        });
    }

    public void updateMoney(Double balance,int id){
        database.execSQL("update waimai_user set balance=? where Id=?",new Object[]{
                balance,
                id
        });
    }

    public void updatePwd(String pwd,int id){
        database.execSQL("update waimai_user set password=? where Id=?",new Object[]{
                pwd,
                id
        });
    }

    public void updateIntegral(int integral,int id){
        database.execSQL("update waimai_user set password=? where Id=?",new Object[]{
                integral,
                id
        });
    }

    public Waimai_usermanage find(){
        Cursor cursor = database.rawQuery("select Id," +
                "username," +
                "password," +
                "balance," +
                "integral from waimai_user ",null);
        if (cursor.moveToNext()){
            return new Waimai_usermanage(cursor.getInt(cursor.getColumnIndex("Id")),
                    cursor.getString(cursor.getColumnIndex("username")),
                    cursor.getString(cursor.getColumnIndex("password")),
                    cursor.getDouble(cursor.getColumnIndex("balance")),
                    cursor.getInt(cursor.getColumnIndex("integral")));
        }
        cursor.close();
        return null;
    }

    public long getCount(){
        Cursor cursor = database.rawQuery("select count(Id) from waimai_user",null);
        if (cursor.moveToNext()){
            return cursor.getLong(0);
        }
        cursor.close();
        return 0;
    }
}
