package com.example.home.healthcare;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class Help_fragment extends Fragment {

    static ListView listView;
    static Help_adapter help_adapter;

    static View hView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        hView = inflater.inflate(R.layout.activity_recomendacoes__fragment, container, false);
        listView = (ListView) hView.findViewById(R.id.helplist);
        String[] ajudas = new String[]{"Pressao alta cuidados: ",
                "Controle do peso -  o sobrepeso aumenta o esforço do coração para bombear o sangue. Na prática, o músculo é exigido mais que o normal;",
                "Atividades físicas- exercícios regulares, principalmente os aeróbicos, contribuem para a melhora de todo o sistema circulatório e pulmonar;",
                "Redução de sal - o excesso de sal na dieta leva à retenção de líquidos, acarretando a hipertensão. Por isso, maneire na hora de temperar a comida e diminua o consumo de enlatados e alimentos em conserva;",
                "Bebidas alcoólicas- O álcool em grande quantidade é inimigo feroz da pressão sob controle. Corte as bebidas da sua dieta ou consuma com muita moderação;",
                "Medicamentos: remédios receitados por um médico especialista devem ser tomados com cautela nos horários certos, descuidos podem afetar a pressão constantemente;",
                "Cigarro: o tabaco, em conjunto às outras substâncias tóxicas do cigarro, eleva a pressão imediatamente além de comprometer toda sua saúde. Parar de fumar imediatamente é fundamental para pessoas com pressão alta;",
                "Estresse: ele aparece como resposta do organismo às sobrecargas físicas e emocionais, acarretando a hipertensão e doenças do coração. Controle suas emoções e procure incluir atividades relaxantes na sua rotina.",
                "Pressao baixa cuidados: ",
                "Ao sentir que pressão está baixa, deite-se e levante as pernas, apoiando-as na cabeceira, por exemplo. Isso irá fazer com que o sangue flua melhor para o coração e os sintomas da pressão baixa deverão desaparecer" +
                        " em poucos minutos;",
                "Evite lugares quentes e fechados. Use roupas leves e evite as mudanças bruscas de temperatura;",
                "Beba de 2 a 3 litros de água por dia, a menos que o médico tenha dado outra orientação em relação à quantidade. A água, além de hidratar o organismo, ajuda a manter a circulação sanguínea sob controle;",
                "Faça refeições pouco volumosas a cada 2 ou 3 horas e não saia de casa sem tomar o café da manhã;",
                "Nunca faça exercícios de estômago vazio, pois isto pode facilitar a queda da pressão. O ideal é tomar 1 iogurte ou 1 copo de suco de laranja puro, por exemplo;",
                "Só tome laxantes ou remédios para emagrecer sob orientação médica, pois estes têm como efeito colateral a queda da pressão arterial;",
                "Use meias de compressão elástica. Elas ajudam no retorno do sangue para o coração e, por isso, também são indicadas em caso de pressão baixa;",
                "Pratique atividade física para fortalecer os músculos dos braços e das pernas, pois isto também ajuda o sangue a chegar mais facilmente ao coração e ao cérebro."};
        help_adapter = new Help_adapter(getActivity(), ajudas);
        listView.setAdapter(help_adapter);
        return hView;
    }
}
