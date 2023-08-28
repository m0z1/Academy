package com.example.team1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{

    private ArrayList<MainBoard> mainList;

    public MainAdapter(ArrayList<MainBoard> mainList) {
        this.mainList = mainList;
    }

    public void removeAllItem(List<MainBoard> mainBoards){
        mainList.removeAll(mainBoards);
        notifyDataSetChanged();
    }

    public void addItem(MainBoard mainBoard){
        mainList.add(mainBoard);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_add_find_and_lost,parent,false);
        MainViewHolder mainViewHolder = new MainViewHolder(view);

        return mainViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MainViewHolder holder, int position) {
        MainBoard mainBoard = mainList.get(position);
        holder.petcharacter.setText(mainBoard.getPetcharacter());
        holder.petgender.setText(mainBoard.getPetgender());
        holder.petImage.setImageResource(R.drawable.dog);
        holder.findaddr.setText(mainBoard.getFindaddr());
        holder.breed.setText(mainBoard.getBreed());
        holder.petCategory.setText(mainBoard.getPetcategory());
    }

    @Override
    public int getItemCount() {
        return mainList == null ? 0 : mainList.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder{
        ImageView petImage;
        TextView breed, petgender,findaddr,petcharacter,petCategory,petName;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            petImage = itemView.findViewById(R.id.petImage);
            breed = itemView.findViewById(R.id.Breed);
            petgender = itemView.findViewById(R.id.petGender);
            findaddr = itemView.findViewById(R.id.findAddr);
            petcharacter = itemView.findViewById(R.id.petCharacter);
            petCategory = itemView.findViewById(R.id.petCategory);
            petName = itemView.findViewById(R.id.petname);


        }
    }
}
