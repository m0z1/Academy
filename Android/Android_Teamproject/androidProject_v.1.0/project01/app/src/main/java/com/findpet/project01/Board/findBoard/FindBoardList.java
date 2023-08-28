package com.findpet.project01.Board.findBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class FindBoardList extends AppCompatActivity {
    private FindAdapter findAdapter;
    private RecyclerView recyclerView;
    private ArrayList<FindBoard> findList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.findpet.project01.R.layout.activity_find_board_list);

        ImageView missingImg = findViewById(R.id.missing);
        Button writeMissing = findViewById(R.id.writeMissing);

        SearchView searchView = findViewById(R.id.searchViewFind);
        Button DogFindbtn = findViewById(R.id.DogFindbtn);
        Button CatFindbtn = findViewById(R.id.CatFindbtn);
        Button EtcFindbtn = findViewById(R.id.EtcFindbtn);
        searchView.isSubmitButtonEnabled();

        recyclerView = findViewById(R.id.recylerViewFind);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FindBoardList.this,LinearLayoutManager.VERTICAL,false);
        Log.d("linearLayoutManager",linearLayoutManager+"" );
        findList = new ArrayList<>();
        findAdapter = new FindAdapter(findList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(findAdapter);


        BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
        Call<List<FindBoard>> call = boardInterface.find_list();
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

        findAdapter.setOnItemClickListener(new FindAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int pos) {
                Intent intent = new Intent(getApplicationContext(), FindBoardDetail.class);
                startActivity(intent);
            }
        }); // 아이템 클릭시 상세보기

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
                Call<List<FindBoard>> call = boardInterface.findAll(query);
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
                BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
                Call<List<FindBoard>> call = boardInterface.findDog(dog);

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
                BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
                Call<List<FindBoard>> call = boardInterface.findCat(Cat);
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
                BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
                Call<List<FindBoard>> call = boardInterface.findEtc(etc);
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
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
            }
        });

        writeMissing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindBoardActivity.class);
                startActivity(intent);
            }
        });

    }
}