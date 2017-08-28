package com.example.home.healthcare;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ClockFragment extends Fragment {

    static View clockview;
    Calendar dateTime = Calendar.getInstance();
    static EditText textdate;
    static EditText texttime;
    static Button btn_date;
    static Button btn_time;
    MainActivity mainActivity = new MainActivity();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        clockview = inflater.inflate(R.layout.activity_clock_fragment, container,false);
        textdate = (EditText)clockview.findViewById(R.id.editTextdata);
        texttime = (EditText)clockview.findViewById(R.id.editTexttime);
        btn_date = (Button)clockview.findViewById(R.id.btdata);
        btn_time = (Button)clockview.findViewById(R.id.bthora);


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

        return clockview;

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

}
