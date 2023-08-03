package com.example.myapp06;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity5_sub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity5_sub);

        Button btnBack = findViewById(R.id.btnResult);

        Intent intent = getIntent();

        int num1 = intent.getIntExtra("Num1",0);
        int num2 = intent.getIntExtra("Num2",0);

        int sel = intent.getIntExtra("sel",0);



        btnBack.setOnClickListener(new View.OnClickListener() {
            int result = 0;
            @Override
            public void onClick(View view) {
                if(sel == R.id.btnAdd){
                    result = num1 + num2;
                }else if (sel == R.id.btnSub){
                    result = num1 - num2;
                }else if (sel == R.id.btnMul){
                    result = num1 * num2;
                }else if (sel == R.id.btnDiv){
                    result = num1 / num2;
                }

                Intent intent = new Intent(getApplicationContext(), MainActivity5.class);
                intent.putExtra("Result",result);
                setResult(RESULT_OK,intent);
                finish();
            }



        });


    }


}