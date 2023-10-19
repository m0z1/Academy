package com.findpet.project01.Board.missingBoard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.findpet.project01.Board.BoardClient;
import com.findpet.project01.Board.BoardInterface;
import com.findpet.project01.Board.findBoard.FindAdapter;
import com.findpet.project01.Board.findBoard.FindBoard;
import com.findpet.project01.Board.findBoard.FindBoardActivity;
import com.findpet.project01.Board.findBoard.FindBoardDetail;
import com.findpet.project01.Board.findBoard.FindBoardList;
import com.findpet.project01.Board.shelter.ShelterBoardList;
import com.findpet.project01.Board.storyBoard.StoryBoardList;
import com.findpet.project01.Main;
import com.findpet.project01.R;
import com.findpet.project01.account.Client;
import com.findpet.project01.account.Member;
import com.findpet.project01.account.MemberInfo;
import com.findpet.project01.account.MemberService;
import com.findpet.project01.databinding.ActivityMainBinding;
import com.findpet.project01.databinding.ActivityMissyouBoardListBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MissyouBoardList extends AppCompatActivity {
    private MissyouAdapter missyouAdapter;
    private RecyclerView recyclerView;
    private ArrayList<MissingBoard> missingList;
    private ActivityMissyouBoardListBinding binding;
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMissyouBoardListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ImageView missingImg = findViewById(R.id.missyou);
        Button writeMissing = findViewById(R.id.writeMissyou);
        TextView memberName = findViewById(R.id.memberName);


        SearchView searchView = findViewById(R.id.SearchViewMiss);
        Button DogMissbtn = findViewById(R.id.DogMissbtn);
        Button CatMissbtn = findViewById(R.id.CatMissbtn);
        Button EtcMissbtn = findViewById(R.id.EtcMissbtn);
        searchView.isSubmitButtonEnabled();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MissyouBoardList.this,LinearLayoutManager.VERTICAL,false);
        recyclerView = findViewById(R.id.recylerviewMissyou);
        Log.d("linearLayoutManager",linearLayoutManager+"" );
        missingList = new ArrayList<>();
        missyouAdapter = new MissyouAdapter(missingList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(missyouAdapter);

        SharedPreferences sharedPreferences = getSharedPreferences("autoLogin", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        binding.member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MissyouBoardList.this, MemberInfo.class);
                startActivity(intent);
            }
        });

        //전체 보기
        init();
        DogMissbtn.setSelected(true);
        CatMissbtn.setSelected(false);
        EtcMissbtn.setSelected(false);

        // 아이템 클릭시 상세보기
        missyouAdapter.setOnItemClickListener(new MissyouAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int pos) {
                Intent intent = new Intent(getApplicationContext(), MissyouBoardDetail.class);
                startActivity(intent);
            }
        });

        //검색 기능
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
                Call<List<MissingBoard>> call = boardInterface.findAll_miss(query);
                missyouAdapter.removeAllItem(missingList);
                call.enqueue(new Callback<List<MissingBoard>>() {
                    @Override
                    public void onResponse(Call<List<MissingBoard>> call, Response<List<MissingBoard>> response) {
                        for(MissingBoard m : response.body()){
                            missyouAdapter.addItem(m);
                        }
                        missyouAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<MissingBoard>> call, Throwable t) {

                    }
                });

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //강아지 정보 태그
        DogMissbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //카테고리 버튼 선택 시 색상 변경(0829)
                DogMissbtn.setSelected(true);
                CatMissbtn.setSelected(false);
                EtcMissbtn.setSelected(false);

                String dog = "강아지";
                BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
                Call<List<MissingBoard>> call = boardInterface.MissingDog(dog);

                missyouAdapter.removeAllItem(missingList);
                call.enqueue(new Callback<List<MissingBoard>>() {
                    @Override
                    public void onResponse(Call<List<MissingBoard>> call, Response<List<MissingBoard>> response) {
                        for(MissingBoard m : response.body()){

                            missyouAdapter.addItem(m);
                        }
                        missyouAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<MissingBoard>> call, Throwable t) {

                    }
                });

            }
        });

        //고양이 정보 태그
        CatMissbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //카테고리 버튼 선택 시 색상 변경(0829)
                DogMissbtn.setSelected(false);
                CatMissbtn.setSelected(true);
                EtcMissbtn.setSelected(false);

                String Cat ="고양이";
                BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
                Call<List<MissingBoard>> call = boardInterface.MissingCat(Cat);
                missyouAdapter.removeAllItem(missingList);
                call.enqueue(new Callback<List<MissingBoard>>() {
                    @Override
                    public void onResponse(Call<List<MissingBoard>> call, Response<List<MissingBoard>> response) {
                        for(MissingBoard m : response.body()){
                            missyouAdapter.addItem(m);
                        }
                        missyouAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<MissingBoard>> call, Throwable t) {

                    }
                });
            }
        });

        // 기타 동물 정보 태그
        EtcMissbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //카테고리 버튼 선택 시 색상 변경(0829)
                DogMissbtn.setSelected(false);
                CatMissbtn.setSelected(false);
                EtcMissbtn.setSelected(true);

                String etc = "기타";
                BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
                Call<List<MissingBoard>> call = boardInterface.MissingEtc(etc);
                missyouAdapter.removeAllItem(missingList);

                call.enqueue(new Callback<List<MissingBoard>>() {
                    @Override
                    public void onResponse(Call<List<MissingBoard>> call, Response<List<MissingBoard>> response) {

                        for(MissingBoard m : response.body()){
                            missyouAdapter.addItem(m);
                        }
                        missyouAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<MissingBoard>> call, Throwable t) {

                    }
                });
            }
        });

        //홈으로 이동
        missingImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
            }
        });

        // 발견자 게시판으로 가는 버튼
        binding.goTomissing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindBoardList.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        // 실종 주인 게시판으로 가는 버튼
        binding.goTomissyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MissyouBoardList.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        //스토리 게시판으로 가는 버튼
        binding.Story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StoryBoardList.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        //보호소 게시판으로 가는 버튼
        binding.protect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShelterBoardList.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        //글쓰기 버튼
        writeMissing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MissingBoardActivity.class);
                startActivity(intent);
            }
        });

    }
    public void init() {
        BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();

        String dog = "강아지";
        Call<List<MissingBoard>> call = boardInterface.MissingDog(dog);
        call.enqueue(new Callback<List<MissingBoard>>() {
            @Override
            public void onResponse(Call<List<MissingBoard>> call, Response<List<MissingBoard>> response) {
                for (MissingBoard m : response.body()) {
                    missyouAdapter.addItem(m);
                }
                missyouAdapter.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Call<List<MissingBoard>> call, Throwable t) {
            }
        });
    }
}