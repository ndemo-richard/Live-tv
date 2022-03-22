package com.KLTC.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocalData {

    @SerializedName("tv") private List<Tv> tv;
    @SerializedName("radio") private  List<Radio> radio;

    public List<Radio> getRadio(){
        return radio;
    }

    public void setRadio(List<Radio> radio){
        this.radio=radio;
    }

    public List<Tv> getTv(){
        return tv;
    }

    public void setTv(List<Tv> tv){
        this.tv=tv;
    }
}
