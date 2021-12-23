package com.example.Ambulance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminHelper extends SQLiteOpenHelper {
    public AdminHelper(Context context) {
        super(context, "ambulance.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table ambulance_details (name TEXT primary key, type TEXT ,phone TEXT,location TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP Table if exists ambulance_details");
    }
    public boolean add_ambulance(String name,String type,String phone, String location){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("type",type);
        contentValues.put("phone",phone);
        contentValues.put("location",location);
        long result = DB.insert("ambulance_details",null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor get_ambulance(){
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("select * from ambulance_details",null);
    }
}
