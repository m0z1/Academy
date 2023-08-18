package com.example.team1;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {
    private ArrayList<StoryBoard> storyList;

    public StoryAdapter(ArrayList<StoryBoard> storyList) {
        this.storyList = storyList;
    }

    @NonNull
    @Override
    public StoryAdapter.StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull StoryAdapter.StoryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class StoryViewHolder extends RecyclerView.ViewHolder {

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
