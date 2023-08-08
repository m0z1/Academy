package com.example.myapp09;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{
        Button btnSong, btnArtist, btnAlbum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnSong = findViewById(R.id.btnSong);
        btnArtist = findViewById(R.id.btnArtist);
        btnAlbum = findViewById(R.id.btnAlbum);

        btnSong.setOnClickListener(this);
        btnArtist.setOnClickListener(this);
        btnAlbum.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Fragment fr = null;

        if(view.getId()==R.id.btnSong){
            fr = new SongFragment();
        }else if(view.getId()==R.id.btnAlbum){
            fr = new AlbumFragment();
        }else if(view.getId()==R.id.btnArtist){
            fr = new ArtistFragment();
        }
       /* FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fr);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();*/

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,fr)
                .addToBackStack(null)
                .commit();
    }
}