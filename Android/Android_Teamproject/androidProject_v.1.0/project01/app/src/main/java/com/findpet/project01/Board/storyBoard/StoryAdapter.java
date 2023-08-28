package com.findpet.project01.Board.storyBoard;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.findpet.project01.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {
    private List<StoryBoard> storyList;

    public StoryAdapter(List<StoryBoard> storyList) {
        this.storyList = storyList;
    }

    //추가
    public void addItem(StoryBoard storyBoard){
        storyList.add(storyBoard);
        notifyDataSetChanged();
    }

    //삭제
    public void deleteItem(Long storyId){
        storyList.remove(storyId);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        return new StoryViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        StoryBoard storyBoard = storyList.get(position);

        holder.txTitle.setText(storyBoard.getTitle());
        Long storyId = storyBoard.getStoryId();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), StoryBoardDetail.class);
                intent.putExtra("storyId", storyId);
                view.getContext().startActivity(intent);
            }
        });

        //날짜 포맷 변경
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(storyBoard.getRegdate());

        holder.txregdate.setText(date);
    }

    @Override
    public int getItemCount() {
        return storyList == null ? 0 : storyList.size();
    }

    class StoryViewHolder extends RecyclerView.ViewHolder {
        ImageView ivimage;
        TextView txTitle, txregdate;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ivimage = itemView.findViewById(R.id.ivimage);
            txTitle = itemView.findViewById(R.id.txTitle);
            txregdate = itemView.findViewById(R.id.txregdate);
        }
    }

}
