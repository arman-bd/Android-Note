package com.softopian.simplenote;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by ASUS-PC on 3/31/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    final String TABLE_NAME = "NoteTable";
    final String COL_TITLE = "Title";
    final String COL_BODY = "Body";

    public DataBaseHelper(Context context) {
        super(context, "SimpleNote", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+
                               " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_TITLE+" TEXT, "+COL_BODY+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor getAll(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT ID, Title, Body FROM "+TABLE_NAME, null);
    }

    public Cursor getSingle(String id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT ID, Title, Body FROM "+TABLE_NAME+" WHERE ID = "+id, null);
    }

    public long Insert(String Title, String Body) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_TITLE, Title);
        values.put(COL_BODY, Body);

        long isInserted = sqLiteDatabase.insert(TABLE_NAME, null, values);

        if(isInserted == -1){
            return -1;
        }else{
            return isInserted;
        }
    }

    public boolean Update(String ID, String Title, String Body) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_TITLE, Title);
        values.put(COL_BODY, Body);

        int isUpdated = sqLiteDatabase.update(TABLE_NAME, values, "ID = "+ID, null);

        if(isUpdated > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean Delete(String ID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int isDeleted = sqLiteDatabase.delete(TABLE_NAME, "ID = "+ID, null);
        if(isDeleted > 0){
            return true;
        }else{
            return false;
        }
    }
}
