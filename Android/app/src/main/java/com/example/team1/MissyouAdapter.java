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

public class MissyouAdapter extends RecyclerView.Adapter<MissyouAdapter.MissyouViewHolder> {

    private ArrayList<MissyouBoard>  missList;

    public MissyouAdapter(ArrayList<MissyouBoard> missList) {
        this.missList = missList;
    }

    public void removeAllItem(List<MissyouBoard> missyouBoard){
        missList.removeAll(missyouBoard);
        notifyDataSetChanged();
    }

    public void addItem(MissyouBoard missyouBoard){
        missList.add(missyouBoard);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MissyouAdapter.MissyouViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_add_find_and_lost,parent,false);
        MissyouViewHolder missyouViewHolder = new MissyouViewHolder(view);
        return missyouViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MissyouAdapter.MissyouViewHolder holder, int position) {
        MissyouBoard missyouBoard = missList.get(position);
        holder.petcharacter.setText(missyouBoard.getPetcharacter());
        holder.petgender.setText(missyouBoard.getPetgender());
        holder.petImage.setImageResource(R.drawable.dog);
        holder.missaddr.setText(missyouBoard.getMissingaddr());
        holder.breed.setText(missyouBoard.getBreed());
        holder.petCategory.setText(missyouBoard.getPetcategory());



    }

    @Override
    public int getItemCount() {
        return missList == null ? 0: missList.size();
    }


    class MissyouViewHolder extends RecyclerView.ViewHolder {
        ImageView petImage;
        TextView breed, petgender, missaddr,petcharacter, petCategory;

        public MissyouViewHolder(@NonNull View itemView) {
            super(itemView);
            petImage = itemView.findViewById(R.id.petImage);
            breed = itemView.findViewById(R.id.Breed);
            petgender = itemView.findViewById(R.id.petGender);
            missaddr = itemView.findViewById(R.id.findAddr);
            petcharacter = itemView.findViewById(R.id.petCharacter);
            petCategory = itemView.findViewById(R.id.petCategory);

        }
    }
}
