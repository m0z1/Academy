package com.example.myapp12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity3 extends AppCompatActivity {

    private PhotoInterface photoInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button btnPhoto = findViewById(R.id.btnPhoto);
        Button btnPost = findViewById(R.id.btnPost);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        ArrayList<Photo> photoList = new ArrayList<>();
        recyclerView.setLayoutManager(linearLayoutManager);
        PhotoAdapter photoAdapter = new PhotoAdapter(photoList);

        recyclerView.setAdapter(photoAdapter);

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoInterface = PhotoClient.getClient().create(PhotoInterface.class);
               Call<List<Photo>> call =  photoInterface.doGetPhotos();
               call.enqueue(new Callback<List<Photo>>() {
                   @Override
                   public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                       List<Photo> resource = response.body();
                       Log.d("test111" , resource.size() +"");

                       for(Photo photo : resource){
                           photoList.add(photo);
                       }
                       Log.d("photoList size" , photoList.size() +"");
                       photoAdapter.notifyDataSetChanged();

                   }

                   @Override
                   public void onFailure(Call<List<Photo>> call, Throwable t) {

                   }
               });
            }
        });
    }
}