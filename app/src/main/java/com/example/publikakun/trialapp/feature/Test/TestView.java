package com.example.publikakun.trialapp.feature.Test;

import com.example.publikakun.trialapp.model.Post;

import java.util.List;

public interface TestView {
    void onGetLoadData(List<Post> posts);
    void onSuccessAddData();
    void onError(Exception e);

}
