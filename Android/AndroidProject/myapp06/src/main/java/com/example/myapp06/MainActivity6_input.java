package com.example.myapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity6_input extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity6_input);

        Button com = findViewById(R.id.Complete);
        EditText editName = findViewById(R.id.editName);
        EditText editAge = findViewById(R.id.editAge);
        EditText editTel = findViewById(R.id.editTel);

        com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity6_input.this, MainActivity6.class);

                intent.putExtra("name",editName.getText().toString());
                intent.putExtra("age",editAge.getText().toString());
                intent.putExtra("tel",editTel.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }
}