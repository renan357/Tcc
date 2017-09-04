package com.example.home.healthcare;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


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

    public void updatebase (Userdata user){
        db.execSQL("DROP TABLE IF EXISTS pressaobase;");
        db.execSQL("CREATE TABLE pressaobase (ID INTEGER PRIMARY KEY AUTOINCREMENT, SYS TEXT NOT NULL, DIA TEXT NOT NULL, ASYS TEXT NOT NULL, ADIA TEXT NOT NULL, BSYS TEXT NOT NULL, BDIA TEXT NOT NULL);");
        db.execSQL("INSERT INTO pressaobase (SYS, DIA, ASYS, ADIA, BSYS, BDIA) VALUES ('"+user.getNormalsys() +"','"+
                user.getNormaldia()+"','"+user.getAltasys()+"','"+user.getAltadia()+"','"+user.getBaixasys()+"','"+user.getBaixadia()+"');");
    }
    public Userdata getbase (){
        Userdata user = new Userdata();
        Cursor c;
        c=db.rawQuery("SELECT SYS FROM pressaobase;", null);
        while(c.moveToNext()){
            user.setNormalsys(c.getString(0));
        }
        c=db.rawQuery("SELECT DIA FROM pressaobase;", null);
        while(c.moveToNext()){
            user.setNormaldia(c.getString(0));
        }

        c=db.rawQuery("SELECT ASYS FROM pressaobase;", null);
        while(c.moveToNext()){
            user.setAltasys(c.getString(0));
        }
        c=db.rawQuery("SELECT ADIA FROM pressaobase;", null);
        while(c.moveToNext()){
            user.setAltadia(c.getString(0));
        }
        c=db.rawQuery("SELECT BSYS FROM pressaobase;", null);
        while(c.moveToNext()){
            user.setBaixasys(c.getString(0));
        }
        c=db.rawQuery("SELECT BDIA FROM pressaobase;", null);
        while(c.moveToNext()){
            user.setBaixadia(c.getString(0));
        }
        c.close();
        db.close();
        return user;
    }

    public void delatabanco()
    {
        db.execSQL("DROP TABLE IF EXISTS paciente;");
        db.execSQL("CREATE TABLE paciente (ID INTEGER PRIMARY KEY AUTOINCREMENT, SYS TEXT NOT NULL, DIA TEXT NOT NULL, PULSE TEXT NOT NULL, DATE TEXT NOT NULL, TIME TEXT NOT NULL);");

    }
    public void deletabase()
    {
        db.execSQL("DROP TABLE IF EXISTS pressaobase;");
        db.execSQL("CREATE TABLE pressaobase (ID INTEGER PRIMARY KEY AUTOINCREMENT, SYS TEXT NOT NULL, DIA TEXT NOT NULL, ASYS TEXT NOT NULL, ADIA TEXT NOT NULL, BSYS TEXT NOT NULL, BDIA TEXT NOT NULL);");

    }
}
