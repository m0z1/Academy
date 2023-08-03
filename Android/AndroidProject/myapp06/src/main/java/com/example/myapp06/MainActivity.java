package com.example.myapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RadioButton rdoBtn1 = findViewById(R.id.rdoBtn1);
        RadioButton rdoBtn2 = findViewById(R.id.rdoBtn2);
        RadioButton rdoBtn3 = findViewById(R.id.rdoBtn3);


        Button btn1 = (Button) findViewById(R.id.Btn1);

   /*     btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(intent);
            }
        });*/

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rdoBtn1.isChecked()){
                    Intent intent1 = new Intent(getApplicationContext(),SecondActivity.class);
                    startActivity(intent1);
                }
                else if(rdoBtn2.isChecked()){
                    Intent intent2 = new Intent(getApplicationContext(), ThirdActivity.class);
                    startActivity(intent2);
                }else if(rdoBtn3.isChecked()){
                    Intent intent3 = new Intent(getApplicationContext(), FourthActivity.class);
                    startActivity((intent3));
                }
            }
        });
    }
}