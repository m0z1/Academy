package com.findpet.project01.account;

import java.net.CookieManager;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static Client instance;
    private MemberService memberService;

    //
    public Client() {
        //일반 로그인 시 발생된 쿠키 저장소
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new JavaNetCookieJar(new CookieManager())).build();
        //레트로핏
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("http://10.100.102.58:8899/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        memberService = retrofit.create(MemberService.class);


    }

    public static Client getInstance() {
        if(instance == null) {
            instance = new Client();
        }
        return instance;
    }

    public MemberService getMemberService() {
        return memberService;
    }
}
