package com.example.publikakun.trialapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePengguna {
    @SerializedName("pengguna")
    private Data pengguna;

    public Data getPengguna() {
        return pengguna;
    }

    public void setPengguna(Data pengguna) {
        this.pengguna = pengguna;
    }

    public static class Data{
     @SerializedName("data")
     private List<Pengguna> data;

     public List<Pengguna> getData() {
         return data;
     }

     public void setData(List<Pengguna> data) {
         this.data = data;
     }
 }
}
