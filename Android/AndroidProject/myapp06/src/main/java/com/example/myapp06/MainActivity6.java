package com.example.myapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        Button btnDataInput = findViewById(R.id.btnDatainput);
        Button btnDataOutput = findViewById(R.id.btnDataoutput);
        Button btnDataStu = findViewById(R.id.btnDataStu);


        btnDataInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity6_input.class);
                startActivity(intent);
            }
        });

        btnDataOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),MainActivity6_output.class);
                startActivity(intent1);
            }
        });


    }
}