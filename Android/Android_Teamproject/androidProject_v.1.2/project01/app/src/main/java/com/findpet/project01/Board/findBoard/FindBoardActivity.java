package com.findpet.project01.Board.findBoard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.findpet.project01.Board.BoardClient;
import com.findpet.project01.Board.BoardInterface;
import com.findpet.project01.Main;
import com.findpet.project01.R;
import com.findpet.project01.databinding.ActivityFindBoardBinding;

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

public class FindBoardActivity extends AppCompatActivity {

    private ActivityFindBoardBinding binding;

    private FindAdapter findAdapter;

    private ArrayList<FindBoard> findList;

    private ArrayList<Uri> filePathList = new ArrayList<>(3);

    private ArrayList<MultipartBody.Part> imgFileList = new ArrayList<>(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences("autoLogin", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        findList = new ArrayList<>();
        findAdapter = new FindAdapter(findList);

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

        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindBoardActivity.this, Main.class);
                startActivity(intent);
            }
        });

        binding.btnSave.setOnClickListener(view -> {

            for (int i = 0; i < filePathList.size(); ++i) {
                if(filePathList.get(i)!=null){

                    File imgFile = new File(getRealPathFromURI(filePathList.get(i)));
                    RequestBody fileRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), imgFile);
                    MultipartBody.Part filePart = MultipartBody.Part.createFormData("imgFile", imgFile.getName(), fileRequestBody);

                    imgFileList.add(filePart);
                }
            }

            RequestBody petname = RequestBody.create(MediaType.parse("text/plain"),binding.petname.getText().toString());
            RequestBody breed = RequestBody.create(MediaType.parse("text/plain"),binding.breed.getText().toString());
            RequestBody petage = RequestBody.create(MediaType.parse("text/plain"),binding.petage.getText().toString());
            RequestBody content = RequestBody.create(MediaType.parse("text/plain"),binding.content.getText().toString());
            RequestBody petcharacter = RequestBody.create(MediaType.parse("text/plain"),binding.petcharacter.getText().toString());
            RequestBody findaddr = RequestBody.create(MediaType.parse("text/plain"),binding.findaddr.getText().toString());
            RequestBody petcategory = RequestBody.create(MediaType.parse("text/plain"),binding.petcategory.getSelectedItem().toString());
            RequestBody petgender;

            if(binding.petgenderHe.isChecked()){
                petgender = RequestBody.create(MediaType.parse("text/plain"),"남아");
            } else {
                petgender = RequestBody.create(MediaType.parse("text/plain"),"여아");
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





            BoardInterface boardInterFace = BoardClient.getInstance().getBoardInterface();
            Call<String> call = boardInterFace.saveFindBoard(imgFileList,map,username);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.d("FindBoard 등록 Response", "등록성공");
                    Intent intent = new Intent(FindBoardActivity.this, FindBoardList.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("FindBoard 등록 failure", t.toString());
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

    private String getRealPathFromURI(Uri contentURI) {

        String result;

        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);

        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();

        }

        return result;

    }

}