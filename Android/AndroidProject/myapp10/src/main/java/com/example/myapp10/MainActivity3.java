package com.example.myapp10;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class MainActivity3 extends AppCompatActivity {
        SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button btnInsert = findViewById(R.id.btnInsert);
        EditText edit = findViewById(R.id.Edit);
        DatePicker date = findViewById(R.id.Date);
        
    }

    public class MyDBHelper extends SQLiteOpenHelper {

        public MyDBHelper(@Nullable Context context) {
            super(context,"Diary",null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table Diary(diaryDate char(10) primary key, content varchar(500));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("drop table if exists Diary");
            onCreate(sqLiteDatabase);
        }
    }
}