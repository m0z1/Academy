package com.example.myapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity3_sub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3_sub);

        Intent intent = getIntent();

        int[] voteResult = intent.getIntArrayExtra("voteCout");
        String [] imageName = intent.getStringArrayExtra("ImageName");


        TextView tv[] = new TextView[imageName.length];
        RatingBar rbar[] = new RatingBar[imageName.length];

        int maxPos = 0 ;
        Integer tvID[] = {
                R.id.tv1, R.id.tv2,  R.id.tv3,  R.id.tv4, R.id.tv5,  R.id.tv6,  R.id.tv7,
                R.id.tv8, R.id.tv9,
        };

        Integer rbarID[] = {
                R.id.rbar1,R.id.rbar2,R.id.rbar3,R.id.rbar4,R.id.rbar5, R.id.rbar6 , R.id.rbar7,R.id.rbar8,R.id.rbar9
        };

        for(int i = 0; i < voteResult.length; i++){
            tv[i] = (TextView) findViewById(tvID[i]);
            rbar[i] =(RatingBar) findViewById(rbarID[i]);
        }

        for(int i = 0; i < voteResult.length;i++){
            tv[i].setText(imageName[i]);
            rbar[i].setRating((float) voteResult[i]);
        }


            Button btnReturn = findViewById(R.id.btnBack);
            btnReturn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

    }
}