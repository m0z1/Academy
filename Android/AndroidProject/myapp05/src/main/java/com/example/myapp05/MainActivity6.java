package com.example.myapp05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity6 extends AppCompatActivity {
    private LinearLayout baseLayout;
    private Button button1 , button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        setTitle("배경색 바꾸기(컨텍스트 메뉴)");

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        baseLayout = findViewById(R.id.baseLayout);
        registerForContextMenu(button1);
        registerForContextMenu(button2);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();

        if(v == button1){
            menu.setHeaderTitle("배경색 변경");
            menuInflater.inflate(R.menu.menu2,menu);
        }

        if(v == button2){
            menu.setHeaderTitle("회전");
            menuInflater.inflate(R.menu.menu2,menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.itemRed) {
            baseLayout.setBackgroundColor(Color.RED);
        }else if(item.getItemId() == R.id.itemBlue){
            baseLayout.setBackgroundColor(Color.BLUE);
        }else if(item.getItemId() == R.id.itemGreen){
            baseLayout.setBackgroundColor(Color.GREEN);
        }else if(item.getItemId() == R.id.subRotate){
            button1.setRotation(45);
        } else if(item.getItemId() == R.id.subSize){
            button2.setScaleX(2);
        }
        return false;
    }
}