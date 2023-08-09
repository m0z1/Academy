package com.example.myapp11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TextView;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity2 extends AppCompatActivity {
    private  int num_page = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ViewPager2 viewPager2 = findViewById(R.id.viewpager);
        CircleIndicator circleIndicator =findViewById(R.id.indicator);
        TextView textView = findViewById(R.id.text);
        FragmentStateAdapter fragmentStateAdapter = new MyFragAdapter(this,num_page);
        viewPager2.setAdapter(fragmentStateAdapter);

    }



}