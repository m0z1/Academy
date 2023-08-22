package com.example.team1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.team1.databinding.ActivityFindBoardInsertBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindBoard_Insert extends AppCompatActivity {

    private ActivityFindBoardInsertBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindBoardInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnSave.setOnClickListener(view -> {

            FindBoard findBoard = new FindBoard();

            findBoard.setPetname(binding.petname.getText().toString());
            findBoard.setBreed(binding.breed.getText().toString());
            findBoard.setPetage(binding.petage.getText().toString());
            findBoard.setContent(binding.content.getText().toString());
            findBoard.setPetcharacter(binding.petcharacter.getText().toString());
            findBoard.setFindaddr(binding.findaddr.getText().toString());
            findBoard.setPetcategory(binding.petcategory.getSelectedItem().toString());
            if(binding.petgenderHe.isChecked()){
                findBoard.setPetgender("남아");
            } else {
                findBoard.setPetgender("여아");
            }

            AppService boardInterFace = AppClient.getInstance().getAppService();
            Call<FindBoard> call = boardInterFace.insert(findBoard);
            call.enqueue(new Callback<FindBoard>() {
                @Override
                public void onResponse(Call<FindBoard> call, Response<FindBoard> response) {
                    Log.d("FindBoard 등록", response.toString());
                    finish();
                }

                @Override
                public void onFailure(Call<FindBoard> call, Throwable t) {
                    Log.d("FindBoard 등록", "fail");
                }
            });
            finish();
        });

    }
}