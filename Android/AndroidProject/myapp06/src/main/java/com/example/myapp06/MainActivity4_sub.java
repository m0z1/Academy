package com.example.myapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity4_sub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity4_sub);

        Button btnBack = findViewById(R.id.btnBack);


        Intent intent = getIntent();
        int num1 = intent.getIntExtra("Num1",0);
        int num2 = intent.getIntExtra("Num2",0);

        int sum = num1 + num2;

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Toast.makeText(getApplicationContext(),"합계 : " + sum, Toast.LENGTH_SHORT).show();*/
                Intent intent1 = new Intent(getApplicationContext(),MainActivity4.class);
                intent1.putExtra("합" , sum);
                setResult(RESULT_OK,intent1);
                finish();
            }
        });

    }
}