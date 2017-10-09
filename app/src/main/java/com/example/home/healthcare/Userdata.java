package com.example.home.healthcare;


public class Userdata {

    int id;
    String sys;
    String dia;
    String pulse;
    String date;
    String time;
    String normalsys;
    String normaldia;
    String altasys;
    String altadia;
    String baixasys;
    String baixadia;
    String status;

    public Userdata(String sys, String dia, String bat, String date, String time, String status) {
        this.sys = sys;
        this.dia = dia;
        this.pulse = bat;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public Userdata(){

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNormalsys() {
        return normalsys;
    }

    public void setNormalsys(String normalsys) {
        this.normalsys = normalsys;
    }

    public String getNormaldia() {
        return normaldia;
    }

    public void setNormaldia(String normaldia) {
        this.normaldia = normaldia;
    }

    public String getAltasys() {
        return altasys;
    }

    public void setAltasys(String altasys) {
        this.altasys = altasys;
    }

    public String getAltadia() {
        return altadia;
    }

    public void setAltadia(String altadia) {
        this.altadia = altadia;
    }

    public String getBaixasys() {
        return baixasys;
    }

    public void setBaixasys(String baixasys) {
        this.baixasys = baixasys;
    }

    public String getBaixadia() {
        return baixadia;
    }

    public void setBaixadia(String baixadia) {
        this.baixadia = baixadia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
