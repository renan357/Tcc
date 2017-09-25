package com.example.home.healthcare;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Recomendacoes_Fragment extends Fragment {

    static ListView listView;
    static Help_adapter help_adapter;

    static View recView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recView = inflater.inflate(R.layout.activity_recomendacoes__fragment, container, false);
        listView = (ListView) recView.findViewById(R.id.helplist);
        String[] ajudas = new String[]{"1- A Medida da pressão deve ser realizada no braço esquerdo, se formos destros, e no direito, se formos canhotos.",
                "2- A pressão arterial deve ser medida sempre antes de tomar qualquer tipo de medicação",
                "3- Sente-se em uma cadeira de encosto reto com as costas bem apoiadas, os dois pés no chão, as pernas em um ângulo reto descruzadas, " +
                        "adotando uma postura relaxada. O aparelho será colocado na altura do coração adaptando-o bem à circunferência do nosso braço e" +
                        " cuidando para que o indicador seja colocado sobre a artéria braquial. Também deve permitir-nos apoiar o cotovelo na mesa sem forçar a postura.",
                "4- Uma vez bem posicionados, começaremos a medir a pressão arterial, ativando nosso medidor.",
                "5- Uma vez bem posicionados, começaremos a medir a pressão arterial, ativando nosso medidor.",
                "6- Em caso de erro na aferição, aguarde 2 minutos antes de realizar uma nova."};
        help_adapter = new Help_adapter(getActivity(), ajudas);
        listView.setAdapter(help_adapter);
        return recView;
    }
}
