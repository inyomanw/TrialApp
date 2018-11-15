package com.example.publikakun.trialapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.publikakun.trialapp.base.api.ApiClientOther;
import com.example.publikakun.trialapp.base.api.ApiInterface;
import com.example.publikakun.trialapp.feature.Test.TestActivity;
import com.example.publikakun.trialapp.model.ResponsePengguna;
import com.example.publikakun.trialapp.utils.Formats;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private EditText etTest, edtPassword, edtConfirmPass, edtEmail;
    private TextView alertPassword, alertConfirmPass, textEmailAlert;
    private ApiInterface apiInterface;
    private List<String> listEmail = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();
        RxTextView.textChanges(etTest)
                .debounce(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(CharSequence charSequence) throws Exception {
                        return charSequence.length() > 5;
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
        getListEmail();

        rxPassword();
        rxConfirmPassword();
        rxEmail();
    }

    private void initLayout() {
        etTest = findViewById(R.id.et_test);
        edtPassword = findViewById(R.id.edt_password);
        edtConfirmPass = findViewById(R.id.edt_confirm_password);
        alertPassword = findViewById(R.id.alert_password);
        alertConfirmPass = findViewById(R.id.alert_confirm_pass);
        textEmailAlert = findViewById(R.id.alert_email);
        apiInterface = ApiClientOther.getClient().create(ApiInterface.class);
        edtEmail = findViewById(R.id.edt_email);
    }

    private void getListEmail() {
        apiInterface.getPengguna().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponsePengguna>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponsePengguna responsePengguna) {
                        for (int i = 0; i < responsePengguna.getPengguna().getData().size(); i++) {
                            listEmail.add(responsePengguna.getPengguna().getData().get(i).getEmail().toString());
                        }
                        Log.d(TAG, "LOGV onNext: " + listEmail.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void rxPassword() {
        Observable<Boolean> passwordStream = RxTextView.textChanges(edtPassword)
                .map(new Function<CharSequence, Boolean>() {
                    @Override
                    public Boolean apply(CharSequence charSequence) throws Exception {
                        return !TextUtils.isEmpty(charSequence)
//                                && charSequence.toString().trim().length() < 6
                                && !Formats.password(charSequence.toString());
                    }
                });
        Consumer<Boolean> passwordConsumer = new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {

            }
        };
        Observer<Boolean> passwordObserver = new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                Log.d("passwordObserver", String.valueOf(aBoolean.booleanValue()));
                showPasswordMinimalAlert(aBoolean.booleanValue());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("rx", e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("rx", "Password stream completed");
            }
        };
        passwordStream.subscribe(passwordConsumer);
    }

    private void rxConfirmPassword() {
        Observable<Boolean> confirmPasswordStream = Observable.merge(
                (ObservableSource<? extends Boolean>) RxTextView.textChanges(edtPassword)
                        .map(new Function<CharSequence, Boolean>() {
                            @Override
                            public Boolean apply(CharSequence charSequence) throws Exception {
                                return !charSequence.toString().trim().equalsIgnoreCase(edtConfirmPass.getText().toString());
                            }
                        }),
                RxTextView.textChanges(edtConfirmPass)
                        .map(new Function<CharSequence, Boolean>() {
                            @Override
                            public Boolean apply(CharSequence charSequence) throws Exception {
                                return !charSequence.toString().trim().equalsIgnoreCase(edtPassword.getText().toString());
                            }
                        })
        );
        Observer<Boolean> confirmPasswordObserver = new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                Log.d("passwordConfirmation", String.valueOf(aBoolean.booleanValue()));
                showPasswordConfirmationAlert(aBoolean.booleanValue());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("rx", e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        confirmPasswordStream.subscribe(confirmPasswordObserver);
    }

    private void rxEmail() {
        RxTextView.textChanges(edtEmail)
                .debounce(100, TimeUnit.MILLISECONDS)
                .map(new Function<CharSequence, Boolean>() {
                    @Override
                    public Boolean apply(CharSequence charSequence) throws Exception {
                        return checkIfEmailExistFromAPI(charSequence.toString()) && charSequence.length() > 6;
                    }
                }).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                Toast.makeText(MainActivity.this,"Email Exist",Toast.LENGTH_SHORT).show();
//                showEmailExistAlert(aBoolean);

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

    public void showPasswordMinimalAlert(boolean value) {
        if (value) {
            alertPassword.setText("Password harus memiliki 3 huruf dan 3 angka");
            alertPassword.setVisibility(View.VISIBLE);
//            btnRegister.setEnabled(false);
        } else {
            alertPassword.setVisibility(View.GONE);
//            btnRegister.setEnabled(true);
        }
    }

    public void showPasswordConfirmationAlert(boolean value) {
        if (value) {
            alertConfirmPass.setText("Password yang di inputkan tidak sesuai");
            alertConfirmPass.setVisibility(View.VISIBLE);
        } else {
            alertConfirmPass.setVisibility(View.GONE);
        }
    }

    public void showEmailExistAlert(boolean value) {
        if (value) {
            textEmailAlert.setText(getString(R.string.email_exist_alert));
            textEmailAlert.setVisibility(View.VISIBLE);
        } else {
            textEmailAlert.setVisibility(View.GONE);
        }
    }

    public void test(View view) {
        Intent intent = new Intent(MainActivity.this, TestActivity.class);
        startActivity(intent);
    }

}
