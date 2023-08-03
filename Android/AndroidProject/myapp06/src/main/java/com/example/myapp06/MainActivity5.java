package com.example.myapp06;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);


        EditText editText1 = findViewById(R.id.edit1);
        EditText editText2 = findViewById(R.id.edit2);

        RadioButton rdoAdd = findViewById(R.id.btnAdd);
        RadioButton rdoSub = findViewById(R.id.btnSub);
        RadioButton rdoMul = findViewById(R.id.btnMul);
        RadioButton rdoDiv = findViewById(R.id.btnDiv);

        RadioGroup rg = findViewById(R.id.rg);


        Button button = findViewById(R.id.btnResult);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity5.this, MainActivity5_sub.class );

                intent.putExtra("Num1",Integer.parseInt(editText1.getText().toString()));
                intent.putExtra("Num2",Integer.parseInt(editText2.getText().toString()));
                intent.putExtra("sel",rg.getCheckedRadioButtonId());

                startActivityForResult(intent,0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        TextView txtView = findViewById(R.id.result);
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            int result = data.getIntExtra("Result" ,0);
            txtView.setText("결과는 : " + result);
            Toast.makeText(getApplicationContext(),"결과는 : " + result,Toast.LENGTH_SHORT).show();
        }
    }
}