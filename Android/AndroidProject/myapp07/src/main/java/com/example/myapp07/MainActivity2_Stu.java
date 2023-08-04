package com.example.myapp07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2_Stu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_stu);

        Button button = findViewById(R.id.Complete);
        EditText edtSno = findViewById(R.id.editStdID);
        EditText edtName = findViewById(R.id.editName);
        EditText edtMajor = findViewById(R.id.editMajor);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2_Stu.this, MainActivity2.class);

                intent.putExtra("stdNum",edtSno.getText().toString());
                intent.putExtra("stdName" ,edtName.getText().toString());
                intent.putExtra("stdMajor",edtMajor.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
            }
        });
    }
}