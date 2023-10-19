package com.findpet.project01.Board;



import com.findpet.project01.Board.findBoard.FindBoard;
import com.findpet.project01.Board.missingBoard.MissingBoard;
import com.findpet.project01.Board.storyBoard.StoryBoard;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BoardInterface {

    //<<<<<<<<< 발견 (FindBoard) 게시판 >>>>>>>>//

    //발견 게시판 작성
    @Multipart
    @POST("findBoard/insert")
    Call<String> saveFindBoard(
            @Part List<MultipartBody.Part> imgFileList,
            @PartMap Map<String, RequestBody> findBoard,
            @Part("username") String username);

    //발견 게시판 상세보기
    @GET("/findBoard/view")
    Call<FindBoard> view(@Query("findId") Long findId);
    
    //발견 게시판 수정
    @Multipart
    @POST("findBoard/update")
    Call<FindBoard> updateFindBoard(@Part List<MultipartBody.Part> imgFileList, @PartMap Map<String, RequestBody> findBoard);

    //발견 게시판 삭제
    @DELETE("/findBoard/delete/{findId}")
    Call<Void> deleteById1(@Path("findId") Long findId);

    //발견 게시판 전체보기
    @GET("/findBoard/list")
    Call<List<FindBoard>> find_list();

    //발견 게시판 검색
    @GET("/findBoard/findAll/{word}")
    Call<List<FindBoard>> findAll(@Path("word") String word);

    //강아지 태그
    @GET("/findBoard/findDog/{petcategory}")
    Call<List<FindBoard>> findDog(@Path("petcategory") String petcategory);
    //고양이 태그
    @GET("/findBoard/findCat/{petcategory}")
    Call<List<FindBoard>> findCat(@Path("petcategory") String petcategory);
    //기타 태그
    @GET("/findBoard/findEtc/{petcategory}")
    Call<List<FindBoard>> findEtc(@Path("petcategory") String petcategory );









    //<<<<<<<<< 실종 (MissingBoard) 게시판 >>>>>>>>//
    //실종 게시판 작성

    @Multipart
    @POST("missingBoard/insert")
    Call<String> saveMissingBoard(@Part List<MultipartBody.Part> imgFileList, @PartMap Map<String, RequestBody> missingBoard
    , @Part("username") String username);
//    @POST("/missingBoard/insert")
//    Call<MissingBoard> save(@Body MissingBoard missingBoard);
    

    //실종 게시판 상세보기
    @GET("/missingBoard/view2")
    Call<MissingBoard> view2(@Query("missingId") Long missingId);

    //실종 게시판 수정
    @Multipart
    @POST("missingBoard/update")
    Call<MissingBoard> updateMissingBoard(@Part List<MultipartBody.Part> imgFileList, @PartMap Map<String, RequestBody> missingBoard);


    //실종 게시판 삭제
    @DELETE("/missingBoard/delete/{missingId}")
    Call<Void> deleteById2(@Path("missingId") Long missingId);

    //실종 게시판 전체보기
    @GET("/missingBoard/list")
    Call<List<MissingBoard>> missing_list();

    //검색
    @GET("/missingBoard/findAll/{word}")
    Call<List<MissingBoard>> findAll_miss(@Path("word") String word);

    @GET("/missingBoard/findDog/{petcategory}")
    Call<List<MissingBoard>> MissingDog(@Path("petcategory") String petcategory);

    @GET("/missingBoard/findCat/{petcategory}")
    Call<List<MissingBoard>> MissingCat(@Path("petcategory") String petcategory);

    @GET("/missingBoard/findEtc/{petcategory}")
    Call<List<MissingBoard>> MissingEtc(@Path("petcategory") String petcategory);







    //<<<<<<<<< 스토리 (StoryBoard) 게시판 >>>>>>>>//

    //스토리 게시판 작성
    @Multipart
    @POST("storyBoard/insert")
    Call<String> saveStoryBoard(@Part List<MultipartBody.Part> imgFileList,
                                @PartMap Map<String, RequestBody> storyBoard,
                                @Part("username") String username);

    //스토리 게시판 상세보기
    @GET("/storyBoard/view3")
    Call<StoryBoard> view3(@Query("storyId") Long storyId);

    //스토리 게시판 수정
    @Multipart
    @POST("storyBoard/update")
    Call<StoryBoard> updateStoryBoard(@Part List<MultipartBody.Part> imgFileList, @PartMap Map<String, RequestBody> storyBoard);


    //스토리 게시판 삭제
    @DELETE("/storyBoard/delete/{storyId}")
    Call<Void> deleteById3(@Path("storyId") Long storyId);

    //스토리 게시판 전체보기
    @GET("/storyBoard/list")
    Call<List<StoryBoard>> story_list();

    @GET("/storyBoard/findTitle")
    Call<List<StoryBoard>> StoryTitle();

    @GET("/storyBoard/findContent")
    Call<List<StoryBoard>> StoryContent();

}
