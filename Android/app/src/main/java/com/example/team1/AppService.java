package com.example.team1;

import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


// spring 연결
public interface AppService {

    @GET("/findBoard/list")
    Call<List<FindBoard>> find_list();

    @GET("/findBoard/find")
    Call<List<FindBoard>> find(@Path("id") int id);

    @POST("/findBoard/insert")
    Call<FindBoard> insert(@Body FindBoard findBoard);

    @GET("/findBoard/findDog")
    Call<List<FindBoard>> findDog();
    @GET("/findBoard/findCat")
    Call<List<FindBoard>> findCat();

    @GET("/findBoard/findEtc")
    Call<List<FindBoard>> findEtc();

    @GET("/missingBoard/list")
    Call<List<MissyouBoard>> missing_list();

    @GET("/missingBoard/findDog")
    Call<List<MissyouBoard>> MissingDog();

    @GET("/missingBoard/findCat")
    Call<List<MissyouBoard>> MissingCat();

    @GET("/missingBoard/findEtc")
    Call<List<MissyouBoard>> MissingEtc();

    @GET("/storyBoard/list")
    Call<List<StoryBoard>> story_list();

    @GET("/storyBoard/findTitle")
    Call<List<StoryBoard>> StoryTitle();

    @GET("/storyBoard/findContent")
    Call<List<StoryBoard>> StoryContent();



}
