package com.example.team1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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

public class Missyou extends AppCompatActivity {

    private MissyouAdapter missyouAdapter;
    private RecyclerView recyclerView;
    private ArrayList<MissyouBoard> missList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missyou);
        SearchView searchView = findViewById(R.id.SearchViewMiss);
        Button writeMissyou = findViewById(R.id.writeMissyou);
        ImageView missyouImg = findViewById(R.id.missyou);
        Button DogMissbtn = findViewById(R.id.DogMissbtn);
        Button CatMissbtn = findViewById(R.id.CatMissbtn);
        Button EtcMissbtn = findViewById(R.id.EtcMissbtn);

        recyclerView = findViewById(R.id.recylerviewMissyou);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Missyou.this,LinearLayoutManager.VERTICAL,false);
        missList =  new ArrayList<>();
        missyouAdapter = new MissyouAdapter(missList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(missyouAdapter);

        AppService appService = AppClient.getInstance().getAppService();
        Call<List<MissyouBoard>> call = appService.missing_list();

        call.enqueue(new Callback<List<MissyouBoard>>() {
            @Override
            public void onResponse(Call<List<MissyouBoard>> call, Response<List<MissyouBoard>> response) {
                for(MissyouBoard m : response.body()){
                    missyouAdapter.addItem(m);
                }
                missyouAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<MissyouBoard>> call, Throwable t) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                AppService appService = AppClient.getInstance().getAppService();
                Call<List<MissyouBoard>> call = appService.findAll_miss(query);
                missyouAdapter.removeAllItem(missList);
                call.enqueue(new Callback<List<MissyouBoard>>() {
                    @Override
                    public void onResponse(Call<List<MissyouBoard>> call, Response<List<MissyouBoard>> response) {
                        for(MissyouBoard m : response.body()){
                            missyouAdapter.addItem(m);
                        }
                        missyouAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<MissyouBoard>> call, Throwable t) {

                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        DogMissbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dog = "강아지";
                AppService appService = AppClient.getInstance().getAppService();
                Call<List<MissyouBoard>> call = appService.missingDog(dog);
                missyouAdapter.removeAllItem(missList);
                call.enqueue(new Callback<List<MissyouBoard>>() {
                    @Override
                    public void onResponse(Call<List<MissyouBoard>> call, Response<List<MissyouBoard>> response) {
                        for(MissyouBoard m : response.body()){
                            missyouAdapter.addItem(m);
                        }
                       missyouAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<MissyouBoard>> call, Throwable t) {

                    }
                });
            }
        });

        CatMissbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cat = "고양이";
                AppService appService = AppClient.getInstance().getAppService();
                missyouAdapter.removeAllItem(missList);
                Call<List<MissyouBoard>> call = appService.missingCat(cat);
                call.enqueue(new Callback<List<MissyouBoard>>() {
                    @Override
                    public void onResponse(Call<List<MissyouBoard>> call, Response<List<MissyouBoard>> response) {
                        for(MissyouBoard m : response.body()){
                            missyouAdapter.addItem(m);
                        }
                        missyouAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<MissyouBoard>> call, Throwable t) {

                    }
                });
            }
        });

        EtcMissbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etc = "기타";
                AppService appService = AppClient.getInstance().getAppService();
                Call<List<MissyouBoard>> call = appService.missingEtc(etc);
                missyouAdapter.removeAllItem(missList);

                call.enqueue(new Callback<List<MissyouBoard>>() {
                    @Override
                    public void onResponse(Call<List<MissyouBoard>> call, Response<List<MissyouBoard>> response) {
                        for(MissyouBoard m : response.body()){
                            missyouAdapter.addItem(m);
                        }
                        missyouAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<MissyouBoard>> call, Throwable t) {

                    }
                });

            }
        });


        missyouImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        writeMissyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Missyou_Insert.class);
                startActivity(intent);
            }
        });
    }
}