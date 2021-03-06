package com.example.home.healthcare;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static FragmentManager fragmentManager;
    static BancoActions banco;
    static BancoFragment fragment;
    static Context context;
    String data;
    String hora;

    public final Context getContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        banco = new BancoActions(this);
        checkbasepression();
        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame
                        , new MainFragment())
                .commit();
        context = this;
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
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        fragmentManager = getFragmentManager();
        if (id == R.id.nav_camera) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new MainFragment())
                    .commit();
        } else if (id == R.id.nav_gallery) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new LeituraFragment())
                    .commit();
        } else if (id == R.id.nav_slideshow) {
            listabanco();
        } else if (id == R.id.nav_manage) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new ClockFragment())
                    .commit();
        } else if (id == R.id.nav_share) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new Help_fragment())
                    .commit();
        } else if (id == R.id.nav_send) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new Recomendacoes_Fragment())
                    .commit();
        }else if (id == R.id.nav_config) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new Base_pressionFragment())
                    .commit();
        }else if (id == R.id.nav_relatorio) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new RelatorioFragment())
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void gravabanco(String sys, String dia, String pulse, String status) {
        banco.open();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        data = dateFormat.format(new Date(System.currentTimeMillis())).toString();
        SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");
        Date hora1 = Calendar.getInstance().getTime();
        hora= horaFormat.format(hora1).toString();
        Userdata userdata = new Userdata(sys, dia, pulse, data, hora, status);
        banco.inseredata(userdata);
        banco.close();
    }

    public void checkbasepression() {
        Userdata user;
        banco.open();
        user = banco.getbase();
        banco.close();
        banco.open();
        if (user.getNormalsys() == null) {
            user.setAltadia("90");
            user.setAltasys("140");
            user.setNormaldia("80");
            user.setNormalsys("120");
            user.setBaixasys("90");
            user.setBaixadia("60");
            banco.updatebase(user);
        }
    }
    public void listabanco(){
        banco.open();
        List<String> list = new ArrayList<String>();
        list = banco.getsys();
        String[] sys = list.toArray(new String[list.size()]);
        banco.close();
        banco.open();
        list = banco.getdia();
        String[] dia= list.toArray(new String[list.size()]);
        banco.close();
        banco.open();
        list = banco.getpulse();
        String[] pulse = list.toArray(new String[list.size()]);
        banco.close();
        banco.open();
        list = banco.getdata();
        String[] date =list.toArray(new String[list.size()]);
        banco.close();
        banco.open();
        list = banco.gettime();
        String[] time = list.toArray(new String[list.size()]);
        banco.close();
        banco.open();
        list = banco.getstatus();
        String[] status = list.toArray(new String[list.size()]);
        banco.close();
        fragment = new BancoFragment(this, sys, dia, pulse, date, time, status);
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame
                        ,fragment).commit();
    }

    public void chamainicio(){
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame
                        , new MainFragment())
                .commit();
    }

    public void chamaleitura(){
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame
                        , new LeituraFragment())
                .commit();
    }
}