package com.example.home.healthcare;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Home on 26/09/2017.
 */

public class Help_fragment extends Fragment {

    static ListView listView;
    static Help_adapter help_adapter;

    static View hView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        hView = inflater.inflate(R.layout.activity_recomendacoes__fragment, container, false);
        listView = (ListView) hView.findViewById(R.id.helplist);
        String[] ajudas = new String[]{"1- A Medida da pressão deve ser realizada no braço esquerdo, se formos destros, e no direito, se formos canhotos.",
                "2- Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaeto com as costas bem apoiadas, os dois pés no chão, as pernas em um ângulo reto descruzadas, " +
                        "adotando uma postura relaxada. O aparelho será colocado na altura do coração adaptando-o bem à circunferência do nosso braço e" +
                        " cuidando para que o dasdasdindicador seja colocado sobre a artéria braquial. Também deve permitir-nos apoiar o cotovelo na mesa sem forçar a postura.",
                "4- Uma vez asd posicionado, a pressão arterial pode começar a ser medida ativando o medidor.",
                "5- Em caso dasde erro na aferição, aguarde 2 minutos antes de realizar uma nova.",
                "6- Aasdasdraasd, ultilize o seu smartphone para gravar os dados obtidos."};
        help_adapter = new Help_adapter(getActivity(), ajudas);
        listView.setAdapter(help_adapter);
        return hView;
    }
}
