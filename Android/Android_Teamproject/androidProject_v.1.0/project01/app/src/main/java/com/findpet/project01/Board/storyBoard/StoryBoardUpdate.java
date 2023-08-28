package com.findpet.project01.Board.storyBoard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.findpet.project01.Board.BoardClient;
import com.findpet.project01.Board.BoardInterface;
import com.findpet.project01.R;
import com.findpet.project01.databinding.ActivityStoryBoardUpdateBinding;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoryBoardUpdate extends AppCompatActivity {

    List<StoryBoard> storyBoardList;

    private ActivityStoryBoardUpdateBinding binding;

    String baseUrl = "http://10.100.102.45:8899";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_board_update);

        binding = ActivityStoryBoardUpdateBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //게시글 번호 Intent로 받기
        Intent intent = getIntent();
        Long storyId = intent.getLongExtra("storyId", 0);

        //게시글 뿌려주기
        BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
        Call<StoryBoard> call = boardInterface.view3(storyId);
        call.enqueue(new Callback<StoryBoard>() {
            @Override
            public void onResponse(Call<StoryBoard> call, Response<StoryBoard> response) {
                StoryBoard storyBoard = response.body();
                binding.edTitle.setText(storyBoard.getTitle());
                binding.edContent.setText(storyBoard.getContent());
                //이미지 가져오기
                Glide.with(getApplicationContext())
                        .load(baseUrl+response.body().getImgFileList().get(0).getImgUrl())
                        .override(500,400)
                        .into(binding.img1);
                Glide.with(getApplicationContext())
                        .load(baseUrl+response.body().getImgFileList().get(1).getImgUrl())
                        .override(500,400)
                        .into(binding.img2);
                Glide.with(getApplicationContext())
                        .load(baseUrl+response.body().getImgFileList().get(2).getImgUrl())
                        .override(500,400)
                        .into(binding.img3);
            }

            @Override
            public void onFailure(Call<StoryBoard> call, Throwable t) {

            }
        });

        //수정버튼
        binding.btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoryBoard storyBoard = new StoryBoard(
                        storyId,
                        binding.edTitle.getText().toString(),
                        binding.edContent.getText().toString());
                BoardInterface boardInterface1 = BoardClient.getInstance().getBoardInterface();
                Call<StoryBoard> call1 = boardInterface1.update3(storyBoard);
                call1.enqueue(new Callback<StoryBoard>() {
                    @Override
                    public void onResponse(Call<StoryBoard> call, Response<StoryBoard> response) {
                        StoryBoard storyBoard1 = response.body();
                        Intent intent1 = new Intent(getApplicationContext(), StoryBoardDetail.class);
                        intent1.putExtra("storyBoard", storyBoard1);
                        setResult(RESULT_FIRST_USER, intent1);
                        Toast.makeText(getApplicationContext(), "수정 완료", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<StoryBoard> call, Throwable t) {

                    }
                });

            }
        });
        
        //취소버튼
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(getApplicationContext());
                dlg.setTitle("정말 수정을 취소할까요?");
                dlg.setCancelable(false);
                dlg.setPositiveButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                dlg.setNegativeButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                dlg.show();
            }
        });

    }
}