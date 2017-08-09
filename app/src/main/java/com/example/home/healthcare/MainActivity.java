package com.example.home.healthcare;

import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Fragment {

    TextView statusMessage;
    TextView counterMessage;
    ConnectionThread connect;
    BluetoothAdapter btAdapter;
    View myView;

  //  @Override
    //public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
     //   super.onViewCreated(view, savedInstanceState);
   //// }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_main, container, false);
        return myView;

    }


  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        counterMessage = (TextView) findViewById(R.id.text);
        //counterMessage = (TextView) findViewById(R.id.counterMessage);

        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter == null) {
            counterMessage.setText("Que pena! Hardware Bluetooth não está funcionando :(");
        } else {
            counterMessage.setText("Ótimo! Hardware Bluetooth está funcionando :)");
        }

        btAdapter.enable();
        //  MAC do módulo Bluetooth.
        connect = new ConnectionThread("00:15:83:35:73:AF");
        connect.start();


    }*/



  /*  public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
   }*/

    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            /* Esse método é invocado na Activity principal
                sempre que a thread de conexão Bluetooth recebe
                uma mensagem.
             */
            Bundle bundle = msg.getData();
            byte[] data = bundle.getByteArray("data");
            String dataString= new String(data);

            /* Aqui ocorre a decisão de ação, baseada na string
                recebida. Caso a string corresponda à uma das
                mensagens de status de conexão (iniciadas com --),
                atualizamos o status da conexão conforme o código.
             */
            if(dataString.equals("---N"));
              // statusMessage.setText("Ocorreu um erro durante a conexão D:");
            else if(dataString.equals("---S"));
              // statusMessage.setText("Conectado :D");
            else {

                /* Se a mensagem não for um código de status,
                    então ela deve ser tratada pelo aplicativo
                    como uma mensagem vinda diretamente do outro
                    lado da conexão. Nesse caso, simplesmente
                    atualizamos o valor contido no TextView do
                    contador.
                 */
              //  counterMessage.setText(dataString);
            }

        }
    };


    public void GET(View view) {
        connect.write("restart\n".getBytes());
    }
}