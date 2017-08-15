package com.example.home.healthcare;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Home on 31/07/2017.
 */

public class BancoActions {
    private Banco banco;
    private SQLiteDatabase db;

    public BancoActions(Context context)
    {
        banco = new Banco(context);
    }

    public void open()
    {
        db = banco.getWritableDatabase();
    }
    public void close()
    {
        banco.close();
    }

    public void inseredata(Userdata c)
    {
        db.execSQL("INSERT INTO paciente (SYS, DIA, PULSE, DATE, TIME) VALUES ('"+c.getSys()+"','"+c.getDia()+"','"+c.getPulse()+"','"+c.getDate()+"','"+c.getTime()+"');");
    }

    public java.util.List<String> getsys() {
        java.util.List<String> l = new ArrayList<String>();
        Cursor c;
        c=db.rawQuery("SELECT SYS FROM paciente;", null);

        while(c.moveToNext()){
            l.add(c.getString(0));
        }
        c.close();
        db.close();
        return l;
    }

    public java.util.List<String> getdia() {
        java.util.List<String> l = new ArrayList<String>();
        Cursor d;
        d=db.rawQuery("SELECT DIA FROM paciente;", null);

        while(d.moveToNext()){
            l.add(d.getString(0));
        }
        d.close();
        db.close();
        return l;
    }

    public java.util.List<String> getpulse() {
        java.util.List<String> l = new ArrayList<String>();
        Cursor c;
        c=db.rawQuery("SELECT PULSE FROM paciente;", null);

        while(c.moveToNext()){
            l.add(c.getString(0));
        }
        c.close();
        db.close();
        return l;
    }

    public java.util.List<String> getdata() {
        java.util.List<String> l = new ArrayList<String>();
        Cursor c;
        c=db.rawQuery("SELECT DATE FROM paciente;", null);

        while(c.moveToNext()){
            l.add(c.getString(0));
        }
        c.close();
        db.close();
        return l;
    }

    public java.util.List<String> gettime() {
        java.util.List<String> l = new ArrayList<String>();
        Cursor c;
        c=db.rawQuery("SELECT TIME FROM paciente;", null);

        while(c.moveToNext()){
            l.add(c.getString(0));
        }
        c.close();
        db.close();
        return l;
    }

    public void delatabanco()
    {
        db.execSQL("DROP TABLE IF EXISTS paciente;");
        db.execSQL("CREATE TABLE paciente (ID INTEGER PRIMARY KEY AUTOINCREMENT, SYS TEXT NOT NULL, DIA TEXT NOT NULL, PULSE TEXT NOT NULL, DATE TEXT NOT NULL, TIME TEXT NOT NULL);");

    }

}
