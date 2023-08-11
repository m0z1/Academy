package com.example.myapp14;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

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

        Button btnList = findViewById(R.id.btnList);
        Button btnInsert = findViewById(R.id.btnInsert);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        EditText edtName = findViewById(R.id.etName);
        EditText edtPhone = findViewById(R.id.etTel);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.phone_list,null);

        recyclerView = findViewById(R.id.recyclerView);
        // 뷰랑 연결

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        phoneList = new ArrayList<>();
        phoneAdapter = new PhoneAdapter(phoneList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(phoneAdapter);


        // 어댑터 연결

        phoneAdapter.setOnItemClickListener(new PhoneAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int pos) {

                Toast.makeText(getApplicationContext(),"Test :  " + pos,Toast.LENGTH_SHORT).show();

                Phone phone = phoneList.get(pos);
                TextView name = findViewById(R.id.name);
                TextView Phone = findViewById(R.id.phone);

                name.setText(phone.getName());
                Phone.setText(phone.getPhone());

                edtName.setText(name.getText().toString());
                edtPhone.setText(Phone.getText().toString());

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PhoneService phoneService = PhoneClient.getInstance().getPhoneService();
                        Call<Void> call = phoneService.delete(phone.getId());
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                phoneAdapter.removeItem(pos);
                                phoneAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });
                    }
                });

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Phone phone1 = new Phone(edtName.getText().toString(),edtPhone.getText().toString());
                        PhoneService phoneService = PhoneClient.getInstance().getPhoneService();
                        Call<Phone> call = phoneService.update(phone.getId(),phone1);
                        call.enqueue(new Callback<com.example.myapp14.Phone>() {
                            @Override
                            public void onResponse(Call<com.example.myapp14.Phone> call, Response<com.example.myapp14.Phone> response) {
                                phoneAdapter.updateItem(response.body(),pos);
                                phoneAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<com.example.myapp14.Phone> call, Throwable t) {

                            }
                        });
                    }
                });

            }
        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneService phoneService = PhoneClient.getInstance().getPhoneService();
                Call<List<Phone>> call = phoneService.find();
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
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Phone phone = new Phone(edtName.getText().toString(),edtPhone.getText().toString());
                PhoneService phoneService = PhoneClient.getInstance().getPhoneService();
                Call<Phone> call = phoneService.insert(phone);
                call.enqueue(new Callback<Phone>() {
                    @Override
                    public void onResponse(Call<Phone> call, Response<Phone> response) {
                        phoneAdapter.addItem(phone);

                    }

                    @Override
                    public void onFailure(Call<Phone> call, Throwable t) {

                    }
                });
            }
        });


    }
}