package com.example.myapp05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity3 extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TabHost tabHost = getTabHost();

        TabSpec tabSpecCat = tabHost.newTabSpec("Cat").setIndicator("고양이");
        tabSpecCat.setContent(R.id.imgCat);
        tabHost.addTab(tabSpecCat);

        TabSpec tabSpecDog = tabHost.newTabSpec("Dog").setIndicator("개");
        tabSpecDog.setContent(R.id.imgDog);
        tabHost.addTab(tabSpecDog);

        TabSpec tabSpecRabbit = tabHost.newTabSpec("Rabbit").setIndicator("토끼");
        tabSpecRabbit.setContent(R.id.imgRabbit);
        tabHost.addTab(tabSpecRabbit);

        TabSpec tabSpecHorse = tabHost.newTabSpec("Horse").setIndicator("말");
        tabSpecHorse.setContent(R.id.imgHorse);
        tabHost.addTab(tabSpecHorse);

    }
}