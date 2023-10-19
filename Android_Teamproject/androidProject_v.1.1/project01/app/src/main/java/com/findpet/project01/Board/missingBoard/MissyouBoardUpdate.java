package com.findpet.project01.Board.missingBoard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.findpet.project01.Board.BoardClient;
import com.findpet.project01.Board.BoardInterface;
import com.findpet.project01.Board.findBoard.FindBoard;
import com.findpet.project01.Board.findBoard.FindBoardDetail;
import com.findpet.project01.Board.findBoard.FindBoardUpdate;
import com.findpet.project01.R;
import com.findpet.project01.databinding.ActivityMissyouBoardUpdateBinding;;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MissyouBoardUpdate extends AppCompatActivity {

    private ActivityMissyouBoardUpdateBinding binding;
    String baseUrl = "http://10.100.102.44:8899";
    private ArrayList<Uri> filePathList = new ArrayList<>(3);

    private ArrayList<MultipartBody.Part> imgFileList = new ArrayList<>(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMissyouBoardUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //게시글 번호 intent로 받기
        Intent intent = getIntent();
        Long missingId1 = intent.getLongExtra("missingId", 0);

        filePathList.add(null);
        filePathList.add(null);
        filePathList.add(null);

        //게시글 뿌려주기
        BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
        Call<MissingBoard> call = boardInterface.view2(missingId1);
        call.enqueue(new Callback<MissingBoard>() {
            @Override
            public void onResponse(Call<MissingBoard> call, Response<MissingBoard> response) {
                MissingBoard missingBoard = response.body();
                binding.edmissingAddr.setText(missingBoard.getMissingaddr());
                binding.edBreed.setText(missingBoard.getBreed());
                if(response.body().getPetcategory().equals("강아지")){
                    binding.petcategory.setSelection(0);
                } else if (response.body().getPetcategory().equals("고양이")) {
                    binding.petcategory.setSelection(1);
                } else {
                    binding.petcategory.setSelection(2);
                }

                if(response.body().getPetgender().equals("남아")){
                    binding.petgenderHe.setChecked(true);
                } else {
                    binding.petgenderShe.setChecked(true);
                }
                binding.edPetCharacter.setText(missingBoard.getPetcharacter());
                binding.edPetAge.setText(missingBoard.getPetage());
                binding.edPetName.setText(missingBoard.getPetname());
                binding.edContent.setText(missingBoard.getContent());

                //이미지 받아오기
                if(response.body().getImgFileList()!=null) {
                    Log.d("imgFileList.size", "size : " + response.body().getImgFileList().size());
                    for(int i = 0; i < response.body().getImgFileList().size(); i++) {
                        List<ImageView> viewList = Arrays.asList(binding.img1, binding.img2, binding.img3);

                        Glide.with(getApplicationContext())
                                .load(baseUrl + response.body().getImgFileList().get(i).getImgUrl())
                                .override(500)
                                .into(viewList.get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<MissingBoard> call, Throwable t) {

            }
        });

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
        binding.btnOK.setOnClickListener(view -> {

            for (int i = 0; i < filePathList.size(); ++i) {
                if(filePathList.get(i)!=null){
                    Cursor cursor = getContentResolver().query(filePathList.get(i),null,null,null,null);
                    cursor.moveToNext();
                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                    cursor.close();

                    File imgFile = new File(path);
                    RequestBody fileRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), imgFile);
                    MultipartBody.Part filePart = MultipartBody.Part.createFormData("imgFile", imgFile.getName(), fileRequestBody);

                    imgFileList.add(filePart);
                }
            }

            RequestBody missingId = RequestBody.create(MediaType.parse("text/plain"),missingId1.toString());
            RequestBody petname = RequestBody.create(MediaType.parse("text/plain"),binding.edPetName.getText().toString());
            RequestBody breed = RequestBody.create(MediaType.parse("text/plain"),binding.edBreed.getText().toString());
            RequestBody petage = RequestBody.create(MediaType.parse("text/plain"),binding.edPetAge.getText().toString());
            RequestBody content = RequestBody.create(MediaType.parse("text/plain"),binding.edContent.getText().toString());
            RequestBody petcharacter = RequestBody.create(MediaType.parse("text/plain"),binding.edPetCharacter.getText().toString());
            RequestBody missingaddr = RequestBody.create(MediaType.parse("text/plain"),binding.edmissingAddr.getText().toString());
            RequestBody petcategory = RequestBody.create(MediaType.parse("text/plain"),binding.petcategory.getSelectedItem().toString());
            RequestBody petgender;

            if(binding.petgenderHe.isChecked()){
                petgender = RequestBody.create(MediaType.parse("text/plain"),"남아");
            } else {
                petgender = RequestBody.create(MediaType.parse("text/plain"),"여아");
            }

            Map<String, RequestBody> map = new HashMap<>();
            map.put("missingId", missingId);
            map.put("petname", petname);
            map.put("breed", breed);
            map.put("petage", petage);
            map.put("content", content);
            map.put("petcharacter", petcharacter);
            map.put("missingaddr", missingaddr);
            map.put("petcategory", petcategory);
            map.put("petgender", petgender);





            BoardInterface boardInterFace = BoardClient.getInstance().getBoardInterface();
            Call<MissingBoard> call1 = boardInterFace.updateMissingBoard(imgFileList,map);
            call1.enqueue(new Callback<MissingBoard>() {
                @Override
                public void onResponse(Call<MissingBoard> call, Response<MissingBoard> response) {
                    Log.d("MissingBoard 등록 Response", "등록성공");
                    Intent intent1 = new Intent(MissyouBoardUpdate.this, MissyouBoardDetail.class);
                    intent1.putExtra("missingId", missingId1);
                    startActivity(intent1.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT));
                }

                @Override
                public void onFailure(Call<MissingBoard> call, Throwable t) {
                    Log.d("MissingBoard 등록 failure", t.toString());
                }
            });
        });
        /*binding.btnOK.setOnClickListener(new View.OnClickListener() {
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
                Call<MissingBoard> call1 = boardInterface1.updateMissingBoard(missingBoard);
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
        });*/


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