package com.example.team1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btngoTomissing = findViewById(R.id.goTomissing);
        Button btngoTomissyou = findViewById(R.id.goTomissyou);
        Button btngoStory = findViewById(R.id.Story);




        btngoTomissing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Find.class);
                startActivity(intent);
            }
        });
        // 발견자 게시판으로 가는 버튼

        btngoTomissyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Missyou.class);
                startActivity(intent);
            }
        });//분실자 게시판으로 가는 버튼


        btngoStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Story.class);
                startActivity(intent);
            }
        });

    }
}