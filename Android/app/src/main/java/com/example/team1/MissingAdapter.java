package com.example.team1;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MissingAdapter extends RecyclerView.Adapter<MissingAdapter.MissingViewHolder> {

    private ArrayList<FindBoard> findList;

    public MissingAdapter(ArrayList<FindBoard> findList) {
        this.findList = findList;
    }

    @NonNull
    @Override
    public MissingAdapter.MissingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MissingAdapter.MissingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MissingViewHolder extends RecyclerView.ViewHolder {

        public MissingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
