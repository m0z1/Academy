package com.example.myapp06;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        EditText edtNum1 = findViewById(R.id.edit1);
        EditText edtNum2 = findViewById(R.id.edit2);


        Button button = findViewById(R.id.cal);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity4.this, MainActivity4_sub.class);

                intent.putExtra("Num1",Integer.parseInt(edtNum1.getText().toString()));
                intent.putExtra("Num2",Integer.parseInt(edtNum2.getText().toString()));

                startActivityForResult(intent,0);


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
         int sum1   = data.getIntExtra("합" ,0);
            Toast.makeText(getApplicationContext(),"합 : " +sum1,Toast.LENGTH_SHORT ).show();
        }
    }
}