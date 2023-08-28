package com.findpet.project01.Board.missingBoard;

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
import com.findpet.project01.databinding.ActivityMissyouBoardUpdateBinding;;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MissyouBoardUpdate extends AppCompatActivity {

    private ActivityMissyouBoardUpdateBinding binding;
    String basUrl = "http://10.100.102.45:8899";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.findpet.project01.R.layout.activity_missyou_board_update);
        binding = ActivityMissyouBoardUpdateBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //게시글 번호 intent로 받기
        Intent intent = getIntent();
        Long missingId = intent.getLongExtra("missingId", 0);

        //게시글 뿌려주기
        BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
        Call<MissingBoard> call = boardInterface.view2(missingId);
        call.enqueue(new Callback<MissingBoard>() {
            @Override
            public void onResponse(Call<MissingBoard> call, Response<MissingBoard> response) {
                MissingBoard missingBoard = response.body();
                binding.edFindAddr.setText(missingBoard.getMissingaddr());
                binding.edBreed.setText(missingBoard.getBreed());
                binding.edPetGender.setText(missingBoard.getPetgender());
                binding.edPetCharacter.setText(missingBoard.getPetcharacter());
                binding.edPetAge.setText(missingBoard.getPetage());
                binding.edPetName.setText(missingBoard.getPetname());
                binding.edContent.setText(missingBoard.getContent());

                //이미지 받아오기
                Glide.with(getApplicationContext())
                        .load(basUrl+response.body().getImgFileList().get(0).getImgUrl())
                        .override(500,400)
                        .into(binding.img1);
                Glide.with(getApplicationContext())
                        .load(basUrl+response.body().getImgFileList().get(1).getImgUrl())
                        .override(500,400)
                        .into(binding.img2);
                Glide.with(getApplicationContext())
                        .load(basUrl+response.body().getImgFileList().get(2).getImgUrl())
                        .override(500,400)
                        .into(binding.img3);
            }

            @Override
            public void onFailure(Call<MissingBoard> call, Throwable t) {

            }
        });

//        //수정 버튼
        binding.btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MissingBoard missingBoard = new MissingBoard(missingId,
                        binding.edBreed.getText().toString(),
                        binding.edContent.getText().toString(),
                        binding.edPetName.getText().toString(),
                        binding.edPetAge.getText().toString(),
                        binding.edPetGender.getText().toString(),
                        binding.edPetCharacter.getText().toString(),
                        binding.edFindAddr.getText().toString());
                BoardInterface boardInterface1 = BoardClient.getInstance().getBoardInterface();
                Call<MissingBoard> call1 = boardInterface1.update2(missingBoard);
                call1.enqueue(new Callback<MissingBoard>() {
                    @Override
                    public void onResponse(Call<MissingBoard> call, Response<MissingBoard> response) {
                        MissingBoard missingBoard1 = response.body();
                        Intent intent1 = new Intent(getApplicationContext(), MissyouBoardDetail.class);
                        intent1.putExtra("missingBoard", missingBoard1);
                        setResult(RESULT_FIRST_USER, intent1);
                        Toast.makeText(getApplicationContext(), "수정 완료", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<MissingBoard> call, Throwable t) {

                    }
                });
            }
        });


        //취소
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("정말 수정을 취소할까요?");
                dlg.setCancelable(false);   //두 개 버튼 중 하나 무조건 클릭해야 닫힘
                dlg.setPositiveButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();   //다이얼로그 닫기
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