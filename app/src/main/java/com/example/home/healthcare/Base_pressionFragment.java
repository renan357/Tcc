package com.example.home.healthcare;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Base_pressionFragment extends Fragment {

    static View baseview;
    static EditText editsys;
    static EditText editdia;
    static EditText editasys;
    static EditText editadia;
    static EditText editbsys;
    static EditText editbdia;
    static Button btsalva;
    BancoActions banco;
    MainActivity main = new MainActivity();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        baseview = inflater.inflate(R.layout.activity_base_pression_fragment, container, false);
        editsys = (EditText) baseview.findViewById(R.id.editsysnormal);
        editdia = (EditText) baseview.findViewById(R.id.editdianormal);
        editasys = (EditText) baseview.findViewById(R.id.editsysalto);
        editadia = (EditText) baseview.findViewById(R.id.editdiaalto);
        editbsys = (EditText) baseview.findViewById(R.id.editsysbaixo);
        editbdia = (EditText) baseview.findViewById(R.id.editdiabaixo);
        btsalva = (Button) baseview.findViewById(R.id.btsaveconfig);
        banco = new BancoActions(main.getContext());
        Userdata user;
        banco.open();
        user= banco.getbase();
        editsys.setText(user.getNormalsys());
        editdia.setText(user.getNormaldia());
        editasys.setText(user.getAltasys());
        editadia.setText(user.getAltadia());
        editbsys.setText(user.getBaixasys());
        editbdia.setText(user.getBaixadia());
        banco.close();

        btsalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Userdata user = new Userdata();
                user.setNormalsys(editsys.getText().toString());
                user.setNormaldia(editdia.getText().toString());
                user.setAltasys(editasys.getText().toString());
                user.setAltadia(editadia.getText().toString());
                user.setBaixasys(editbsys.getText().toString());
                user.setBaixadia(editbdia.getText().toString());
                banco.open();
                banco.updatebase(user);
                banco.close();
                main.chamainicio();
            }
        });
        return baseview;
    }
}
