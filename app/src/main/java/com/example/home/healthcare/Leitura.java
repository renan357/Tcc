package com.example.home.healthcare;

import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
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

    View myView;
    Switch aswitch;
    BluetoothAdapter btAdapter;
    String sys;
    String dia;
    String pulse;
    static Button bt;
    static TextView txtconexao;
    static TextView txtsys;
    static TextView txtdia;
    static TextView txtpulse;
    static TextView txtpareamento;
    static TextView txtdadosrecebidos;
    ConnectionThread connect;
    Main2Activity main2Activity = new Main2Activity();
    static String a;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_main, container, false);
        aswitch = (Switch)myView.findViewById(R.id.conswitch);
        txtdadosrecebidos= (TextView)myView.findViewById(R.id.Leituratext4);
        txtconexao = (TextView)myView.findViewById(R.id.Leituratextok);
        txtsys = (TextView)myView.findViewById(R.id.Leiturasys);
        txtdia = (TextView)myView.findViewById(R.id.Leituradia);
        txtpulse = (TextView)myView.findViewById(R.id.Leiturapulse);
        txtpareamento = (TextView)myView.findViewById(R.id.Leituratext5);
        bt = (Button)myView.findViewById(R.id.button);

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
           /*         try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    txtpareamento.setText(" ");*/
                }else {
                    txtpareamento.setText("Dispositivo pareado!");
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
                    String sys = parts[0];
                    String dia = parts[1];
                    String pulse = parts[2];
                    txtdia.setText(dataString);
                    txtsys.setText(sys);
                    txtpulse.setText(pulse);
                    main2Activity.gravabanco(dataString);
                }

            }
    };
}