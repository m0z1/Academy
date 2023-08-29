package com.findpet.project01.Board.storyBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.findpet.project01.Board.BoardClient;
import com.findpet.project01.Board.BoardInterface;
import com.findpet.project01.Board.missingBoard.MissyouBoardList;
import com.findpet.project01.Board.shelter.ShelterBoardList;
import com.findpet.project01.Main;
import com.findpet.project01.R;
import com.findpet.project01.account.Client;
import com.findpet.project01.account.Member;
import com.findpet.project01.account.MemberService;
import com.findpet.project01.databinding.ActivityMissyouBoardListBinding;
import com.findpet.project01.databinding.ActivityStoryBoardListBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoryBoardList extends AppCompatActivity {

    List<StoryBoard> storyBoardList;
    StoryAdapter storyAdapter;
    String name = "";
    private ActivityStoryBoardListBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoryBoardListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Button writeStroy = findViewById(R.id.writeStory);
        ImageView StoryImg = findViewById(R.id.StoryImg);
        RecyclerView recyclerView = findViewById(R.id.recylerView);
        TextView membername = findViewById(R.id.memberName);

        SharedPreferences sharedPreferences = getSharedPreferences("autoLogin", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

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
                    membername.setText(name + " 님 환영합니다");
                }
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {

            }
        });


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

        // 발견자 게시판으로 가는 버튼
        binding.goTomissing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MissyouBoardList.class);
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