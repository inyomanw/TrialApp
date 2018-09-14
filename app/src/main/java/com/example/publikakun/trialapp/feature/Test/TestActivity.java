package com.example.publikakun.trialapp.feature.Test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.publikakun.trialapp.R;
import com.example.publikakun.trialapp.base.api.ApiClientOther;
import com.example.publikakun.trialapp.base.api.ApiClientTest;
import com.example.publikakun.trialapp.base.api.ApiInterface;
import com.example.publikakun.trialapp.model.Post;
import com.example.publikakun.trialapp.model.loginmodel;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TestActivity extends AppCompatActivity {

    private ApiInterface mapiInterface,apiInterface2;
    private RecyclerView recyclerViewPost;
    private Subscription subscription;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ProgressBar progressBar;
    private String a = "a", b="b",no="23423";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        progressBar = findViewById(R.id.progressBar);
        mapiInterface = ApiClientTest.getClient().create(ApiInterface.class);
        apiInterface2 = ApiClientOther.getClient().create(ApiInterface.class);
        recyclerViewPost = findViewById(R.id.rv_posts);
        recyclerViewPost.setHasFixedSize(true);
        recyclerViewPost.setLayoutManager(new LinearLayoutManager(this));
        loadData();
    }
    private void fetchData() {
        compositeDisposable.add(mapiInterface.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Post>>() {
                    @Override
                    public void accept(List<Post> posts) throws Exception {
                        PostAdapter postAdapter = new PostAdapter(TestActivity.this,posts);
                        recyclerViewPost.setAdapter(postAdapter);
                    }
                }));
    }

    private void loadData(){
        mapiInterface.getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        PostAdapter postAdapter = new PostAdapter(TestActivity.this,posts);
                        recyclerViewPost.setAdapter(postAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(TestActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
    private void test()
    {
        compositeDisposable.add(mapiInterface.addAlamat("user2",a,a,b,b,b,no,a,no)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<loginmodel>() {
                    @Override
                    public void accept(loginmodel loginmodel) throws Exception {
                        if (loginmodel.getStatus().equalsIgnoreCase("Success"))
                        {
                            Toast.makeText(TestActivity.this,"Sukses Bor",Toast.LENGTH_SHORT).show();
                        }
                    }
                }));
    }
    private void addData()
    {
        Observable<loginmodel> loginmodelObservable = apiInterface2.addAlamat("user2",a,a,b,b,b,no,a,no);
        loginmodelObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<loginmodel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(loginmodel loginmodel) {
                        if (loginmodel.getStatus().equalsIgnoreCase("Success"))
                        {
                            Toast.makeText(TestActivity.this,"Sukses Bor",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(TestActivity.this,"FAILED: "+e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
