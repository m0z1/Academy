package com.example.myapp08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {
    private  PhoneAdapter4 phoneAdapter4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        EditText etName = findViewById(R.id.etName);
        EditText etTel = findViewById(R.id.etTel);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Button btnInsert = findViewById(R.id.btnInsert);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnList = findViewById(R.id.btnList);

        //전체보기

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Phone4> phoneList = new ArrayList<>();
                phoneAdapter4 = new PhoneAdapter4(phoneList);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity4.this,RecyclerView.VERTICAL,false);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(phoneAdapter4);

                for(int i=0 ; i<5; i++){
                    phoneAdapter4.addItem(new Phone4("홍길동"+i,
                            "010-1111-2222"));
                }
            }
        });


        // 추가

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Phone4 phone = new Phone4(etName.getText().toString(),etTel.getText().toString());
                phoneAdapter4.addItem(phone);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}