package com.example.myapp07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2_output extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_output);
        Button btnBack = findViewById(R.id.Back);


        TextView tvDisplay = findViewById(R.id.tvDisplay);
        TextView tvDisplay2 = findViewById(R.id.tvDisplay2);

        Intent intent = getIntent();

        String name = intent.getStringExtra("Name");
        String Num = intent.getStringExtra("Num");
        String Major = intent.getStringExtra("Major");
       Object object = (Object) intent.getSerializableExtra("object");

        tvDisplay.setText("학생정보 : " + name + "and" + Num + "and" + Major);
        tvDisplay2.setText("객체 정보 - 이름 : " + object.getName() + "나이 : " +object.getAge() +"전화번호 : " + object.getTel());
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}