package com.example.publikakun.trialapp.base.api;

import com.example.publikakun.trialapp.model.Alamat;
import com.example.publikakun.trialapp.model.Post;
import com.example.publikakun.trialapp.model.ResponsePengguna;
import com.example.publikakun.trialapp.model.loginmodel;
import com.jakewharton.rxbinding2.internal.GenericTypeNullable;

import java.util.HashMap;
import java.util.List;
import java.util.Observer;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("posts")
    Observable<List<Post>> getPosts();

    @FormUrlEncoded
    @POST("Alamat")
    Observable<loginmodel> addAlamat(@Field("idpengguna") String idpengguna,
                                     @Field("namapenerima") String namapenerima,
                                     @Field("alamat") String alamat,
                                     @Field("kecamatan") String kecamatan,
                                     @Field("kabupaten") String kabupaten,
                                     @Field("provinsi") String provinsi,
                                     @Field("kodepos") String kodepos,
                                     @Field("keterangan") String keterangan,
                                     @Field("nohp") String nohp);
    @FormUrlEncoded
    @POST("Alamat")
    Observable<loginmodel> addAlamatHash(@FieldMap HashMap<String,String> hashMap);

    @FormUrlEncoded
    @POST("Alamat")
    Observable<loginmodel> addAlamatWithModel(@Body Alamat alamat);

    @GET("Pengguna")
    Observable<ResponsePengguna> getPengguna();
}
