package com.example.myapp07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2_obj extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_obj);
        EditText editName = findViewById(R.id.editName);
        EditText editAge = findViewById(R.id.editAge);
        EditText editTel = findViewById(R.id.editTel);

        Button btn = findViewById(R.id.Complete);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object object = new Object();
                object.setName(editName.getText().toString());
                object.setAge(editAge.getText().toString());
                object.setTel(editTel.getText().toString());

                Intent intent = new Intent();

                intent.putExtra("object",object);
                setResult(10,intent);
                finish();
            }
        });
    }
}