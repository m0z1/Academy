package com.findpet.project01.Board.findBoard;

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
import com.findpet.project01.R;
import com.findpet.project01.databinding.ActivityFindBoardUpdateBinding;

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

public class FindBoardUpdate extends AppCompatActivity {

    List<FindBoard> findBoardList;

    private ActivityFindBoardUpdateBinding binding;

    String baseUrl = "http://10.100.102.44:8899";

    private Uri uri0, uri1, uri2;

    private ArrayList<MultipartBody.Part> imgFileList = new ArrayList<>(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindBoardUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //게시글 번호 intent로 받기
        Intent intent = getIntent();
        Long findId1 = intent.getLongExtra("findId", 0);

        //게시글 뿌려주기
        BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
        Call<FindBoard> call = boardInterface.view(findId1);
        call.enqueue(new Callback<FindBoard>() {
            @Override
            public void onResponse(Call<FindBoard> call, Response<FindBoard> response) {
                FindBoard findBoard = response.body();
                binding.edFindAddr.setText(findBoard.getFindaddr());
                binding.edBreed.setText(findBoard.getBreed());
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
                binding.edPetCharacter.setText(findBoard.getPetcharacter());
                binding.edPetAge.setText(findBoard.getPetage());
                binding.edPetName.setText(findBoard.getPetname());
                binding.edContent.setText(findBoard.getContent());



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
            public void onFailure(Call<FindBoard> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "상세보기 불러오기 실패", Toast.LENGTH_SHORT).show();
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

            ArrayList<Uri> filePathList = new ArrayList<>(3);
            ArrayList<Integer> imgIndexList = new ArrayList<>(3);

            filePathList.add(uri0);
            filePathList.add(uri1);
            filePathList.add(uri2);

            if(uri0!=null){
                imgFileList.add(createImgFile(uri0));
                imgIndexList.add(0);
            }

            if(uri1!=null){
                imgFileList.add(createImgFile(uri1));
                imgIndexList.add(1);
            }

            if(uri2!=null){
                imgFileList.add(createImgFile(uri2));
                imgIndexList.add(2);
            }

            RequestBody findId = RequestBody.create(MediaType.parse("text/plain"),findId1.toString());
            RequestBody petname = RequestBody.create(MediaType.parse("text/plain"),binding.edPetName.getText().toString());
            RequestBody breed = RequestBody.create(MediaType.parse("text/plain"),binding.edBreed.getText().toString());
            RequestBody petage = RequestBody.create(MediaType.parse("text/plain"),binding.edPetAge.getText().toString());
            RequestBody content = RequestBody.create(MediaType.parse("text/plain"),binding.edContent.getText().toString());
            RequestBody petcharacter = RequestBody.create(MediaType.parse("text/plain"),binding.edPetCharacter.getText().toString());
            RequestBody findaddr = RequestBody.create(MediaType.parse("text/plain"),binding.edFindAddr.getText().toString());
            RequestBody petcategory = RequestBody.create(MediaType.parse("text/plain"),binding.petcategory.getSelectedItem().toString());
            RequestBody petgender;

            if(binding.petgenderHe.isChecked()){
                petgender = RequestBody.create(MediaType.parse("text/plain"),"남아");
            } else {
                petgender = RequestBody.create(MediaType.parse("text/plain"),"여아");
            }

            Map<String, RequestBody> map = new HashMap<>();
            map.put("findId", findId);
            map.put("petname", petname);
            map.put("breed", breed);
            map.put("petage", petage);
            map.put("content", content);
            map.put("petcharacter", petcharacter);
            map.put("findaddr", findaddr);
            map.put("petcategory", petcategory);
            map.put("petgender", petgender);





            BoardInterface boardInterFace = BoardClient.getInstance().getBoardInterface();
            Call<FindBoard> call1 = boardInterFace.updateFindBoard(imgFileList,map,imgIndexList);
            call1.enqueue(new Callback<FindBoard>() {
                @Override
                public void onResponse(Call<FindBoard> call, Response<FindBoard> response) {
                    Log.d("FindBoard 등록 Response", "등록성공");
                    Intent intent1 = new Intent(FindBoardUpdate.this, FindBoardDetail.class);
                    intent1.putExtra("findId", findId1);
                    startActivity(intent1.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT));
                }

                @Override
                public void onFailure(Call<FindBoard> call, Throwable t) {
                    Log.d("FindBoard 등록 failure", t.toString());
                }
            });
        });
        /*binding.btnOK.setOnClickListener(new View.OnClickListener() {
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
                uri0 = uri;
            }
        } else if (requestCode == 2) {
            if(data==null){
                return;
            }
            Uri uri = data.getData();
            if(uri != null){
                binding.img2.setImageURI(uri);
                uri1 = uri;
            }
        } else if (requestCode == 3) {
            if(data==null){
                return;
            }
            Uri uri = data.getData();
            if(uri != null){
                binding.img3.setImageURI(uri);
                uri2 = uri;
            }
        }
    }

    public MultipartBody.Part createImgFile(Uri uri){
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        cursor.moveToNext();
        String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
        cursor.close();

        File imgFile = new File(path);
        RequestBody fileRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), imgFile);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("imgFile", imgFile.getName(), fileRequestBody);

        return filePart;
    }
}