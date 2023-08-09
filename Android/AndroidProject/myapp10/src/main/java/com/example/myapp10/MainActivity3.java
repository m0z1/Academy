package com.example.myapp10;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity3 extends AppCompatActivity {
        SQLiteDatabase sqLiteDatabase;
    String diaryDate;
    MyDBHelper2 myDBHelper2;
    Button btnInsert;
    EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

         btnInsert = findViewById(R.id.btnInsert);
         edit = findViewById(R.id.Edit);
        DatePicker date = findViewById(R.id.Date);

        myDBHelper2 = new MyDBHelper2(this);
        sqLiteDatabase = myDBHelper2.getWritableDatabase();
        myDBHelper2.onUpgrade(sqLiteDatabase,1,2);

        Calendar calendar = Calendar.getInstance();
        int cYear = calendar.get(Calendar.YEAR);
        int cMonth = calendar.get(Calendar.MONTH);
        int cDay = calendar.get(Calendar.DAY_OF_MONTH);
      diaryDate = cYear + "-" +cMonth + "-" +cDay;
        edit.setText(diaryDate);
        btnInsert.setEnabled(true);
        edit.setText(readDiary(diaryDate));



        date.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day){
                diaryDate = year + "-" +(month+1) +"-" +day;
                edit.setText(readDiary(diaryDate));

            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = myDBHelper2.getWritableDatabase();
                String sql = "insert into Diary values('"+diaryDate+"',"+"'"+edit.getText().toString()+"')";
                if(btnInsert.getText().equals("수정하기")){
                    sql = "update Diary set content = '" + edit.getText().toString()+"'" + " where diaryDate = '" +diaryDate+"'";
                }
                sqLiteDatabase.execSQL(sql);
                sqLiteDatabase.close();
                Toast.makeText(MainActivity3.this,"입력",Toast.LENGTH_SHORT).show();
            }
        });

    }
    private String readDiary(String diaryDate) {
        String strResult = "";
        sqLiteDatabase = myDBHelper2.getReadableDatabase();
        String sql = "select * from Diary where diaryDate = '" + diaryDate+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if(cursor.moveToNext()){
            strResult = cursor.getString(0);
            btnInsert.setText("수정하기");
            Toast.makeText(this,"일기조회",Toast.LENGTH_SHORT).show();
        }else {
            btnInsert.setText("새로저장");
            edit.setText("");
            edit.setHint("일기없음");
        }

        return strResult;
    }

    public class MyDBHelper2 extends SQLiteOpenHelper {

        public MyDBHelper2(@Nullable Context context) {
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