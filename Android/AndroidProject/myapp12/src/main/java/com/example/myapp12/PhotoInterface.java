package com.example.myapp12;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotoInterface {
    @GET("photos/")
    Call<List<Photo>> doGetPhotos();

    @GET("posts/")
    Call<List<Post>> doGetPosts();


}
