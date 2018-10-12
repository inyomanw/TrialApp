package com.example.publikakun.trialapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Alamat implements Parcelable {
    @SerializedName("idpengguna")
    private String idpengguna;
    @SerializedName("namapenerima")
    private String namapenerima;
    @SerializedName("kecamatan")
    private String kecamatan;
    @SerializedName("kabupaten")
    private String kabupaten;
    @SerializedName("provinsi")
    private String provinsi;
    @SerializedName("kodepos")
    private String kodepos;
    @SerializedName("keterangan")
    private String keterangan;
    @SerializedName("nohp")
    private String nohp;
    @SerializedName("alamat")
    private String alamat;
    public Alamat(){}

    protected Alamat(Parcel in) {
        idpengguna = in.readString();
        namapenerima = in.readString();
        kecamatan = in.readString();
        kabupaten = in.readString();
        provinsi = in.readString();
        kodepos = in.readString();
        keterangan = in.readString();
        nohp = in.readString();
        alamat = in.readString();
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getIdpengguna() {
        return idpengguna;
    }

    public void setIdpengguna(String idpengguna) {
        this.idpengguna = idpengguna;
    }

    public String getNamapenerima() {
        return namapenerima;
    }

    public void setNamapenerima(String namapenerima) {
        this.namapenerima = namapenerima;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKodepos() {
        return kodepos;
    }

    public void setKodepos(String kodepos) {
        this.kodepos = kodepos;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public static Creator<Alamat> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<Alamat> CREATOR = new Creator<Alamat>() {
        @Override
        public Alamat createFromParcel(Parcel in) {
            return new Alamat(in);
        }

        @Override
        public Alamat[] newArray(int size) {
            return new Alamat[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idpengguna);
        parcel.writeString(namapenerima);
        parcel.writeString(kecamatan);
        parcel.writeString(kabupaten);
        parcel.writeString(provinsi);
        parcel.writeString(kodepos);
        parcel.writeString(keterangan);
        parcel.writeString(nohp);
    }
}
