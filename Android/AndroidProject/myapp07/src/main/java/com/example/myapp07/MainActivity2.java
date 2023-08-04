package com.example.myapp07;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    String stdName , stdNum, stdMajor;
    Object object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btnInput = findViewById(R.id.input);
        Button btnOuput = findViewById(R.id.output);
        Button btnObInput = findViewById(R.id.input2);



        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {

            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == 10){
                    Intent intent = result.getData();
                    object = (Object) intent.getSerializableExtra("object");
                    return;
                }

                if(result.getResultCode() == Activity.RESULT_OK){
                    Intent intent = result.getData();
                    stdName = intent.getStringExtra("stdName");
                     stdNum = intent.getStringExtra("stdNum");
                     stdMajor = intent.getStringExtra("stdMajor");

                     return;

                }
            }
        });


        btnObInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2_obj.class);
                launcher.launch(intent);
            }
        });
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2_Stu.class);

                launcher.launch(intent);

            }
        });

        btnOuput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2_output.class);
                intent.putExtra("Name",stdName);
                intent.putExtra("Major",stdMajor);
                intent.putExtra("Num",stdNum);
                intent.putExtra("object",object);
                launcher.launch(intent);
            }
        });
    }
}