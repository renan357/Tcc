package com.example.home.healthcare;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BancoFragment extends Fragment {

    static View bview;
    static ListView listView;
    static Button btlimpa;
    private final Activity context;
    private final String[] sys;
    private final String[] dia;
    private final String[] pulse;
    private final String[] date;
    private final String[] time;
    static list_adapter adapter;
    static BancoActions banco;


    public BancoFragment(Activity context, String[] sys, String[] dia, String[] pulse, String[] date, String[] time){
        this.context = context;
        this.sys = sys;
        this.dia = dia;
        this.pulse = pulse;
        this.date = date;
        this.time = time;
    }


    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        bview = inflater.inflate(R.layout.activity_banco, container ,false);
        listView = (ListView)bview.findViewById(R.id.listview);
        btlimpa = (Button)bview.findViewById(R.id.buttonbanco);
        adapter = new list_adapter(context,sys,dia,pulse,date,time);
        listView.setAdapter(adapter);
         btlimpa.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 banco = new BancoActions(context);
                 banco.open();
                 banco.delatabanco();
                 List<String> list = new ArrayList<String>();
                 list = banco.getsys();
                 String[] syss = list.toArray(new String[list.size()]);
                 String[] dias= list.toArray(new String[list.size()]);
                 String[] pulses= list.toArray(new String[list.size()]);
                 String[] dates=list.toArray(new String[list.size()]);
                 String[] times= list.toArray(new String[list.size()]);
                 adapter = new list_adapter(context,syss,dias,pulses,dates,times);
                 listView.setAdapter(adapter);
                 banco.close();
             }
         });

        return bview;
    }



}
