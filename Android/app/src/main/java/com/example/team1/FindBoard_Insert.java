package com.example.team1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.example.team1.databinding.ActivityFindBoardInsertBinding;

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

public class FindBoard_Insert extends AppCompatActivity {

    private ActivityFindBoardInsertBinding binding;

    private ArrayList<Uri> filePathList = new ArrayList<>(3);

    private ArrayList<MultipartBody.Part> imgFileList = new ArrayList<>(3);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindBoardInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        filePathList.add(null);
        filePathList.add(null);
        filePathList.add(null);

        binding.img1.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 1);
        });

        binding.img2.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 2);
        });

        binding.img3.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 3);
        });


        binding.btnSave.setOnClickListener(view -> {

            for (int i = 0; i < filePathList.size(); ++i) {
                if (filePathList.get(i) != null) {
                    Cursor cursor = getContentResolver().query(filePathList.get(i), null, null, null, null);
                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                    cursor.close();
                    Log.d("addList", path);
                    Log.d("addList", path);

                    File imgFile = new File(path);
                    RequestBody fileRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), imgFile);
                    MultipartBody.Part filePart = MultipartBody.Part.createFormData("imgFile", imgFile.getName(), fileRequestBody);
                    imgFileList.add(filePart);


                }
            }

            RequestBody petname = RequestBody.create(MediaType.parse("text/plain"), binding.petname.getText().toString());
            RequestBody breed = RequestBody.create(MediaType.parse("text/plain"), binding.breed.getText().toString());
            RequestBody petage = RequestBody.create(MediaType.parse("text/plain"), binding.petage.getText().toString());
            RequestBody content = RequestBody.create(MediaType.parse("text/plain"), binding.content.getText().toString());
            RequestBody petcharacter = RequestBody.create(MediaType.parse("text/plain"), binding.petcharacter.getText().toString());
            RequestBody findaddr = RequestBody.create(MediaType.parse("text/plain"), binding.findaddr.getText().toString());
            RequestBody petcategory = RequestBody.create(MediaType.parse("text/plain"), binding.petcategory.getSelectedItem().toString());
            RequestBody petgender;
            if (binding.petgenderHe.isChecked()) {
                petgender = RequestBody.create(MediaType.parse("text/plain"), "남아");
            } else {
                petgender = RequestBody.create(MediaType.parse("text/plain"), "여아");
            }

            Map<String, RequestBody> map = new HashMap<>();

            map.put("petname", petname);
            map.put("breed", breed);
            map.put("petage", petage);
            map.put("content", content);
            map.put("petcharacter", petcharacter);
            map.put("findaddr", findaddr);
            map.put("petcategory", petcategory);
            map.put("petgender", petgender);
           /* FindBoard findBoard = new FindBoard();

            findBoard.setPetname(binding.petname.getText().toString());
            findBoard.setBreed(binding.breed.getText().toString());
            findBoard.setPetage(binding.petage.getText().toString());
            findBoard.setContent(binding.content.getText().toString());
            findBoard.setPetcharacter(binding.petcharacter.getText().toString());
            findBoard.setFindaddr(binding.findaddr.getText().toString());
            findBoard.setPetcategory(binding.petcategory.getSelectedItem().toString());*/


            AppService boardInterFace = AppClient.getInstance().getAppService();
            if (petname != breed) {
                Call<String> call = boardInterFace.saveFindBoard(imgFileList, map);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("FindBoard 등록 Response", "등록성공");
                        Intent intent = new Intent(getApplicationContext(), Find.class);
                        startActivity(intent);
                        finish();
                        // 조건이 충족되지 않았을 때의 동작 계속 진행
                        // ...
                    }


                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("FindBoard 등록 failure", t.toString());
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "같은 값 오류", Toast.LENGTH_SHORT).show();
            }
        });
    }

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

