package com.example.publikakun.trialapp.feature.Test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.publikakun.trialapp.R;
import com.example.publikakun.trialapp.model.Post;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class TestActivity extends AppCompatActivity implements TestView {

    private RecyclerView recyclerViewPost;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ProgressBar progressBar;
    private Button btnAdd;
    private String a = "a", b = "b", no = "432123";
    private TestPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        progressBar = findViewById(R.id.progressBar);
        recyclerViewPost = findViewById(R.id.rv_posts);
        recyclerViewPost.setHasFixedSize(true);
        recyclerViewPost.setLayoutManager(new LinearLayoutManager(this));
        btnAdd = findViewById(R.id.btn_add);

        presenter = new TestPresenter(this, compositeDisposable);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addData(a, b, no);
            }
        });
        presenter.loadData();
    }

    @Override
    public void onGetLoadData(List<Post> posts) {
        PostAdapter postAdapter = new PostAdapter(TestActivity.this, posts);
        recyclerViewPost.setAdapter(postAdapter);
    }

    @Override
    public void onSuccessAddData() {
        Toast.makeText(TestActivity.this, "Sukses Bor", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Exception e) {
        Toast.makeText(TestActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
