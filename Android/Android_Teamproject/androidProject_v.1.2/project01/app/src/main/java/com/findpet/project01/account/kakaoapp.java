package com.findpet.project01.account;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class kakaoapp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //로그인 api
        KakaoSdk.init(this, "a0b0ba75b281cc409f20442a8b7e5744");
    }
}
