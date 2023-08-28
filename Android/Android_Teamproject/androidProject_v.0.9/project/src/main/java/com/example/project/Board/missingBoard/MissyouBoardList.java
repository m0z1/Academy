package com.example.project.Board.missingBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.project.Board.BoardClient;
import com.example.project.Board.BoardInterface;
import com.example.project.MainActivity;
import com.example.project.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MissyouBoardList extends AppCompatActivity {

    List<MissingBoard> missingBoardList;
    MissyouAdapter missyouAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missyou_board_list);

        Button writeMissyou = findViewById(R.id.writeMissyou);
        ImageView missyouImg = findViewById(R.id.missyou);
        RecyclerView recyclerView = findViewById(R.id.recylerView);

        //이미지 클릭 -> 홈으로
        missyouImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //글 작성하기
        writeMissyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(), MissingBoardActivity.class);
                startActivity(intent);
            }
        });

        //전체보기
        missingBoardList = new ArrayList<>();
        missyouAdapter = new MissyouAdapter(missingBoardList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setAdapter(missyouAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
        Call<List<MissingBoard>> call = boardInterface.missing_list();
        call.enqueue(new Callback<List<MissingBoard>>() {
            @Override
            public void onResponse(Call<List<MissingBoard>> call, Response<List<MissingBoard>> response) {
                List<MissingBoard> resource = response.body();
                for(MissingBoard missingBoard : resource){
                    missingBoardList.add(missingBoard);
                }
                missyouAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<MissingBoard>> call, Throwable t) {

            }
        });


    }
}