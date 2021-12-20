package com.example.uchpraktika0102;

public class ModelResult {
    private String name;
    private String res;
    private String normal;
    private String date;

    ModelResult(String name, String res, String normal, String date){
        this.name = name;
        this.res = res;
        this.normal = normal;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getNoram() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public  String toString(){

        return name+"\n"+ "" + res+"\n"+normal+"\nДата: "+date;
    }
}
