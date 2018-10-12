package com.example.publikakun.trialapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.publikakun.trialapp.feature.Test.TestActivity;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.example.publikakun.trialapp.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

public class MainActivity extends AppCompatActivity {

    private EditText etTest,edtPassword,edtConfirmPass;
    private TextView alertPassword,alertConfirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();
        RxTextView.textChanges(etTest)
                .debounce(2,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(CharSequence charSequence) throws Exception {
                        return charSequence.length()>5;
                    }
                })
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        Toast.makeText(MainActivity.this, charSequence.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        RxTextView.textChanges(edtPassword)
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(CharSequence charSequence) throws Exception {
                        return charSequence.length()==6;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CharSequence charSequence) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        alertPassword.setVisibility(View.VISIBLE);
                        alertPassword.setText("Password harus mengandung 3 huruf dan 3 angka");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    private void initLayout()
    {
        etTest=findViewById(R.id.et_test);
        edtPassword=findViewById(R.id.edt_password);
        edtConfirmPass=findViewById(R.id.edt_confirm_password);
        alertPassword=findViewById(R.id.alert_password);
        alertConfirmPass=findViewById(R.id.alert_confirm_pass);
    }

    public void test(View view)
    {
        Intent intent = new Intent(MainActivity.this, TestActivity.class);
        startActivity(intent);
    }

}
