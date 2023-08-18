package com.example.team1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Missing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing);

        ImageView missingImg = findViewById(R.id.missing);
        Button writeMissing = findViewById(R.id.writeMissing);






        missingImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        writeMissing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          /*      Intent intent = new Intent(getApplicationContext(), MissingBoard.class);
                startActivity(intent);*/
            }
        });





    }
}