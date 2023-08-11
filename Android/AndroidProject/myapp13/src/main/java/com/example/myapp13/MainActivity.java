package com.example.myapp13;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
        private PhoneAdapter phoneAdapter;
        private  RecyclerView recyclerView;
        private ArrayList<Phone> phoneList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnList = findViewById(R.id.btnList);
        recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton floatingActionButton = findViewById(R.id.floatBtn);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false);
        phoneList = new ArrayList<>();
        phoneAdapter =new PhoneAdapter(phoneList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(phoneAdapter);

        PhoneService phoneService = PhoneClient.getInstance().getPhoneservice();
        Call<List<Phone>> call = phoneService.findAll();
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


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.layout_add,null);
                EditText etName = dialogView.findViewById(R.id.etName);
                EditText etTel = dialogView.findViewById(R.id.etTel);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("연락처 등록");
                dlg.setView(dialogView);
                dlg.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Phone p = new Phone(etName.getText().toString(),
                                etTel.getText().toString() );
                        PhoneService phoneService = PhoneClient.getInstance().getPhoneservice();
                        Call<Phone> call = phoneService.save(p);
                        call.enqueue(new Callback<Phone>() {
                            @Override
                            public void onResponse(Call<Phone> call, Response<Phone> response) {
                                phoneAdapter.addItem(p);
                                phoneAdapter.notifyDataSetChanged();
                            }


                            @Override
                            public void onFailure(Call<Phone> call, Throwable t) {

                            }
                        });
                    }
                });
                dlg.setNegativeButton("닫기", null);
                dlg.show();
            }
        });
    }
}