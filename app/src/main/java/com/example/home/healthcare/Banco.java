package com.example.home.healthcare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Home on 31/07/2017.
 */

public class Banco extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "banco.db";
    public static final int DATABASE_VERSION = 1;

    public Banco(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE paciente (ID INTEGER PRIMARY KEY AUTOINCREMENT, SYS TEXT NOT NULL, DIA TEXT NOT NULL, PULSE TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS paciente;");
        onCreate(db);
    }
}
