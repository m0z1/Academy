package com.example.myapp06;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity6 extends AppCompatActivity {
    String name, age, tel;
    Student student;
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
               startActivityForResult(intent, 0);
            }
        });

        btnDataOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),MainActivity6_output.class);
                intent1.putExtra("name",name);
                intent1.putExtra("age",age);
                intent1.putExtra("tel",tel);

                intent1.putExtra("student",student);

                startActivity(intent1);

            }
        });

            btnDataStu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity6_std.class);
                    startActivityForResult(intent,0);
                }
            });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 10 ){
            student = (Student) data.getSerializableExtra("student");
            return;
        }
        if(resultCode == RESULT_OK){
            name =data.getStringExtra("name");
            age = data.getStringExtra("age");
            tel = data.getStringExtra("tel");
            return;
        }
    }
}