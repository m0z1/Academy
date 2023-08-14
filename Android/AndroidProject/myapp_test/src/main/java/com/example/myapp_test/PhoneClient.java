package com.example.myapp_test;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhoneClient {

    private PhoneService phoneService;

    private static PhoneClient instance;

    public PhoneClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.100.102.58:9981")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        phoneService = retrofit.create(PhoneService.class);
    }

    public static PhoneClient getInstance(){
        if(instance == null) {
            instance = new PhoneClient();
        }
        return instance;
    }

    public PhoneService getPhoneService(){
        return phoneService;
    }

}


