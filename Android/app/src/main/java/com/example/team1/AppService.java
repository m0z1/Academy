package com.example.team1;

import androidx.viewpager2.widget.ViewPager2;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;


// spring 연결
public interface AppService {
    @GET("/main/all")
    Call<List<MainBoard>> all();


    /* Find - 신고자 */
    @GET("/findBoard/list")
    Call<List<FindBoard>> find_list();

    @GET("/findBoard/find")
    Call<List<FindBoard>> find(@Path("id") int id);

    @POST("/findBoard/insert")
    Call<FindBoard> find_insert(@Body FindBoard findBoard);

    @Multipart
    @POST("/findBoard/insert")
    Call<String> saveFindBoard(@Part List<MultipartBody.Part> imgFileList, @PartMap Map<String, RequestBody> findBoard);

    @Multipart
    @POST("/missingBoard/insert")
    Call<String> saveMissingBoard(@Part List<MultipartBody.Part> imgFileList, @PartMap Map<String, RequestBody> missingBoard);

    @Multipart
    @POST("/storyBoard/insert")
    Call<String> saveStoryBoard(@Part List<MultipartBody.Part> imgFileList, @PartMap Map<String, RequestBody> storyBoard);

    @GET("/findBoard/findDog/{petcategory}")
    Call<List<FindBoard>> findDog(@Path("petcategory") String petcategory);
    @GET("/findBoard/findCat/{petcategory}")
    Call<List<FindBoard>> findCat(@Path("petcategory") String petcategory);

    @GET("/findBoard/findEtc/{petcategory}")
    Call<List<FindBoard>> findEtc(@Path("petcategory") String petcategory );

    @GET("/findBoard/findAll/{word}")
    Call<List<FindBoard>> findAll(@Path("word") String word);

    /* MISSYOU - 분실자 */

    @GET("/missingBoard/findAll/{word}")
    Call<List<MissyouBoard>> findAll_miss(@Path("word") String word);
    @POST("/missingBoard/insert")
    Call<MissyouBoard> missyou_insert(@Body MissyouBoard missyouBoard);
    @GET("/missingBoard/list")
    Call<List<MissyouBoard>> missing_list();

    @GET("/missingBoard/missingDog/{petcategory}")
    Call<List<MissyouBoard>> missingDog(@Path("petcategory") String petcategory);

    @GET("/missingBoard/missingCat/{petcategory}")
    Call<List<MissyouBoard>> missingCat(@Path("petcategory")String petcategory);

    @GET("/missingBoard/missingEtc/{petcategory}")
    Call<List<MissyouBoard>> missingEtc(@Path("petcategory") String petcategory);
    /* Story */

    @POST("/storyBoard/insert")
    Call<StoryBoard> story_insert(@Body StoryBoard storyBoard);

    @GET("/storyBoard/list")
    Call<List<StoryBoard>> story_list();

    @GET("/storyBoard/findTitle")
    Call<List<StoryBoard>> storyTitle();

    @GET("/storyBoard/findContent")
    Call<List<StoryBoard>> storyContent();



}
