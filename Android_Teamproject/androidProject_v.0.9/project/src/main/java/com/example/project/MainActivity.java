package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.project.Board.findBoard.FindBoardList;
import com.example.project.Board.missingBoard.MissyouBoardList;
import com.example.project.Board.shelter.ShelterBoardList;
import com.example.project.Board.storyBoard.StoryBoardList;
import com.example.project.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // 발견자 게시판으로 가는 버튼
        binding.goTomissing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindBoardList.class);
                startActivity(intent);
            }
        });

        // 실종 주인 게시판으로 가는 버튼
        binding.goTomissyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MissyouBoardList.class);
                startActivity(intent);
            }
        });

        //스토리 게시판으로 가는 버튼
        binding.Story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StoryBoardList.class);
                startActivity(intent);
            }
        });

        //보호소 게시판으로 가는 버튼
        binding.protect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShelterBoardList.class);
                startActivity(intent);
            }
        });
    }
}