package com.findpet.project01.Board.storyBoard;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.findpet.project01.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {
    private List<StoryBoard> storyList;
    private  String baseUrl = "http://10.100.102.44:8899";

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_item, null);
        return new StoryViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        StoryBoard storyBoard = storyList.get(position);
        Log.d("story", storyBoard.getTitle()+"");
        if(storyBoard.getImgFileList().size() != 0) {
            Glide.with(holder.itemView.getContext())
                    .load(baseUrl+storyBoard.getImgFileList().get(0).getImgUrl())
                    .override(500,400)
                    .into(holder.storyimage);
            Log.d("url", storyBoard.getImgFileList().get(0).getImgUrl());
        } else if(storyBoard.getImgFileList().size() == 0) {

        }
        holder.storyTitle.setText(storyBoard.getTitle());
        holder.storyContent.setText(storyBoard.getContent());
        Long storyId = storyBoard.getStoryId();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), StoryBoardDetail.class);
                intent.putExtra("storyId", storyId);
                view.getContext().startActivity(intent);
            }
        });

        /*//날짜 포맷 변경
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(storyBoard.getRegdate());

        holder.txregdate.setText(date);*/
    }

    @Override
    public int getItemCount() {
        return storyList == null ? 0 : storyList.size();
    }

    class StoryViewHolder extends RecyclerView.ViewHolder {
        ImageView storyimage;
        TextView storyTitle, txregdate, storyContent;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            storyimage = itemView.findViewById(R.id.storyImage);
            storyTitle = itemView.findViewById(R.id.Storytitle);
            txregdate = itemView.findViewById(R.id.txregdate);
            storyContent = itemView.findViewById(R.id.Storycontent);
        }
    }

}
