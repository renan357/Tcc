package com.example.home.healthcare;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.UUID;

public class ConnectionThread extends Thread {

    static BluetoothSocket btSocket = null;
    InputStream input = null;
    OutputStream output = null;
    String btDevAddress = null;
    String myUUID = "00001101-0000-1000-8000-00805F9B34FB";
    boolean server;
    static boolean running = false;
    static boolean isConnected = false;
    LeituraFragment leituraFragment = new LeituraFragment();


    public ConnectionThread(){
    }

    public ConnectionThread(String btDevAddress) {

        this.server = false;
        this.btDevAddress = btDevAddress;
    }


    public void run() {


        this.running = true;
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();


        try {


            BluetoothDevice btDevice = btAdapter.getRemoteDevice(btDevAddress);
            btSocket = btDevice.createRfcommSocketToServiceRecord(UUID.fromString(myUUID));

            btAdapter.cancelDiscovery();

            if (btSocket != null) {
                btSocket.connect();
            }

        } catch (IOException e) {


            e.printStackTrace();
            toMainActivity("---N".getBytes());
        }

        if (btSocket != null) {


            isConnected = true;
            toMainActivity("---S".getBytes());

            try {

                input = btSocket.getInputStream();
                output = btSocket.getOutputStream();


                while (running) {


                    byte[] buffer = new byte[1024];
                    int bytes;
                    int bytesRead = -1;


                    do {
                        bytes = input.read(buffer, bytesRead + 1, 1);
                        bytesRead += bytes;
                    } while (buffer[bytesRead] != '\n');


                    toMainActivity(Arrays.copyOfRange(buffer, 0, bytesRead - 1));

                }

            } catch (IOException e) {


                e.printStackTrace();
                toMainActivity("---N".getBytes());
                isConnected = false;
            }
        }
    }






    private void toMainActivity(byte[] data) {

        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putByteArray("data", data);
        message.setData(bundle);
        leituraFragment.handler.sendMessage(message);
    }


    public void write(byte[] data) {

        if (output != null) {
            try {


                output.write(data);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {


            toMainActivity("---N".getBytes());
        }
    }


    public void Cancel() {

        try {

            running = false;
            isConnected = false;
            btSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        running = false;
        isConnected = false;
        return;

    }

    public boolean isConnected() {
        return isConnected;
    }
}