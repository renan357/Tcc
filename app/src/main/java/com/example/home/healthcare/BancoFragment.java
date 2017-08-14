package com.example.home.healthcare;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

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
    list_adapter adapter;
    MainActivity mainActivity = new MainActivity();

  // public BancoFragment(){

   // }
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
                 mainActivity.deletabanco();
             }
         });

        return bview;
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();


            byte[] data = bundle.getByteArray("data");
            String dataString = new String(data);

        }
    };

}
