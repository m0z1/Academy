package com.findpet.project01.Board.findBoard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.findpet.project01.Board.BoardClient;
import com.findpet.project01.Board.BoardInterface;
import com.findpet.project01.R;
import com.findpet.project01.databinding.ActivityFindBoardUpdateBinding;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindBoardUpdate extends AppCompatActivity {

    List<FindBoard> findBoardList;

    private ActivityFindBoardUpdateBinding binding;

    String basUrl = "http://10.100.102.44:8899";

    private ArrayList<Uri> filePathList = new ArrayList<>(3);

    private ArrayList<MultipartBody.Part> imgFileList = new ArrayList<>(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.findpet.project01.R.layout.activity_find_board_update);binding = ActivityFindBoardUpdateBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //게시글 번호 intent로 받기
        Intent intent = getIntent();
        Long findId = intent.getLongExtra("findId", 0);

        //게시글 뿌려주기
        BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
        Call<FindBoard> call = boardInterface.view(findId);
        call.enqueue(new Callback<FindBoard>() {
            @Override
            public void onResponse(Call<FindBoard> call, Response<FindBoard> response) {
                FindBoard findBoard = response.body();
                binding.edFindAddr.setText(findBoard.getFindaddr());
                binding.edBreed.setText(findBoard.getBreed());
                binding.edPetGender.setText(findBoard.getPetgender());
                binding.edPetCharacter.setText(findBoard.getPetcharacter());
                binding.edPetAge.setText(findBoard.getPetage());
                binding.edPetName.setText(findBoard.getPetname());
                binding.edContent.setText(findBoard.getContent());

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
            public void onFailure(Call<FindBoard> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "상세보기 불러오기 실패", Toast.LENGTH_SHORT).show();
            }
        });

        filePathList.add(null);
        filePathList.add(null);
        filePathList.add(null);

        //이미지 변경 버튼
        binding.btnImageChange1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });

        binding.btnImageChange2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,2);
            }
        });

        binding.btnImageChange3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,3);
            }
        });

        //수정 버튼
        binding.btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FindBoard findBoard = new FindBoard(findId,
                        binding.edBreed.getText().toString(),
                        binding.edContent.getText().toString(),
                        binding.edPetName.getText().toString(),
                        binding.edPetAge.getText().toString(),
                        binding.edPetGender.getText().toString(),
                        binding.edPetCharacter.getText().toString(),
                        binding.edFindAddr.getText().toString());
                BoardInterface boardInterface1 = BoardClient.getInstance().getBoardInterface();
                Call<FindBoard> call1 = boardInterface1.update1(findBoard);
                call1.enqueue(new Callback<FindBoard>() {
                    @Override
                    public void onResponse(Call<FindBoard> call, Response<FindBoard> response) {
                        FindBoard findBoard1 = response.body();
                        Intent intent1 = new Intent(getApplicationContext(), FindBoardDetail.class);
                        intent1.putExtra("findBoard", findBoard1);
                        setResult(RESULT_FIRST_USER, intent1);
                        Toast.makeText(getApplicationContext(), "수정 완료", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<FindBoard> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "수정을 실패했습니다!", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(data==null){
                return;
            }
            Uri uri = data.getData();
            if(uri != null){
                binding.img1.setImageURI(uri);
                filePathList.set(0,uri);
                Log.d("img1", uri.getPath());
            }
        } else if (requestCode == 2) {
            if(data==null){
                return;
            }
            Uri uri = data.getData();
            if(uri != null){
                binding.img2.setImageURI(uri);
                filePathList.set(1,uri);
                Log.d("img2", uri.getPath());
            }
        } else if (requestCode == 3) {
            if(data==null){
                return;
            }
            Uri uri = data.getData();
            if(uri != null){
                binding.img3.setImageURI(uri);
                filePathList.set(2,uri);
                Log.d("img3", uri.getPath());
            }
        }
    }
}