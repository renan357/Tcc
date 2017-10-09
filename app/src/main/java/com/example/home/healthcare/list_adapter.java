package com.example.home.healthcare;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class list_adapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] sys;
    private final String[] dia;
    private final String[] pulse;
    private final String[] date;
    private final String[] time;
    private final String[] status;

    public list_adapter(Activity context, String[] sys, String[] dia, String[] pulse, String[] date, String[] time, String[] status) {
        super(context, R.layout.activity_list_adapter, sys);
        this.context= context;
        this.sys = sys;
        this.dia = dia;
        this.pulse = pulse;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.activity_list_adapter, null, true);
        TextView txt1 = (TextView)rowView.findViewById(R.id.txtadapter1);
        TextView txt2 = (TextView)rowView.findViewById(R.id.txtadapter2);
        TextView txt3 = (TextView)rowView.findViewById(R.id.txtadapter3);
        TextView txt4 = (TextView)rowView.findViewById(R.id.txtadapter4);
        TextView txt5 = (TextView)rowView.findViewById(R.id.txtadapter5);
        TextView txt6 = (TextView) rowView.findViewById(R.id.txtadapter6);
        txt1.setText("Pressão: "+sys[position]);
        txt2.setText("/"+dia[position]);
        txt3.setText("Batimentos: "+pulse[position]);
        txt4.setText(date[position]);
        txt5.setText(time[position]);
        txt6.setText(status[position]);
        return rowView;
    }
}
