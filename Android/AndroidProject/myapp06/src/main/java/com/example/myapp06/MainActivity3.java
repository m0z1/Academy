package com.example.myapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button btnResult = findViewById(R.id.buttonResult);


        int countVote[] = new int[9];
        ImageView image[] = new ImageView[9];

        String imageName[] = {"pic1","pic2","pic3","pic4","pic5","pic6","pic7","pic8","pic9"};

        Integer imageid[] = {R.id.iv1,R.id.iv2,R.id.iv3,R.id.iv4,R.id.iv5,R.id.iv6,R.id.iv7,R.id.iv8,R.id.iv9};

        for(int i = 0; i<imageid.length;i++){
            final int index = i;
            image[index] = findViewById(imageid[index]);
            image[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    countVote[index]++;
                    Toast.makeText(getApplicationContext(),imageid[index] + ": 총" + countVote[index] + " 표",Toast.LENGTH_SHORT).show();

                }
            });
        }



        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this,MainActivity3_sub.class);
                intent.putExtra("countvote",countVote);
                intent.putExtra("imageName",imageName);
                startActivity(intent);
            }
        });









}
}

