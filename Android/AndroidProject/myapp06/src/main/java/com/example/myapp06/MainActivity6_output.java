package com.example.myapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity6_output extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity6_output);
        TextView tvDisplay = findViewById(R.id.tvDisplay);
        TextView tvDisplay2 = findViewById(R.id.tvDisplay2);
        Button back = findViewById(R.id.Back);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        String tel = intent.getStringExtra("tel");

        tvDisplay.setText("데이터 : " +name+ " // " + age + "//" + tel );
        Student student = (Student) intent.getSerializableExtra("student");
        if(student != null) {
            tvDisplay2.setText("학생객체 : " + student.getSno() + " : " + student.getName() + " : " + student.getMajor());
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }
}