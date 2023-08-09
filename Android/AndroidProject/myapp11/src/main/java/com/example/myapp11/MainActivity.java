package com.example.myapp11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager2 = findViewById(R.id.viewpager2);
        Button button  = findViewById(R.id.btnToggle);

        ArrayList<DataPage> list = new ArrayList<>();
        list.add(new DataPage(Color.CYAN, "1 page"));
        list.add(new DataPage(Color.BLUE, "2 page"));
        list.add(new DataPage(Color.RED, "3 page"));
        list.add(new DataPage(Color.GREEN, "4 page"));
        list.add(new DataPage(Color.YELLOW, "5 page"));
        list.add(new DataPage(Color.BLACK, "6 page"));
        viewPager2.setAdapter(new ViewPagerAdapter(list));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewPager2.getOrientation() == ViewPager2.ORIENTATION_VERTICAL) {
                    button.setText("가로로슬라이드");
                    viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
                }else {
                    button.setText("세로로 슬라이드");
                    viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
                }
            }
        });
    }
}