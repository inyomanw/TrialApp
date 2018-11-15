package com.example.publikakun.trialapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.publikakun.trialapp.base.api.ApiClientOther;
import com.example.publikakun.trialapp.base.api.ApiInterface;
import com.example.publikakun.trialapp.model.Pengguna;
import com.example.publikakun.trialapp.model.ResponsePengguna;
import com.example.publikakun.trialapp.utils.Formats;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;

public class RegistrationActivity extends AppCompatActivity {
    private final static String TAG = RegistrationActivity.class.getSimpleName()+" logv";
    EditText etEmail, etPassword, etConfirmationPassword;
    TextInputLayout fieldEmail, fieldPassword, fieldConfirmationPassword;
    Button btnRegister;
    ArrayList<String> listEmail = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etConfirmationPassword = findViewById(R.id.et_confirmation_password);
        fieldEmail = findViewById(R.id.field_email);
        fieldPassword = findViewById(R.id.field_password);
        fieldConfirmationPassword = findViewById(R.id.field_confirmation_password);
        btnRegister = findViewById(R.id.btn_register);
        getListEmail();
        setFieldValidator();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegistrationActivity.this, "ra na fungsine iki", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getListEmail() {
        ApiClientOther.getClient().create(ApiInterface.class)
                .getPengguna().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponsePengguna>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponsePengguna responsePengguna) {
                        for (Pengguna pengguna : responsePengguna.getPengguna().getData()) {
                            listEmail.add(pengguna.getEmail());
                        }
//                        Toast.makeText(RegistrationActivity.this, "success get all email", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onNext: "+listEmail.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void setFieldValidator() {
        RxTextView.textChanges(etEmail)
                .debounce(500,TimeUnit.MILLISECONDS)
                .map(new Function<CharSequence, Boolean>() {
                    @Override
                    public Boolean apply(CharSequence charSequence) throws Exception {
                        return checkIfEmailExistFromAPI(charSequence.toString());
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        fieldEmail.setErrorEnabled(aBoolean);
                        if(aBoolean){
                            fieldEmail.setError("email already exist");
                        }else{
                            fieldEmail.setError(null);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public boolean checkIfEmailExistFromAPI(final String input) {
        return listEmail.contains(input);
    }


}
