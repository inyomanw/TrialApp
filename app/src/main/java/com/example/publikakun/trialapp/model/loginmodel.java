package com.example.publikakun.trialapp.model;

import com.google.gson.annotations.SerializedName;

public class loginmodel {
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private String status;
    @SerializedName("idpengguna")
    private String idpengguna;
    @SerializedName("barang_sama")
    private String barang_sama;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdpengguna() {
        return idpengguna;
    }

    public void setIdpengguna(String idpengguna) {
        this.idpengguna = idpengguna;
    }

    public String getBarang_sama() {
        return barang_sama;
    }

    public void setBarang_sama(String barang_sama) {
        this.barang_sama = barang_sama;
    }
}
