package com.example.team1;


import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.FindViewHolder> {

    private List<FindBoard> findList;

    public FindAdapter(List<FindBoard> findList) {
        this.findList = findList;
    }

    public void removeItem(int position){
        findList.remove(position);
        notifyDataSetChanged();
    }

    public void removeAllItem(List<FindBoard> findBoard){
        findList.removeAll(findBoard);
        notifyDataSetChanged();
    }

    public void addItem(FindBoard findBoard){
        findList.add(findBoard);
        notifyDataSetChanged();
    }


    public void updateItem(FindBoard findBoard){
        FindBoard f = new FindBoard();
        f.setBreed(findBoard.getBreed());
        f.setContent(findBoard.getContent());
        f.setFindaddr(findBoard.getFindaddr());
        f.setPetage(findBoard.getPetage());
        f.setPetgender(findBoard.getPetgender());
        f.setPetname(findBoard.getPetname());
        f.setPetcharacter(findBoard.getPetcharacter());
        f.setPetcategory(findBoard.getPetcategory());

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public FindAdapter.FindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_add_find_and_lost,parent,false);
        FindViewHolder findViewHolder = new FindViewHolder(view);
        return findViewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull FindAdapter.FindViewHolder holder, int position) {
        FindBoard findBoard = findList.get(position);
        holder.petcharacter.setText(findBoard.getPetcharacter());
        holder.petgender.setText(findBoard.getPetgender());
        holder.petImage.setImageResource(R.drawable.dog);
        holder.findaddr.setText(findBoard.getFindaddr());
        holder.breed.setText(findBoard.getBreed());
        holder.petCategory.setText(findBoard.getPetcategory());
    }

    @Override
    public int getItemCount() {
        Log.d("findList  size :" ,findList.size()+"" );
        return findList == null ? 0 : findList.size();
    }

    class FindViewHolder extends RecyclerView.ViewHolder {

        ImageView petImage;
        TextView breed, petgender,findaddr,petcharacter,petCategory;
        public FindViewHolder(@NonNull View itemView) {
            super(itemView);

            petImage = itemView.findViewById(R.id.petImage);
            breed = itemView.findViewById(R.id.Breed);
            petgender = itemView.findViewById(R.id.petGender);
            findaddr = itemView.findViewById(R.id.findAddr);
            petcharacter = itemView.findViewById(R.id.petCharacter);
            petCategory = itemView.findViewById(R.id.petCategory);


        }

    }


}
