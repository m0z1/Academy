package com.example.myapp05;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity7 extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        button = findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

/*             *//*   AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity7.this);
                dlg.setTitle("제목입니다");
                dlg.setMessage("이곳이 내용");
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity7.this,"확인클릭", Toast.LENGTH_SHORT).show();
                            }
                        });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity7.this,"취소클릭",Toast.LENGTH_LONG).show();
                    }
                });
*//*
                dlg.show();*/

                String [] versionArray = {"파이","큐(10)","알(11)"};
                boolean [] checkArray = new boolean[] {true, false, false};
                AlertDialog.Builder alg = new AlertDialog.Builder(MainActivity7.this);
                alg.setTitle("좋아하는 버전은?");
                alg.setMultiChoiceItems(versionArray, checkArray, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        button.setText(versionArray[i]);
                    }
                });
                    alg.setPositiveButton("닫기",null);
                    alg.show();
            }
        });
    }
}