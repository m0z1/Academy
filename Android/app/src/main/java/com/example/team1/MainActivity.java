package com.example.team1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.Request;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private FindAdapter findAdapter;
    private ArrayList<FindBoard> findList;

    private MissyouAdapter missyouAdapter;
    private ArrayList<MissyouBoard> missList;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private ArrayList<MainBoard> mainList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewAll);
        recyclerView2 = findViewById(R.id.recyclerViewAll2);

        Button btngoTomissing = findViewById(R.id.goTomissing);
        Button btngoTomissyou = findViewById(R.id.goTomissyou);
        Button btngoStory = findViewById(R.id.Story);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        Log.d("linearLayoutManager",linearLayoutManager+"" );
        findList = new ArrayList<>();
        findAdapter = new FindAdapter(findList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(findAdapter);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        missList =  new ArrayList<>();
        missyouAdapter = new MissyouAdapter(missList);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        recyclerView2.setAdapter(missyouAdapter);




        AppService appService = AppClient.getInstance().getAppService();
        Call<List<FindBoard>> call = appService.find_list();
        Call<List<MissyouBoard>> call2 = appService.missing_list();

      call.enqueue(new Callback<List<FindBoard>>() {
          @Override
          public void onResponse(Call<List<FindBoard>> call, Response<List<FindBoard>> response) {
              int itemSize = response.body().size();
              if(itemSize>3){
                  itemSize = 3;
              }

              for(int i=0; i < itemSize; i++){
                  findAdapter.addItem(response.body().get(i));
              }
              findAdapter.notifyDataSetChanged();
          }

          @Override
          public void onFailure(Call<List<FindBoard>> call, Throwable t) {

          }
      });

      call2.enqueue(new Callback<List<MissyouBoard>>() {
          @Override
          public void onResponse(Call<List<MissyouBoard>> call, Response<List<MissyouBoard>> response) {
              int itemSize = response.body().size();
              if(itemSize>3){
                  itemSize = 3;
              }
              for(int i = 0 ; i < itemSize ; i++){
                 missyouAdapter.addItem(response.body().get(i));
             }
              missyouAdapter.notifyDataSetChanged();
          }

          @Override
          public void onFailure(Call<List<MissyouBoard>> call, Throwable t) {

          }
      });

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