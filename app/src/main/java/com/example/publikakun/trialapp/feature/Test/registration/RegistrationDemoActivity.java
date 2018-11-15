package com.example.publikakun.trialapp.feature.Test.registration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.publikakun.trialapp.R;
import com.example.publikakun.trialapp.base.api.ApiInterface;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationDemoActivity extends AppCompatActivity {

    private EditText etEmail;
    private TextView textEmailAlert;
    private  EditText etPassword;
    private TextView textPasswordAlert;
    private EditText etPasswordConfirmation;
    private TextView textPasswordConfirmationAlert;
    private Button btnSubmit;

    private Retrofit retrofit;
    private ApiInterface service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_demo);

        // Inisialisasi form
        etEmail = (EditText) findViewById(R.id.et_email);
        textEmailAlert = (TextView) findViewById(R.id.text_email_alert);
        etPassword = (EditText) findViewById(R.id.et_password);
        textPasswordAlert = (TextView) findViewById(R.id.text_password_alert);
        etPasswordConfirmation = (EditText) findViewById(R.id.et_password_confirmation);
        textPasswordConfirmationAlert = (TextView) findViewById(R.id.text_password_confirmation_alert);
        btnSubmit = (Button) findViewById(R.id.btn_submit);

        // Inisialisasi state (keadaan) default form
        textEmailAlert.setVisibility(View.GONE);
        textPasswordAlert.setVisibility(View.GONE);
        textPasswordConfirmationAlert.setVisibility(View.GONE);
        btnSubmit.setEnabled(false);

        // Inisialisasi retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Inisialisasi service sample
        service = retrofit.create(ApiInterface.class);
    }




}
