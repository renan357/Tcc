package com.example.home.healthcare;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ClockFragment extends Fragment {

    static View clockview;
    static Calendar dateTime = Calendar.getInstance();
    static EditText textdate;
    static EditText texttime;
    static Button btn_date;
    static Button btn_time;
    static Button btn_alarme;
    static Button btn_cancela;
    static Switch switchclock;
    static Spinner spinner;
    static Intent myIntent;
    static List<String> list;
    static AlarmManager manager;
    static PendingIntent pendingIntent;
    MainActivity mainActivity = new MainActivity();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        clockview = inflater.inflate(R.layout.activity_clock_fragment, container,false);
        textdate = (EditText)clockview.findViewById(R.id.editTextdata);
        texttime = (EditText)clockview.findViewById(R.id.editTexttime);
        btn_date = (Button)clockview.findViewById(R.id.btdata);
        btn_time = (Button)clockview.findViewById(R.id.bthora);
        btn_alarme = (Button)clockview.findViewById(R.id.btalarme);
        btn_cancela = (Button)clockview.findViewById(R.id.btcancela);
        spinner = (Spinner) clockview.findViewById(R.id.spinner);
        switchclock = (Switch)clockview.findViewById(R.id.switch1);
        manager = (AlarmManager)mainActivity.getContext().getSystemService(mainActivity.getContext().ALARM_SERVICE);
        criaspinner();
        btn_alarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchclock.isChecked())
                    startAlarm(true);
                else {
                    startAlarm(false);
                }
            }
        });

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTime();
            }
        });

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDate();
            }
        });

        btn_cancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });

        return clockview;

    }

    private void criaspinner(){

        list = new ArrayList<String>();
        list.add("15 minutos");
        list.add("30 minutos");
        list.add("1 hora");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mainActivity.getContext(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private void updateDate(){
        new DatePickerDialog(mainActivity.getContext(), R.style.TimePickerTheme , d, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateTime(){
        new TimePickerDialog(mainActivity.getContext(), R.style.TimePickerTheme ,t,  dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, monthOfYear);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateTextdate();
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            dateTime.set(Calendar.SECOND,0);
            dateTime.set(Calendar.MILLISECOND,0);
            //dateTime.set(Calendar.AM_PM,Calendar.PM);
            updateTexttime();

        }
    };

    private void updateTextdate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        textdate.setText(dateFormat.format(dateTime.getTime()));
    }

    private void updateTexttime(){
        SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm");
        texttime.setText(horaFormat.format(dateTime.getTime()));
    }

    private void startAlarm(boolean repeat) {

        long tempo;
        String id = spinner.getSelectedItem().toString();
        if (id.equals("15 minutos")){
            tempo = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
        }else  if (id.equals("30 minutos")){
            tempo = AlarmManager.INTERVAL_HALF_HOUR;
        }else{
            tempo = AlarmManager.INTERVAL_HOUR;
        }
        myIntent = new Intent(mainActivity.getContext(),AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(mainActivity.getContext(),0 ,myIntent,0);
        if (texttime.getText().toString().equals("") || textdate.getText().toString().equals("")){
            Toast.makeText(mainActivity.getContext(), "Data/Hora incorreta!",
                    Toast.LENGTH_LONG).show();
        }else{
            if(!repeat){
                manager.set(AlarmManager.RTC_WAKEUP, dateTime.getTimeInMillis() ,pendingIntent);
                Toast.makeText(mainActivity.getContext(), "Alarme Definido!",
                        Toast.LENGTH_LONG).show();
            }
            else {
                manager.setRepeating(AlarmManager.RTC_WAKEUP,dateTime.getTimeInMillis(),tempo,pendingIntent);
                Toast.makeText(mainActivity.getContext(), "Alarme Definido!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private void cancelAlarm(){
        myIntent = new Intent(mainActivity.getContext(),AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(mainActivity.getContext(),0 ,myIntent,0);
        Toast.makeText(mainActivity.getContext(), "Alarmes Cancelados!",
                Toast.LENGTH_LONG).show();
        manager.cancel(pendingIntent);
    }
}
