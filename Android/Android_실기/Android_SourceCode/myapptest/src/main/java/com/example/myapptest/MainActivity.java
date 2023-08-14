package com.example.myapptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private PhoneAdapter phoneAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Phone> phoneList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button list = findViewById(R.id.btnList);
        Button add = findViewById(R.id.btnAdd);
        EditText edtName = findViewById(R.id.etName);
        EditText edtTel = findViewById(R.id.etTel);
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        phoneList = new ArrayList<>();
        phoneAdapter = new PhoneAdapter(phoneList);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(phoneAdapter);

        // 전체보기
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneService phoneService = PhoneClient.getInstance().getPhoneService();
                Call<List<Phone>> call = phoneService.list();
                call.enqueue(new Callback<List<Phone>>() {
                    @Override
                    public void onResponse(Call<List<Phone>> call, Response<List<Phone>> response) {
                        for(Phone p : response.body()){
                            phoneAdapter.addItem(p);
                        }
                        phoneAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<Phone>> call, Throwable t) {

                    }
                });

            }
        });
        //추가
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Phone p = new Phone(edtName.getText().toString(),edtTel.getText().toString());
                PhoneService phoneService = PhoneClient.getInstance().getPhoneService();
                Call <Phone> call = phoneService.insert(p);
                call.enqueue(new Callback<Phone>() {
                    @Override
                    public void onResponse(Call<Phone> call, Response<Phone> response) {
                        phoneAdapter.addItem(p);
                    }

                    @Override
                    public void onFailure(Call<Phone> call, Throwable t) {

                    }
                });
            }
        });


    }
}