package com.findpet.project01.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MemberService {


    //회원가입
    @POST("member/join")
    Call<Member> join(@Body Member member);

    //아이디 중복확인
    @GET("member/findusername/{username}")
    Call<Integer> findusername(@Path("username") String username);

    //회원 탈퇴
    @DELETE("member/delete/{username}")
    Call<Member> delete(@Path("username") String username);

    //닉네임 중복확인
    @GET("member/findnick/{nickname}")
    Call<Integer> findnick(@Path("nickname") String nickname);


    //일반 로그인
    @GET("member/login")
    Call<Integer> login(@Query("username") String username, @Query("password") String password);

    //유저 상세정보
    @GET("member/findmember/{username}")
    Call<Member> findmember(@Path("username") String username);

    //회원정보 수정
    @PUT("member/update/{username}")
    Call<Member> update(@Path("username") String username, @Body Member member);
}
