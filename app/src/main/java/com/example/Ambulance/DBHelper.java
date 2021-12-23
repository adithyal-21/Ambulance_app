package com.example.Ambulance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "auth.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
DB.execSQL("create Table Signup (name TEXT primary key,email TEXT,contact TEXT ,dob TEXT,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
DB.execSQL("DROP Table if exists Signup");

    }

    public boolean insertUser(String name,String email,String contact,String dob, String password){
SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        contentValues.put("password",password);
        Cursor cursor = DB.rawQuery("select * from Signup where email=?",new String[] {email});
        if (cursor.getCount() == 0){
            long result = DB.insert("Signup",null,contentValues);
            if (result == -1){
                return false;
            }else{
                return true;
            }

        }
        else{
            return false;
        }

    }

    public boolean updateUser(String name,String email,String contact,String dob){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
       contentValues.put("email",email);
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
//        contentValues.put("password",password);
        Cursor cursor = DB.rawQuery("select * from Signup where email=?",new String[] {email});
        if (cursor.getCount()  == 1){
            long result = DB.update("Signup",contentValues,"email=?",new String[] {email});
            if (result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }

    }

    public boolean deleteUser(String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
       // contentValues.put("name",name);
      contentValues.put("email",email);
//        contentValues.put("contact",contact);
//        contentValues.put("dob",dob);
//        contentValues.put("password",password);
        Cursor cursor = DB.rawQuery("select * from Signup where email=?",new String[] {email});
        if (cursor.getCount() == 1){
            long result = DB.delete("Signup","email=?",new String[] {email});
            if (result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }

    }

    public boolean checkUser(String email,String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        Cursor cursor = DB.rawQuery("select * from Signup where email=? and password=?",new String[] {email,password});
        if(cursor.getCount() == 1){
            return true;
        }
        else{
            return false;
        }
    }
    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("select * from Signup",null);
    }

    public Cursor Individual_data(String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("select * from Signup where email=?",new String[] {email});
    }


}
