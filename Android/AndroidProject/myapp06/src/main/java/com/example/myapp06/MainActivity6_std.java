package com.example.myapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity6_std extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity6_std);

        EditText editstdID = findViewById(R.id.editStdID);
        EditText editName = findViewById(R.id.editName);
        EditText editMajor = findViewById(R.id.editMajor);

        Button button = findViewById(R.id.Complete);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student();
                student.setSno(Integer.parseInt(editstdID.getText().toString()));
                student.setName(editName.getText().toString());
                student.setMajor(editMajor.getText().toString());

                Intent intent = new Intent();

                intent.putExtra("student",student);
                setResult(10,intent);

                finish();
            }
        });
    }
}