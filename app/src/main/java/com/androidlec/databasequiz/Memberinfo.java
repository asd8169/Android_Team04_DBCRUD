package com.androidlec.databasequiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Memberinfo extends SQLiteOpenHelper {

    final static String TAG = "MemberInfo";

    public Memberinfo(@Nullable Context context) {
        super(context, "StudentInfo.db", null, 1);
    }


    //데이터베이스 추가
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(TAG,"onCreate()");
        //테이블 생성
        String query = "CREATE TABLE member(sdNo INTEGER PRIMARY KEY AUTOINCREMENT, sdName TEXT, sdDept TEXT, sdTel TEXT)";
        db.execSQL(query);
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(TAG,"upgrade()");
        String query = "DROP TABLE IF EXISTS member;";
        db.execSQL(query);
        onCreate(db);

    }
}
