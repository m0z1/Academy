package com.example.myapp08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter mRecyclerAdapter;
    private ArrayList<FriendItem> mfriendItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        /* initiate adapter */
        mRecyclerAdapter = new MyRecyclerAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        mRecyclerAdapter.setOnItemClickListener(new MyRecyclerAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Toast.makeText(getApplicationContext(),"onItemclick : " + pos, Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerAdapter.setOnLongItemClickListener(new MyRecyclerAdapter.onLongItemClickListener() {
            @Override
            public void onLongItemClick(int pos) {
                mRecyclerAdapter.delete(pos);
            }
        });

        /* adapt data */
        mfriendItems = new ArrayList<>();
        for(int i=1;i<=20;i++){
            if(i%2==0)
                mfriendItems.add(new FriendItem(R.drawable.cat,i+"번째 사람",i+"번째 상태메시지"));
            else
                mfriendItems.add(new FriendItem(R.drawable.cat,i+"번째 사람",i+"번째 상태메시지"));

        }
        mRecyclerAdapter.setFriendList(mfriendItems);
    }
}