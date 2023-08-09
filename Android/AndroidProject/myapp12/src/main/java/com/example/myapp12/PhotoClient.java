package com.example.myapp12;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoClient {
    private static Retrofit retrofit;

    static Retrofit getClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
