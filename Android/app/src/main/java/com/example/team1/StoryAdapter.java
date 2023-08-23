package com.example.team1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {
    private ArrayList<StoryBoard> storyList;

    public StoryAdapter(ArrayList<StoryBoard> storyList) {
        this.storyList = storyList;
    }
    public void removeAllItem(List<StoryBoard> storyList){
        storyList.removeAll(storyList);
        notifyDataSetChanged();
    }

    public void addItem(StoryBoard storyBoard){
        storyList.add(storyBoard);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public StoryAdapter.StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_add_story,parent,false);
        StoryViewHolder storyViewHolder = new StoryViewHolder(view);
        return storyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StoryAdapter.StoryViewHolder holder, int position) {
            StoryBoard storyBoard = storyList.get(position);
            holder.userId.setText("test");
            holder.storyTitle.setText(storyBoard.getTitle());

    }

    @Override
    public int getItemCount() {
        return storyList == null ? 0 : storyList.size();
    }

    class StoryViewHolder extends RecyclerView.ViewHolder {
        TextView userId, storyTitle;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            userId = itemView.findViewById(R.id.userID);
            storyTitle = itemView.findViewById(R.id.storyTitle);
        }
    }

}
