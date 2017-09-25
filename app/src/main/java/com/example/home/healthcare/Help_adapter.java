package com.example.home.healthcare;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class Help_adapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] texto;

    public Help_adapter(Activity context, String[] texto) {
        super(context, R.layout.activity_list_adapter, texto);
        this.context = context;
        this.texto = texto;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.help_adapter, null, true);
        TextView txt1 = (TextView) rowView.findViewById(R.id.txthelp1);
        txt1.setText(texto[position]);
        return rowView;
    }

}