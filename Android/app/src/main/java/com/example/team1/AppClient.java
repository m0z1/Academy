package com.example.team1;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// spring 연결
public class AppClient {

    private AppService appService;

    private  static AppClient instance;


    public AppClient(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.100.102.58:8465")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        appService = retrofit.create(AppService.class);
    }

    public static  AppClient getInstance(){
        if(instance == null){
            instance = new AppClient();
        }
        return instance;
    }

    public AppService getAppService() {return appService;}

}
