package com.example.team1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Find extends AppCompatActivity {
private FindAdapter findAdapter;
private RecyclerView recyclerView;
private ArrayList<FindBoard> findList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        ImageView missingImg = findViewById(R.id.missing);
        Button writeMissing = findViewById(R.id.writeMissing);

        SearchView searchView = findViewById(R.id.searchViewFind);
        Button DogFindbtn = findViewById(R.id.DogFindbtn);
        Button CatFindbtn = findViewById(R.id.CatFindbtn);
        Button EtcFindbtn = findViewById(R.id.EtcFindbtn);
        searchView.isSubmitButtonEnabled();

        recyclerView = findViewById(R.id.recylerViewFind);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Find.this,LinearLayoutManager.VERTICAL,false);
        Log.d("linearLayoutManager",linearLayoutManager+"" );
        findList = new ArrayList<>();
        findAdapter = new FindAdapter(findList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(findAdapter);


        AppService appService = AppClient.getInstance().getAppService();
        Call<List<FindBoard>> call = appService.find_list();
        call.enqueue(new Callback<List<FindBoard>>() {
            @Override
            public void onResponse(Call<List<FindBoard>> call, Response<List<FindBoard>> response) {
                for(FindBoard f : response.body()){
                    findAdapter.addItem(f);
                }
                findAdapter.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Call<List<FindBoard>> call, Throwable t) {
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                    AppService appService = AppClient.getInstance().getAppService();
                    Call<List<FindBoard>> call = appService.findAll(query);
                    findAdapter.removeAllItem(findList);
                    call.enqueue(new Callback<List<FindBoard>>() {
                        @Override
                        public void onResponse(Call<List<FindBoard>> call, Response<List<FindBoard>> response) {
                            for(FindBoard f : response.body()){
                                findAdapter.addItem(f);
                            }
                            findAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<List<FindBoard>> call, Throwable t) {

                        }
                    });

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        DogFindbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String dog = "강아지";
               AppService appService = AppClient.getInstance().getAppService();
               Call<List<FindBoard>> call = appService.findDog(dog);

                findAdapter.removeAllItem(findList);
               call.enqueue(new Callback<List<FindBoard>>() {
                   @Override
                   public void onResponse(Call<List<FindBoard>> call, Response<List<FindBoard>> response) {
                       for(FindBoard f : response.body()){

                           findAdapter.addItem(f);
                       }
                       findAdapter.notifyDataSetChanged();
                   }

                   @Override
                   public void onFailure(Call<List<FindBoard>> call, Throwable t) {

                   }
               });

            }
        });


        CatFindbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Cat ="고양이";
                AppService appService = AppClient.getInstance().getAppService();
                Call<List<FindBoard>> call = appService.findCat(Cat);
                findAdapter.removeAllItem(findList);
                call.enqueue(new Callback<List<FindBoard>>() {
                    @Override
                    public void onResponse(Call<List<FindBoard>> call, Response<List<FindBoard>> response) {
                        for(FindBoard f : response.body()){
                            findAdapter.addItem(f);
                        }
                        findAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<FindBoard>> call, Throwable t) {

                    }
                });
            }
        });

        EtcFindbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etc = "기타";
                AppService appService = AppClient.getInstance().getAppService();
                Call<List<FindBoard>> call = appService.findEtc(etc);
                findAdapter.removeAllItem(findList);

                call.enqueue(new Callback<List<FindBoard>>() {
                    @Override
                    public void onResponse(Call<List<FindBoard>> call, Response<List<FindBoard>> response) {

                        for(FindBoard f : response.body()){
                            findAdapter.addItem(f);
                        }
                        findAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<FindBoard>> call, Throwable t) {

                    }
                });
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
                Intent intent = new Intent(getApplicationContext(), FindBoard_Insert.class);
                startActivity(intent);
            }
        });

    }
}