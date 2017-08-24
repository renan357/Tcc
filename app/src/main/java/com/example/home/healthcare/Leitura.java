package com.example.home.healthcare;

import android.app.AlertDialog;
import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Leitura extends Fragment {

    static View myView;
    Switch aswitch;
    BluetoothAdapter btAdapter;
    static ConnectionThread connect;
    static String sys;
    static String dia;
    static String pulse;
    static Button bt;
    static Button btbanco;
    static TextView txtconexao;
    static TextView txtsys;
    static TextView txtdia;
    static TextView txtpulse;
    static TextView txtpareamento;
    static TextView txtdadosrecebidos;
    static int dadosok = 1;
    MainActivity mainActivity = new MainActivity();
    AlertDialog alert;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_leitura, container, false);
        aswitch = (Switch)myView.findViewById(R.id.conswitch);
        txtdadosrecebidos= (TextView)myView.findViewById(R.id.Leituratext4);
        txtconexao = (TextView)myView.findViewById(R.id.Leituratextok);
        txtsys = (TextView)myView.findViewById(R.id.Leiturasys);
        txtdia = (TextView)myView.findViewById(R.id.Leituradia);
        txtpulse = (TextView)myView.findViewById(R.id.Leiturapulse);
        txtpareamento = (TextView)myView.findViewById(R.id.Leituratext5);
        bt = (Button)myView.findViewById(R.id.button);
        btbanco = (Button)myView.findViewById(R.id.btbanco);

        aswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                aswitch.setFocusable(false);
                if (isChecked) {
                    btAdapter = BluetoothAdapter.getDefaultAdapter();
                    if (btAdapter == null) {
                        txtconexao.setText("Hardware Bluetooth não está funcionando.");
                    } else {
                        txtconexao.setText("Hardware Bluetooth está funcionando.");
                    }
                    btAdapter.enable();
                    //  MAC do módulo Bluetooth.
                    connect = new ConnectionThread("00:15:83:35:73:AF");
                    connect.start();
                } else {
                    btAdapter.disable();
                    txtconexao.setText("Conexão bluetooth desativada.");
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                aswitch.setFocusable(true);
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionThread conec = new ConnectionThread();
                if (aswitch.isChecked() == true) {
                    if (txtconexao.getText().equals("Hardware Bluetooth está funcionando.")) {
                        connect.write("restart\n".getBytes());
                    }
                    else{
                        Toast.makeText(getActivity(), "A conexão bluetooth está desativada!",
                                Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "A conexão bluetooth está desativada!",
                            Toast.LENGTH_LONG).show();
                }
                if (conec.isConnected()== false){
                    txtpareamento.setText("Dispositivo não pareado!");
                }
                else {
                    txtpareamento.setText("Dispositivo pareado!");
                }

            }
        });

        btbanco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dadosok == 0){
                    Toast.makeText(getActivity(), "Dados inválidos!",
                            Toast.LENGTH_LONG).show();
                }else{
                    mainActivity.gravabanco(sys,dia,pulse);
                    dadosok = 0;
                }


            }
        });

        return myView;
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            byte[] data = bundle.getByteArray("data");
            String dataString = new String(data);
            if (dataString.equals("---N"));
            else if (dataString.equals("---S"));
            else {
                txtdadosrecebidos.setText("Dados recebidos: ");
                String[] parts =dataString.split(",");
                sys = parts[0];
                dia = parts[1];
                pulse = parts[2];
                sys = sys.replace(" ","");
                dia = dia.replace(" ","");
                pulse = pulse.replace(" ","");
                txtdia.setText(dia);
                txtsys.setText(sys);
                txtpulse.setText(pulse);
                if ((dia.equals("0")) &&(sys.equals("0"))&&(pulse.equals("0")));
                else {
                    int s;
                    int d;
                    s= Integer.valueOf(sys);
                    d= Integer.valueOf(dia);
                    if (s >=14 && d >=9){
                        alerta("alta");
                    }else if (s <=9 && d <=6){
                        alerta("baixa");
                    }
                    dadosok =1;

                }

            }

        }
    };

    private void alerta(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity.getContext());
        builder.setTitle("Alerta");
        builder.setMessage("\n" + "Sua pressao esta " + s + "." + "\n" + "Refaça o teste, em caso de reincidência procure pela pagina de ajuda ");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        alert = builder.create();
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
            }
        });
        alert.show();
    }

}