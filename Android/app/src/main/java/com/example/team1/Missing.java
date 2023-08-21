package com.example.team1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TabHost;

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Missing extends AppCompatActivity {
private MissingAdapter missingAdapter;
private RecyclerView recyclerView;
private ArrayList<FindBoard> findList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing);

        ImageView missingImg = findViewById(R.id.missing);
        Button writeMissing = findViewById(R.id.writeMissing);
        Button insertest = findViewById(R.id.searchMissing);

        recyclerView = findViewById(R.id.recylerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Missing.this,LinearLayoutManager.VERTICAL,false);
        findList = new ArrayList<>();
        missingAdapter = new MissingAdapter(findList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(missingAdapter);

        AppService appService = AppClient.getInstance().getAppService();
        Call<List<FindBoard>> call = appService.find_list();

        call.enqueue(new Callback<List<FindBoard>>() {
            @Override
            public void onResponse(Call<List<FindBoard>> call, Response<List<FindBoard>> response) {
                if (response.isSuccessful()) {
                    List<FindBoard> findBoard = response.body();

                if(response != null) {
                    for (FindBoard f : findBoard) {
                        missingAdapter.addItem(f);
                    }
                    missingAdapter.notifyDataSetChanged();
                }

                }

            }

            @Override
            public void onFailure(Call<List<FindBoard>> call, Throwable t) {

            }
        });


        missingImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        writeMissing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MissingInsert.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();

        String breed = intent.getStringExtra("breed");
        String content = intent.getStringExtra("content");
        Date date = intent.getParcelableExtra("date");
        String petname = intent.getStringExtra("petname");
        String petage = intent.getStringExtra("petage");
        String petcharacter = intent.getStringExtra("petcharacter");
        String petcategory = intent.getStringExtra("petcategory");
        String findaddr = intent.getStringExtra("findAddr");
        String petgender = intent.getStringExtra("petgender");

     insertest.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             FindBoard findBoard = new FindBoard(breed,content,date,petname,petage,petgender,petcharacter,petcategory,findaddr);
             AppService appService = AppClient.getInstance().getAppService();
             Call<FindBoard> call = appService.insert(findBoard);
             call.enqueue(new Callback<FindBoard>() {
                 @Override
                 public void onResponse(Call<FindBoard> call, Response<FindBoard> response) {
                     missingAdapter.addItem(findBoard);
                     missingAdapter.notifyDataSetChanged();

                 }

                 @Override
                 public void onFailure(Call<FindBoard> call, Throwable t) {

                 }
             });

         }
     });


    }
}