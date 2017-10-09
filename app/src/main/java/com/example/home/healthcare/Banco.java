package com.example.home.healthcare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Banco extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "banco.db";
    public static final int DATABASE_VERSION = 4;

    public Banco(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE paciente (ID INTEGER PRIMARY KEY AUTOINCREMENT, SYS TEXT NOT NULL, DIA TEXT NOT NULL, PULSE TEXT NOT NULL, DATE TEXT NOT NULL, TIME TEXT NOT NULL, STATUS TEXT NOT NULL);");
        db.execSQL("CREATE TABLE pressaobase (ID INTEGER PRIMARY KEY AUTOINCREMENT, SYS TEXT NOT NULL, DIA TEXT NOT NULL, ASYS TEXT NOT NULL, ADIA TEXT NOT NULL, BSYS TEXT NOT NULL, BDIA TEXT NOT NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS paciente;");
        db.execSQL("DROP TABLE IF EXISTS pressaobase;");
        onCreate(db);
    }
}
