package com.findpet.project01.Board.storyBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.findpet.project01.Board.BoardClient;
import com.findpet.project01.Board.BoardInterface;
import com.findpet.project01.Main;
import com.findpet.project01.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoryBoardList extends AppCompatActivity {

    List<StoryBoard> storyBoardList;
    StoryAdapter storyAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_board_list);

        Button writeStroy = findViewById(R.id.writeStory);
        ImageView StoryImg = findViewById(R.id.StoryImg);
        RecyclerView recyclerView = findViewById(R.id.recylerView);


        //이미지 클릭 -> 홈으로 이동
        StoryImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
            }
        });
        
        //스토리 작성
        writeStroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(), StoryBoardActivity.class);
                startActivity(intent);
            }
        });

        //전체보기
        storyBoardList = new ArrayList<>();
        storyAdapter = new StoryAdapter(storyBoardList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setAdapter(storyAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
        Call<List<StoryBoard>> call = boardInterface.story_list();
        call.enqueue(new Callback<List<StoryBoard>>() {
            @Override
            public void onResponse(Call<List<StoryBoard>> call, Response<List<StoryBoard>> response) {
                List<StoryBoard> resource = response.body();
                for(StoryBoard storyBoard : resource){
                    storyBoardList.add(storyBoard);
                }
                storyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<StoryBoard>> call, Throwable t) {

            }
        });

    }
}