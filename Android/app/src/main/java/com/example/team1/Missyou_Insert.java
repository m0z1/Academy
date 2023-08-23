package com.example.team1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.team1.databinding.ActivityMissyouInsertBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Missyou_Insert extends AppCompatActivity {
    private ActivityMissyouInsertBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMissyouInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnSaveMissng.setOnClickListener(view -> {
            MissyouBoard missyouBoard = new MissyouBoard();

            missyouBoard.setPetname(binding.petnameMissing.getText().toString());
            missyouBoard.setBreed(binding.breedMissing.getText().toString());
            missyouBoard.setBreed(binding.petageMissing.getText().toString());
            missyouBoard.setContent(binding.contentMissing.getText().toString());
            missyouBoard.setPetage(binding.petageMissing.getText().toString());
            missyouBoard.setPetcharacter(binding.petcharacterMissing.getText().toString());
            missyouBoard.setMissingaddr(binding.missingaddrMissing.getText().toString());
            missyouBoard.setPetcategory(binding.petcategoryMissing.getSelectedItem().toString());

            if(binding.petgenderHe.isChecked()){
                missyouBoard.setPetgender("남아");
            }
            else {
                missyouBoard.setPetgender("여아");
            }

            AppService appService = AppClient.getInstance().getAppService();
            Call<MissyouBoard> call = appService.missyou_insert(missyouBoard);
            call.enqueue(new Callback<MissyouBoard>() {
                @Override
                public void onResponse(Call<MissyouBoard> call, Response<MissyouBoard> response) {
                    Log.d("MissYou Board 등록", response.toString());
                    finish();
                }

                @Override
                public void onFailure(Call<MissyouBoard> call, Throwable t) {
                    Log.d("Missyou 등록", "fail");
                }
            });

            Intent intent = new Intent(getApplicationContext(),Missyou.class);
            startActivity(intent);
        });
    }
}