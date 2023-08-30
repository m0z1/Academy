package com.findpet.project01.Board.storyBoard;

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
import com.findpet.project01.databinding.ActivityStoryBoardUpdateBinding;


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

public class StoryBoardUpdate extends AppCompatActivity {

    List<StoryBoard> storyBoardList;

    private ActivityStoryBoardUpdateBinding binding;

    private Uri uri0, uri1, uri2;

    private ArrayList<MultipartBody.Part> imgFileList = new ArrayList<>(3);

    String baseUrl = "http://10.100.102.44:8899";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoryBoardUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //게시글 번호 Intent로 받기
        Intent intent = getIntent();
        Long storyId1 = intent.getLongExtra("storyId", 0);


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


        //게시글 뿌려주기
        BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
        Call<StoryBoard> call = boardInterface.view3(storyId1);
        call.enqueue(new Callback<StoryBoard>() {
            @Override
            public void onResponse(Call<StoryBoard> call, Response<StoryBoard> response) {
                StoryBoard storyBoard = response.body();
                binding.edTitle.setText(storyBoard.getTitle());
                binding.edContent.setText(storyBoard.getContent());
                //이미지 가져오기
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
            public void onFailure(Call<StoryBoard> call, Throwable t) {

            }
        });

        //수정버튼
        //수정버튼
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

            RequestBody storyId = RequestBody.create(MediaType.parse("text/plain"),storyId1.toString());
            RequestBody content = RequestBody.create(MediaType.parse("text/plain"),binding.edContent.getText().toString());
            RequestBody title = RequestBody.create(MediaType.parse("text/plain"),binding.edTitle.getText().toString());



            Map<String, RequestBody> map = new HashMap<>();
            map.put("storyId", storyId);
            map.put("title", title);
            map.put("content", content);



            BoardInterface boardInterFace = BoardClient.getInstance().getBoardInterface();
            Call<StoryBoard> call1 = boardInterFace.updateStoryBoard(imgFileList, map, imgIndexList);
            call1.enqueue(new Callback<StoryBoard>() {
                @Override
                public void onResponse(Call<StoryBoard> call, Response<StoryBoard> response) {
                    Log.d("FindBoard 등록 Response", "등록성공");
                    Intent intent1 = new Intent(StoryBoardUpdate.this, StoryBoardDetail.class);
                    intent1.putExtra("storyId", storyId1);
                    startActivity(intent1.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT));
                }

                @Override
                public void onFailure(Call<StoryBoard> call, Throwable t) {
                    Log.d("FindBoard 등록 failure", t.toString());
                }
            });
        });

        /*binding.btnOK.setOnClickListener(new View.OnClickListener() {
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
        });*/
        
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