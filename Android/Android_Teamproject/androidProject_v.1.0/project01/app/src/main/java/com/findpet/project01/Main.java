package com.findpet.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.findpet.project01.Board.BoardClient;
import com.findpet.project01.Board.BoardInterface;
import com.findpet.project01.Board.findBoard.FindAdapter;
import com.findpet.project01.Board.findBoard.FindBoard;
import com.findpet.project01.Board.findBoard.FindBoardList;
import com.findpet.project01.Board.missingBoard.MissingBoard;
import com.findpet.project01.Board.missingBoard.MissyouAdapter;
import com.findpet.project01.Board.missingBoard.MissyouBoardList;
import com.findpet.project01.Board.shelter.ShelterBoardList;
import com.findpet.project01.Board.storyBoard.StoryBoardList;
import com.findpet.project01.account.Client;
import com.findpet.project01.account.Login;
import com.findpet.project01.account.Member;
import com.findpet.project01.account.MemberInfo;
import com.findpet.project01.account.MemberService;
import com.findpet.project01.databinding.ActivityMainBinding;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main extends AppCompatActivity {

    private FindAdapter findAdapter;
    private MissyouAdapter missyouAdapter;

    ImageView movemain, memberImage;
    TextView membername;
    String name = "";
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        memberImage = findViewById(R.id.member);
        movemain = findViewById(R.id.Home);
        membername = findViewById(R.id.memberName);


        SharedPreferences sharedPreferences = getSharedPreferences("autoLogin", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

    /*    MemberService memberService = Client.getInstance().getMemberService();
        Call<Member> call = memberService.findmember(username);
        call.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                Member member = response.body();
                if(member.getName() == null){
                    member.setName("unknown");
                }
                if(member.getName()!=null) {
                    name = member.getName().toString();
                } else {
                    name = "unKnown";
                }
                if(!name.equals("")){
                    membername.setText(name + " 님 환영합니다");
                }
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {

            }
        });
*/


        BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();


        Call<List<FindBoard>> call = boardInterface.find_list();
        Call<List<MissingBoard>> call2 = boardInterface.missing_list();

        call.enqueue(new Callback<List<FindBoard>>() {
            @Override
            public void onResponse(Call<List<FindBoard>> call, Response<List<FindBoard>> response) {
                for (FindBoard f : response.body()){
                    findAdapter.addItem(f);
                }
                findAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<FindBoard>> call, Throwable t) {

            }
        });

        call2.enqueue(new Callback<List<MissingBoard>>() {
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



        movemain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
            }
        });


        if(username != null) {
            memberImage.setEnabled(true);
        } else if( username == null) {
            Intent intent = new Intent(Main.this, Login.class);
            startActivity(intent);
        }

        memberImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main.this, MemberInfo.class);
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