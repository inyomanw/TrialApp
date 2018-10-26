package com.example.publikakun.trialapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pengguna {
    @SerializedName("idpengguna")
    @Expose
    private String idpengguna;
    @SerializedName("idperan")
    @Expose
    private String idperan;
    @SerializedName("namapengguna")
    @Expose
    private String namapengguna;
    @SerializedName("nohp")
    @Expose
    private String nohp;
    @SerializedName("email")
    @Expose
    private String email;

    public String getIdpengguna() {
        return idpengguna;
    }

    public void setIdpengguna(String idpengguna) {
        this.idpengguna = idpengguna;
    }

    public String getIdperan() {
        return idperan;
    }

    public void setIdperan(String idperan) {
        this.idperan = idperan;
    }

    public String getNamapengguna() {
        return namapengguna;
    }

    public void setNamapengguna(String namapengguna) {
        this.namapengguna = namapengguna;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
