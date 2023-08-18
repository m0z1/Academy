package com.example.team1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


// spring 연결
public interface AppService {

    @GET("/findBoard/list")
    Call<List<FindBoard>> find_list();
@GET("/findBoard/findDog")
    Call<List<FindBoard>> findDog();
    @GET("/findBoard/findCat")
    Call<List<FindBoard>> findCat();

    @GET("/findBoard/findEtc")
    Call<List<FindBoard>> findEtc();

    @GET("/missingBoard/list")
    Call<List<MissingBoard>> missing_list();

    @GET("/missingBoard/findDog")
    Call<List<MissingBoard>> MissingDog();

    @GET("/missingBoard/findCat")
    Call<List<MissingBoard>> MissingCat();

    @GET("/missingBoard/findEtc")
    Call<List<MissingBoard>> MissingEtc();

    @GET("/storyBoard/list")
    Call<List<StoryBoard>> story_list();

    @GET("/storyBoard/findTitle")
    Call<List<StoryBoard>> StoryTitle();

    @GET("/storyBoard/findContent")
    Call<List<StoryBoard>> StoryContent();



}
