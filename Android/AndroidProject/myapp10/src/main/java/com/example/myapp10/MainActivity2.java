package com.example.myapp10;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btnInit = findViewById(R.id.btnInit);
        Button btnInsert = findViewById(R.id.btnInsert);
        Button btnSelect = findViewById(R.id.btnSelect);
        Button btnTest = findViewById(R.id.btnInsertAll);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);

        EditText editName = findViewById(R.id.edtName);
        EditText editNumber = findViewById(R.id.edtNumber);
        TextView editNameResult = findViewById(R.id.edtNameResult);
        TextView editNumberResult =findViewById(R.id.edtNumberResult);

        MyDBHelper myDBHelper = new MyDBHelper(this);

        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = myDBHelper.getWritableDatabase();
                myDBHelper.onUpgrade(sqLiteDatabase,1,2);
                sqLiteDatabase.close();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = myDBHelper.getWritableDatabase();
                String sql = "insert into groupTBL values(?,?)";
                SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
                statement.bindString(1,editName.getText().toString());
                statement.bindString(2,editNumber.getText().toString());
                statement.execute();

                sqLiteDatabase.close();
                Toast.makeText(getApplicationContext(),"입력",Toast.LENGTH_SHORT).show();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = myDBHelper.getReadableDatabase();
                String sql = "select * from groupTBL";
                Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
                String strName ="그룹 이름 : \n";
                String strNumber ="그룹 번호 : \n";
                while (cursor.moveToNext()){
                    strName += cursor.getString(0) + "\n";
                    strNumber += cursor.getString(1) + "\n";

                }
                editNameResult.setText(strName);
                editNumberResult.setText(strNumber);
                cursor.close();
                sqLiteDatabase.close();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editName.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"이름입력",Toast.LENGTH_SHORT).show();
                }
                sqLiteDatabase = myDBHelper.getReadableDatabase();
                String sql = "update groupTBL set gNumber= " +editNumber.getText().toString()  +  "where gName = " + editName.getText().toString();

                sqLiteDatabase.execSQL(sql);

                sqLiteDatabase.close();
            }
        });

   }

   public class MyDBHelper extends SQLiteOpenHelper {

       public MyDBHelper(@Nullable Context context) {
           super(context, "groupDB",null,1);
       }



       @Override
       public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table groupTBL(gName char(20) primary key,gNumber integer);");
       }

       @Override
       public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists groupTBL");
        onCreate(sqLiteDatabase);
       }

   }
}