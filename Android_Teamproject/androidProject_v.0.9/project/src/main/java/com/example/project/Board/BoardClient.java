package com.example.project.Board;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BoardClient {
    private static BoardClient instance;

    private BoardInterface boardInterface;

    public BoardClient(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.100.102.45:8899/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        boardInterface = retrofit.create(BoardInterface.class);
    }

    public static BoardClient getInstance(){
        if(instance==null){
            instance = new BoardClient();
        }
        return instance;
    }
    public BoardInterface getBoardInterface(){
        return boardInterface;
    }
}
