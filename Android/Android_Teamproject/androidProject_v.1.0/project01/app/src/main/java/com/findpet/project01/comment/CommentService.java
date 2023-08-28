package com.findpet.project01.comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CommentService {

    //<<<<<<<<<<<<<<<<<<<<<FindBoard(제보)>>>>>>>>>>>>>>>>>>//
    //전체보기
    @GET("/comment/list/{find_id}")
    Call<List<Comment>> findAllByFindId(@Path("find_id") Long find_id);

    //추가
    @POST("/comment/insert/{find_id}")
    Call<Comment> insert(@Body Comment comment,@Path("find_id") Long find_id);

    //개수
    @GET("/comment/count/{find_id}")
    Call<Integer> count(@Path("find_id") Long find_id);

    //상세보기
    @POST("/comment/view/{commentId}")
    Call<Comment> findById(@Path("commentId") Long commentId);

    //수정
    @POST("/comment/update")
    Call<Comment> update(@Body Comment comment);

    //삭제
    @DELETE("/comment/delete/{commentId}")
    Call<Void> deleteById(@Path("commentId") Long commentId);


    //<<<<<<<<<<<<<<<<<<<<<MissingBoard(실종)>>>>>>>>>>>>>>>>>>//
    //전체보기
    @GET("/comment/list2/{missing_id}")
    Call<List<Comment>> findAllByMissingId(@Path("missing_id") Long missing_id);

    //추가
    @POST("/comment/insert2/{missing_id}")
    Call<Comment> insert2(@Body Comment comment,@Path("missing_id") Long missing_id);

    //개수
    @GET("/comment/count2/{missing_id}")
    Call<Integer> count2(@Path("missing_id") Long missing_id);

    //상세보기
    @POST("/comment/view/{commentId}")
    Call<Comment> findById2(@Path("commentId") Long commentId);

    //수정
    @POST("/comment/update")
    Call<Comment> update2(@Body Comment comment);

    //삭제
    @DELETE("/comment/delete/{commentId}")
    Call<Void> deleteById2(@Path("commentId") Long commentId);





    //<<<<<<<<<<<<<<<<<<<<<StoryBoard(스토리)>>>>>>>>>>>>>>>>>>//
    //전체보기
    @GET("/comment/list3/{story_id}")
    Call<List<Comment>> findAllByStoryId(@Path("story_id") Long story_id);

    //추가
    @POST("/comment/insert3/{story_id}")
    Call<Comment> insert3(@Body Comment comment,@Path("story_id") Long story_id);

    //개수
    @GET("/comment/count3/{story_id}")
    Call<Integer> count3(@Path("story_id") Long story_id);

    //상세보기
    @POST("/comment/view/{commentId}")
    Call<Comment> findById3(@Path("commentId") Long commentId);

    //수정
    @POST("/comment/update")
    Call<Comment> update3(@Body Comment comment);

    //삭제
    @DELETE("/comment/delete/{commentId}")
    Call<Void> deleteById3(@Path("commentId") Long commentId);



}
