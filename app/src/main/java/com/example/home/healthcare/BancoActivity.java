package com.example.home.healthcare;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BancoActivity extends Fragment {

    static View bview;


    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        bview = inflater.inflate(R.layout.activity_banco, container ,false);

        return bview;
    }
}
