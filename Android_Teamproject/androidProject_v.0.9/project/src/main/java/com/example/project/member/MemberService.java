package com.example.project.member;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MemberService {


    @GET("/member/view/{memberId}")
    Call<Member> findById(@Path("memberId") Long memberId);
}
