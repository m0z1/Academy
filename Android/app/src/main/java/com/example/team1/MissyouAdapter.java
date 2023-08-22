package com.example.team1;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MissyouAdapter extends RecyclerView.Adapter<MissyouAdapter.MissyouViewHolder> {

    private ArrayList<Find>  missList;

    public MissyouAdapter(ArrayList<Find> missList) {
        this.missList = missList;
    }

    @NonNull
    @Override
    public MissyouAdapter.MissyouViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MissyouAdapter.MissyouViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MissyouViewHolder extends RecyclerView.ViewHolder {

        public MissyouViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
