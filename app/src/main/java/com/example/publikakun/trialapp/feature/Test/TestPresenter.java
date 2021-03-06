package com.example.publikakun.trialapp.feature.Test;

import com.example.publikakun.trialapp.base.api.ApiClientOther;
import com.example.publikakun.trialapp.base.api.ApiClientTest;
import com.example.publikakun.trialapp.base.api.ApiInterface;
import com.example.publikakun.trialapp.model.Post;
import com.example.publikakun.trialapp.model.loginmodel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class TestPresenter {
    TestView view;
    CompositeDisposable compositeDisposable;

    public TestPresenter(TestView view, CompositeDisposable compositeDisposable) {
        this.view = view;
        this.compositeDisposable = compositeDisposable;
    }

    void loadData() {
        compositeDisposable.add(ApiClientTest.getClient().create(ApiInterface.class).getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new  Consumer<List<Post>>() {
                    @Override
                    public void accept(List<Post> posts) {
                        try {
                            view.onGetLoadData(posts);
                        } catch (Exception e) {
                            view.onError(e.getLocalizedMessage());
                        }
                    }
                })
        );
    }
    public void testData()
    {
        ApiClientTest.getClient().create(ApiInterface.class).getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<Post>>() {
                    @Override
                    public void onNext(List<Post> posts) {
                        try {
                            view.onGetLoadData(posts);
                        } catch (Exception e) {
                            view.onError(e.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        view.onProgresBarSuccess();
                    }
                });
    }


    void addData(String a, String b, String no) {
        compositeDisposable.add(ApiClientOther.getClient().create(ApiInterface.class).addAlamat("user3", a, a, b, b, b, no, a, no)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<loginmodel>() {
                    @Override
                    public void accept(loginmodel loginmodel) throws Exception {
                        try {
                            if (loginmodel.getStatus().equalsIgnoreCase("Success")) {
                                view.onSuccessAddData();
                            }
                        } catch (Exception e) {
                            view.onError(e.getLocalizedMessage());
                        }
                    }
                }));
    }
    public void addAlamat(String a,String b, String no)
    {
        ApiClientOther.getClient().create(ApiInterface.class).addAlamat("user2",a,a,b,b,b,no,a,no)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<loginmodel>() {
                    @Override
                    public void onNext(loginmodel loginmodel) {
                        try {
                            if (loginmodel.getStatus().equalsIgnoreCase("Success")) {
                                view.onSuccessAddData();
                            }
                        } catch (Exception e) {
                            view.onError(e.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        view.onProgresBarSuccess();
                    }
                });
    }
}
