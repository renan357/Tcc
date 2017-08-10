package com.example.home.healthcare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
/*
    public java.util.List<String> selecionaTodosNomes() {
        java.util.List<String> l = new ArrayList<String>();
        Cursor c;
        c=db.rawQuery("SELECT NOME FROM cadastros;", null);

        while(c.moveToNext()){
            Userdata cad  =new Userdata(c.getString(0));
            l.add(cad.getNome());
        }
        c.close();
        db.close();
        return l;
    }*/

    public void deleteCabra(int id)
    {
        db.execSQL("DELETE FROM paciente WHERE ID = ('"+id+"');");
    }

  /*  public void updateCabra(Userdata c)
    {
        db.execSQL("UPDATE cadastros SET ENDERECO = '"+c.getEndereco()+"', TELEFONE = '"+c.getTelefone()+"'  WHERE NOME = ('"+c.getNome()+"');");
    }*/
   /* public java.util.List<String> selecioUmCabra(String s )
    {
        java.util.List<String> l = new ArrayList<String>();
        Cursor c;
        c=db.rawQuery("SELECT NOME, ENDERECO, TELEFONE FROM cadastros WHERE NOME = '"+s+"';", null);

        while(c.moveToNext())
        {
            l.add(c.getString(0));
            l.add(c.getString(1));
            l.add(c.getString(2));
        }
        c.close();
        db.close();
        return l;

    }*/
}
