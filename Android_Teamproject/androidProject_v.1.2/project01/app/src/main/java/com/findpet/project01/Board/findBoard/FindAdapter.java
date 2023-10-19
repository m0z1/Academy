package com.findpet.project01.Board.findBoard;

import android.content.Context;
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

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.MissingViewHolder> {

    String baseUrl = "http://10.100.102.44:8899";

    private ArrayList<FindBoard> findBoardList;

    public FindAdapter(ArrayList<FindBoard> findBoardList) {
        this.findBoardList = findBoardList;
    }

    public interface  OnItemClickListener{
        void OnItemClick(int pos);
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    //추가
    public void addItem(FindBoard findBoard){
        findBoardList.add(findBoard);
        notifyDataSetChanged();
    }

    //삭제
    public void deleteItem(Long findId){
        findBoardList.remove(findId);
        notifyDataSetChanged();
    }

    public void removeAllItem(List<FindBoard> findBoard){
        findBoardList.removeAll(findBoard);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MissingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        return new MissingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MissingViewHolder holder, int position) {
        FindBoard findBoard = findBoardList.get(position);
        holder.petcharacter.setText(findBoard.getPetcharacter());
        holder.petgender.setText(findBoard.getPetgender());
        if(findBoard.getImgFileList().size() != 0) {
            Glide.with(holder.itemView.getContext())
                    .load(baseUrl+findBoard.getImgFileList().get(0).getImgUrl())
                    .override(500,400)
                    .into(holder.petImage);
            Log.d("url", findBoard.getImgFileList().get(0).getImgUrl());
        } else if(findBoard.getImgFileList().size() == 0) {

        }
        holder.findaddr.setText(findBoard.getFindaddr());
        holder.breed.setText(findBoard.getBreed());
        holder.petCategory.setText(findBoard.getPetcategory());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), FindBoardDetail.class);
                intent.putExtra("findId", findBoard.getFindId());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return findBoardList == null? 0 : findBoardList.size();
    }

    class MissingViewHolder extends RecyclerView.ViewHolder {
        ImageView petImage;
        TextView breed, petgender, findaddr, petcharacter, petCategory;
        public MissingViewHolder(@NonNull View itemView) {
            super(itemView);
            petImage = itemView.findViewById(R.id.petImage);
            breed = itemView.findViewById(R.id.Breed);
            petgender = itemView.findViewById(R.id.petGender);
            findaddr = itemView.findViewById(R.id.findAddr);
            petcharacter = itemView.findViewById(R.id.petCharacter);
            petCategory = itemView.findViewById(R.id.petCategory);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.OnItemClick(getAdapterPosition());
                    //Log.i("번호", getAdapterPosition()+"");
                }
            });
        }
        }
    }

