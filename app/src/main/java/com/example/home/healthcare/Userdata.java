package com.example.home.healthcare;

/**
 * Created by Home on 30/07/2017.
 */

public class Userdata {
    int id;


    String sys;
    String dia;
    String pulse;

    public Userdata(int id, String sys , String dia, String bat){
        this.id = id;
        this.sys = sys;
        this.dia = dia;
        this.pulse = bat;
    }

    public Userdata( String sys , String dia, String bat){
        this.sys = sys;
        this.dia = dia;
        this.pulse = bat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }
}
