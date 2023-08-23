package com.example.team1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.team1.databinding.ActivityStoryInsertBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Story_insert extends AppCompatActivity {
        private ActivityStoryInsertBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoryInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnSaveStory.setOnClickListener(view -> {
            StoryBoard storyBoard = new StoryBoard();

            storyBoard.setTitle(binding.Storytitle.getText().toString());
            storyBoard.setContent(binding.Storycontent.getText().toString());


            AppService appService = AppClient.getInstance().getAppService();
            Call<StoryBoard> call = appService.story_insert(storyBoard);
            call.enqueue(new Callback<StoryBoard>() {
                @Override
                public void onResponse(Call<StoryBoard> call, Response<StoryBoard> response) {
                    Log.d("StoryBoard 등록", response.toString());
                    finish();
                }

                @Override
                public void onFailure(Call<StoryBoard> call, Throwable t) {
                    Log.d("StoryBoard 등록", "fail");
                }
            });
        });
    }
}