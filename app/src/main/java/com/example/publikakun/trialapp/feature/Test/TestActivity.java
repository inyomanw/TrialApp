package com.example.publikakun.trialapp.feature.Test;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.publikakun.trialapp.R;
import com.example.publikakun.trialapp.model.Post;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class TestActivity extends AppCompatActivity implements TestView ,
        AppBarLayout.OnOffsetChangedListener {

    private RecyclerView recyclerViewPost;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ProgressBar progressBar;
    private Button btnAdd;
    private String a = "a", b = "b", no = "432123";
    private TestPresenter presenter;
    private Toolbar toolbar;

    private View mFab;
    private int mMaxScrollSize;
    private boolean mIsImageHidden;
    private static final int PERCENTAGE_TO_SHOW_IMAGE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        progressBar = findViewById(R.id.progressBar);
        recyclerViewPost = findViewById(R.id.rv_posts);
        recyclerViewPost.setHasFixedSize(true);
        recyclerViewPost.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPost.setHasFixedSize(true);
        btnAdd = findViewById(R.id.btn_add);
        mFab = findViewById(R.id.main_appbar);

        presenter = new TestPresenter(this, compositeDisposable);
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                presenter.addAlamat(a, b, no);
            }
        });

//        AppBarLayout appBarLayout = findViewById(R.id.main_appbar);
//        appBarLayout.addOnOffsetChangedListener(this);
        presenter.testData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
    public void onError(String e) {
        Toast.makeText(TestActivity.this,e,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProgresBarSuccess() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
//        if (id==R.id.action_favorite)
//        {
//            Toast.makeText(TestActivity.this,"Action Love Clicked",Toast.LENGTH_SHORT).show();
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int currentScrollPercentage = (Math.abs(verticalOffset)) * 100
                / mMaxScrollSize;

        if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
            if (!mIsImageHidden) {
                mIsImageHidden = true;

                ViewCompat.animate(mFab).scaleY(0).scaleX(0).start();
            }
        }

        if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
            if (mIsImageHidden) {
                mIsImageHidden = false;
                ViewCompat.animate(mFab).scaleY(1).scaleX(1).start();
            }
        }
    }
}
