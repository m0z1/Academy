package com.example.team1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Story extends AppCompatActivity {

    private StoryAdapter storyAdapter;
    private RecyclerView recyclerView;
    private ArrayList<StoryBoard> storyList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        Button writeStory = findViewById(R.id.writeStory);
        ImageView StoryImg = findViewById(R.id.StoryImg);

        recyclerView = findViewById(R.id.recyclerViewStory);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Story.this,LinearLayoutManager.VERTICAL,false);
        storyList = new ArrayList<>();
        storyAdapter = new StoryAdapter(storyList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(storyAdapter);

        AppService appService = AppClient.getInstance().getAppService();
        Call<List<StoryBoard>> call = appService.story_list();
        call.enqueue(new Callback<List<StoryBoard>>() {
            @Override
            public void onResponse(Call<List<StoryBoard>> call, Response<List<StoryBoard>> response) {
                for(StoryBoard s : response.body()){
                    storyAdapter.addItem(s);
                }
                storyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<StoryBoard>> call, Throwable t) {

            }
        });

        StoryImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        writeStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Story_insert.class);
                startActivity(intent);
            }
        });

    }
}