package com.example.team1;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MissingAdapter extends RecyclerView.Adapter<MissingAdapter.MissingViewHolder> {

    private ArrayList<FindBoard> findList;

    public MissingAdapter(ArrayList<FindBoard> findList) {
        this.findList = findList;
    }

    public void removeItem(int position){
        findList.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(FindBoard findBoard){
        findList.add(findBoard);
        notifyDataSetChanged();
    }

    public void updateItem(FindBoard findBoard, int position){
        FindBoard f = findList.get(position);
        f.setBreed(findBoard.getBreed());
        f.setContent(findBoard.getContent());
        f.setFindaddr(findBoard.getFindaddr());
        f.setPetage(findBoard.getPetage());
        f.setPetgender(findBoard.getPetgender());
        f.setPetname(findBoard.getPetname());
        f.setPetcharacter(findBoard.getPetcharacter());

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MissingAdapter.MissingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_add_find_and_lost,parent,false);
        MissingViewHolder missingViewHolder = new MissingViewHolder(view);
        return missingViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MissingAdapter.MissingViewHolder holder, int position) {
        FindBoard findBoard = findList.get(position);
        holder.petcharacter.setText(findBoard.getPetcharacter());
        holder.petgender.setText(findBoard.getPetgender());
        holder.petImage.setImageResource(R.drawable.dog);
        holder.findaddr.setText(findBoard.getFindaddr());
        holder.breed.setText(findBoard.getBreed());


    }

    @Override
    public int getItemCount() {
        return findList == null ? 0 : findList.size();
    }

    class MissingViewHolder extends RecyclerView.ViewHolder {
        ImageView petImage;
        TextView breed, petgender,findaddr,petcharacter;
        public MissingViewHolder(@NonNull View itemView) {
            super(itemView);
            petImage = itemView.findViewById(R.id.petImage);
            breed = itemView.findViewById(R.id.breed);
            petgender = itemView.findViewById(R.id.petgender);
            findaddr = itemView.findViewById(R.id.findaddr);
            petcharacter = itemView.findViewById(R.id.petcharacter);
        }

    }
}
