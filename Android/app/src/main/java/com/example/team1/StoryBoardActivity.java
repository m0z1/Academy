package com.example.team1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.example.team1.databinding.ActivityStoryBoardBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoryBoardActivity extends AppCompatActivity {

    private ActivityStoryBoardBinding binding;

    private ArrayList<Uri> filePathList = new ArrayList<>(3);

    private ArrayList<MultipartBody.Part> imgFileList = new ArrayList<>(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoryBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        filePathList.add(null);
        filePathList.add(null);
        filePathList.add(null);

        binding.img1.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent,1);

        });

        binding.img2.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent,2);
        });

        binding.img3.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent,3);
        });



        binding.btnSave.setOnClickListener(view -> {

            for (int i = 0; i < filePathList.size(); ++i) {
                if(filePathList.get(i)!=null){
                    Cursor cursor = getContentResolver().query(filePathList.get(i),null,null,null,null);
                    cursor.moveToNext();
                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                    cursor.close();

                    Log.d("addList", path);

                    File imgFile = new File(path);
                    RequestBody fileRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), imgFile);
                    MultipartBody.Part filePart = MultipartBody.Part.createFormData("imgFile", imgFile.getName(), fileRequestBody);

                    imgFileList.add(filePart);
                }
            }

            RequestBody title = RequestBody.create(MediaType.parse("text/plain"),binding.title.getText().toString());
            RequestBody content = RequestBody.create(MediaType.parse("text/plain"),binding.content.getText().toString());

            Map<String, RequestBody> map = new HashMap<>();
            map.put("title", title);
            map.put("content", content);


            AppService boardInterFace = AppClient.getInstance().getAppService();
            Call<String> call = boardInterFace.saveStoryBoard(imgFileList,map);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.d("StoryBoard 등록 Response", "등록성공");
                    finish();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("StoryBoard 등록 failure", t.toString());
                }
            });
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