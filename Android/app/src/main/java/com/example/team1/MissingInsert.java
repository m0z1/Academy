package com.example.team1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Date;
import java.text.DateFormat;
import java.util.Calendar;

public class MissingInsert extends AppCompatActivity {
    TextView textDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing_insert);
        Button btnInsert = findViewById(R.id.btnInsert);
        EditText breed = findViewById(R.id.edtBreed);
        EditText content = findViewById(R.id.edtContent);
        DatePicker date = findViewById(R.id.edtRegdate);
        EditText petname = findViewById(R.id.edtPetname);
        EditText petage = findViewById(R.id.edtPetAge);
        EditText petcharacter = findViewById(R.id.edtPetCharacter);
        EditText petCategory = findViewById(R.id.edtPetcategory);
        EditText findAddr = findViewById(R.id.edtFindaddr);
        EditText petgender = findViewById(R.id.edtPetgender);
        textDate = findViewById(R.id.Date);

        Calendar cal = Calendar.getInstance();

        textDate.setText(cal.get(Calendar.YEAR) +"-"+ (cal.get(Calendar.MONTH)+1) +"-"+ cal.get(Calendar.DATE));


        DatePickerDialog.OnDateSetListener mdate= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                TextView txtDate= findViewById(R.id.Date);
                txtDate.setText(String.format("%d-%d-%d", yy,mm+1,dd));

            }
        };

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Missing.class);
                intent.putExtra("breed",breed.getText().toString());
                intent.putExtra("content",content.getText().toString());
                intent.putExtra("date",textDate.getText().toString());
                intent.putExtra("petname",petname.getText().toString());
                intent.putExtra("petage",petage.getText().toString());
                intent.putExtra("petcharacter",petcharacter.getText().toString());
                intent.putExtra("petcategory",petCategory.getText().toString());
                intent.putExtra("findAddr",findAddr.getText().toString());
                intent.putExtra("petgender",petgender.getText().toString());

                startActivity(intent);
            }
        });

    }
}