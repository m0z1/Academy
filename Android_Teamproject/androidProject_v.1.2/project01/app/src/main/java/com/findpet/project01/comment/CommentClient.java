package com.findpet.project01.comment;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentClient {
    private static CommentClient instance;

    private CommentService commentService;

    public CommentClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.100.102.44:8899/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        commentService = retrofit.create(CommentService.class);
    }

    public static CommentClient getInstance(){
        if(instance==null){
            instance = new CommentClient();
        }
        return instance;
    }

    public CommentService getCommentService(){
        return commentService;
    }
}
