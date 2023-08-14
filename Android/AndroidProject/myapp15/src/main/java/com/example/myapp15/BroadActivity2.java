package com.example.myapp15;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BroadActivity2 extends AppCompatActivity {
    ImageView ivBattery;
    TextView tvBattery;
    BroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad2);
        setTitle("배터리 상태 체크");
        ivBattery = findViewById(R.id.ivBattery);
         tvBattery = findViewById(R.id.tvBattery);

        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(action.equals(Intent.ACTION_BATTERY_CHANGED));
               int remain =  intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
               if(remain > 90) {
                   ivBattery.setImageResource(R.drawable.battery_100);

               } else if (remain >= 70) {
                   ivBattery.setImageResource(R.drawable.battery_80);

               } else if (remain >= 50) {
                   ivBattery.setImageResource(R.drawable.battery_60);

               } else if ((remain >=20)) {
                   ivBattery.setImageResource(R.drawable.battery_20);

               }else{
                   ivBattery.setImageResource(R.drawable.battery_0);
                   Toast.makeText(getApplicationContext(),"btn1",Toast.LENGTH_SHORT).show();
               }



            }
        };
    int plug = getIntent().getIntExtra(BatteryManager.EXTRA_PLUGGED,0);
    switch (plug){
        case 0:
            tvBattery.append("전원 연결 : 안됨");
            break;
        case BatteryManager.BATTERY_PLUGGED_AC:
            tvBattery.append("전원 연결 : 어댑터 연결됨");
            break;
        case BatteryManager.BATTERY_PLUGGED_USB:
            tvBattery.append("전원 연결 : USB 연결됨");
            break;
    }

    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPostResume() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(br,intentFilter);
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}