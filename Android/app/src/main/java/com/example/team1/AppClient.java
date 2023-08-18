package com.example.team1;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// spring 연결
public class AppClient {

    private AppService appService;

    private  static AppClient instance;


    public AppClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.100.102.58:8899")
                .addConverterFactory(GsonConverterFactory.create())
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
