package com.example.home.healthcare;

import android.app.FragmentManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ConnectionThread connect;
    BluetoothAdapter btAdapter;
    String sys;
    String dia;
    String pulse;
    FragmentManager fragmentManager = getFragmentManager();
    TextView txtconexao;
    TextView txtsys;
    TextView txtdia;
    TextView txtpulse;
    TextView txtdadosrecebidos;
    Switch aswitch ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        txtconexao = (TextView)findViewById(R.id.Leituratextok);
        txtsys = (TextView)findViewById(R.id.Leiturasys);
        txtdia = (TextView)findViewById(R.id.Leituradia);
        txtpulse = (TextView)findViewById(R.id.Leiturapulse);
        txtdadosrecebidos = (TextView)findViewById(R.id.Leituratext4);
        aswitch = (Switch)findViewById(R.id.conswitch);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        fragmentManager = getFragmentManager();

        if (id == R.id.nav_camera) {
            Intent i = new Intent(Main2Activity.this,Main2Activity.class);
            startActivity(i);
            finish();
        } else if (id == R.id.nav_gallery) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new Leitura())
                    .commit();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            byte[] data = bundle.getByteArray("data");
            String dataString = new String(data);
            if (dataString.equals("---N")){
                Leitura leitura = new Leitura();
                Bundle b = new Bundle();
                sys= "xD";
                dia = "xD";
                pulse= "xD";
                b.putString("sys",sys);
                b.putString("dia",dia);
                b.putString("pulse",pulse);
                leitura.setArguments(b);
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame
                                , leitura)
                        .commit();
            }
            /*else if (dataString.equals("---S"));
            else {
                txtdadosrecebidos.setText("Dados recebidos: ");
                leitura.set(dataString);
                //// String[] parts = dataString.split("(.)");
                // String sys = parts[0];
                //String dia = parts[1];
                //  String pulse = parts[2];
                //txtdia.setText(dia);
                // txtsys.setText(sys);
                //   txtpulse.setText(pulse);
                gravabanco(dataString);
            }*/

        }
    };

    public void gravabanco (String s){
    }

}

