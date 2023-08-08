package com.example.myapp10;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDB = findViewById(R.id.btnDB);
        Button btnTable = findViewById(R.id.btnTable);
        Button btnInsert = findViewById(R.id.btnInsert);
        Button btnSelect = findViewById(R.id.btnSelect);
        EditText edDb = findViewById(R.id.edDB);
        EditText edTable = findViewById(R.id.edTable);
         textView = findViewById(R.id.textView);

        btnDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
                sqLiteDatabase = openOrCreateDatabase(edDb.getText().toString(),MODE_PRIVATE,null);
                output("데이터 베이스 생성  :: " + edDb.getText().toString());
            }
        });

        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
                String tableName = edTable.getText().toString();
                if(sqLiteDatabase == null){
                    output("데이터베이스를 생성하세요");
                    return;
                }
                String sql = "create table if not exists " + tableName +"("+
                        " id integer primary key autoincrement, " +
                        " name text , " +
                        " age integer," +
                        " phone text)";

                sqLiteDatabase.execSQL(sql);
                output("테이블 생성 : " + edTable.getText().toString());
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
                String tableName = edTable.getText().toString();
                if(sqLiteDatabase == null){
                    output("데이터 베이스를 생성하세요");
                    return;
                }
                if (tableName == null){
                    output("테이블을 생성하세요");
                }
                output("btnSelect 호출");
                String sql = "Select * from " + tableName;
                Cursor cursor =  sqLiteDatabase.rawQuery(sql,null);
                while (cursor.moveToNext()){
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    int age = cursor.getInt(2);
                    String phone = cursor.getString(3);
                    output(id + " // " + name + " // " + age + " // " + phone);

                }
            }
        });
    }

    private void output(String str){
        textView.setText(str);
    }
}