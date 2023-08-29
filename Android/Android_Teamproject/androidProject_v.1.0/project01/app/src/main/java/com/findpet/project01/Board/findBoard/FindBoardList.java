package com.findpet.project01.Board.findBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.findpet.project01.Board.BoardClient;
import com.findpet.project01.Board.BoardInterface;
import com.findpet.project01.Board.missingBoard.MissyouBoardList;
import com.findpet.project01.Board.shelter.ShelterBoardList;
import com.findpet.project01.Board.storyBoard.StoryBoardList;
import com.findpet.project01.Main;
import com.findpet.project01.R;
import com.findpet.project01.account.Client;
import com.findpet.project01.account.Member;
import com.findpet.project01.account.MemberService;
import com.findpet.project01.databinding.ActivityFindBoardBinding;
import com.findpet.project01.databinding.ActivityFindBoardListBinding;
import com.findpet.project01.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindBoardList extends AppCompatActivity {
    private FindAdapter findAdapter;
    private RecyclerView recyclerView;
    private ArrayList<FindBoard> findList;
    private ActivityFindBoardListBinding binding;
    private String name = "";
    private Button gotomissing, gotomissyou, story, protect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindBoardListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ImageView missingImg = findViewById(R.id.missing);
        Button writeMissing = findViewById(R.id.writeMissing);
        TextView memberName = findViewById(R.id.memberName);


        SearchView searchView = findViewById(R.id.searchViewFind);
        Button DogFindbtn = findViewById(R.id.DogFindbtn);
        Button CatFindbtn = findViewById(R.id.CatFindbtn);
        Button EtcFindbtn = findViewById(R.id.EtcFindbtn);
        gotomissing = findViewById(R.id.goTomissing);
        gotomissyou = findViewById(R.id.goTomissyou);
        searchView.isSubmitButtonEnabled();

        recyclerView = findViewById(R.id.recylerViewFind);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FindBoardList.this,LinearLayoutManager.VERTICAL,false);
        Log.d("linearLayoutManager",linearLayoutManager+"" );
        findList = new ArrayList<>();
        findAdapter = new FindAdapter(findList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(findAdapter);

        SharedPreferences sharedPreferences = getSharedPreferences("autoLogin", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        //유저 이름 표시
        MemberService memberService = Client.getInstance().getMemberService();
        Call<Member> call1 = memberService.findmember(username);
        call1.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                Member member = response.body();
                if(member.getName()!=null) {
                    name = member.getName().toString();
                } else {
                    name = "";
                }
                if(!name.equals("")){
                    memberName.setText(name + " 님 환영합니다");
                }
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {

            }
        });

        //전체 보기
        BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
        String dog = "강아지";
        Call<List<FindBoard>> call = boardInterface.findDog(dog);
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


        // 아이템 클릭시 상세보기
        findAdapter.setOnItemClickListener(new FindAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int pos) {
                Intent intent = new Intent(getApplicationContext(), FindBoardDetail.class);
                startActivity(intent);
            }
        });

        //검색 기능
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

        //강아지 정보 태그
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

        //고양이 정보 태그
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

        // 기타 동물 정보 태그
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
                Intent intent = new Intent(getApplicationContext(), FindBoardActivity.class);
                startActivity(intent);
            }
        });

    }
}