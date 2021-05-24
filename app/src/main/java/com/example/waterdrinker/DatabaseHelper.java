package com.example.waterdrinker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "WaterDB.db";
    public static final String TABLE_NAME = "water_table";
    public static final String ID = "ID";
    public static final String WaterDrank = "WaterDrank";
    public static final String Streak = "Streak";
    public static final String Weight = "Weight";
    public static final String Date = "Date";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NAME + " (ID INTEGER PRIMARY KEY, WaterDrank INTEGER, Streak INTEGER, Weight INTEGER, Date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Looks like you may only be able to enter strings. How stupid lol
    //Sike I'm just dumb as hell!
    public boolean insertData(int id, int waterDrank, int streak, int weight, String date ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(WaterDrank,waterDrank);
        contentValues.put(Streak,streak);
        contentValues.put(Weight,weight);
        contentValues.put(Date,date);
       long result = db.insert(TABLE_NAME,null, contentValues);
       return result != -1;
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ TABLE_NAME + " WHERE "+ ID + "=1;",null);
        return res;
    }

    public boolean setValues(int id, int waterDrank, int streak, int weight, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(WaterDrank,waterDrank);
        contentValues.put(Streak,streak);
        contentValues.put(Weight,weight);
        contentValues.put(Date,date);
        int update = db.update(TABLE_NAME,contentValues,"ID = ?",new String[] { Integer.toString(id) });

        return 0!=update;
    }
    public boolean setWaterDrank(int id, int waterDrank){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WaterDrank,waterDrank);
        int update = db.update(TABLE_NAME,contentValues,"ID = ?",new String[] { Integer.toString(id) });

        return 0!=update;
    }
    public boolean setStreak(int id, int streak){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Streak,streak);
        int update = db.update(TABLE_NAME,contentValues,"ID = ?",new String[] { Integer.toString(id) });

        return 0!=update;
    }
    public boolean setWeight(int id, int weight){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Weight,weight);
        int update = db.update(TABLE_NAME,contentValues,"ID = ?",new String[] { Integer.toString(id) });

        return 0!=update;
    }
    public boolean setDate(int id, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Date,date);
        int update = db.update(TABLE_NAME,contentValues,"ID = ?",new String[] { Integer.toString(id) });

        return 0!=update;
    }
}
