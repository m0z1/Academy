package com.findpet.project01.Board.shelter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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

public class ShelterAdapter extends RecyclerView.Adapter<ShelterAdapter.ViewHolder> {

    private List<Shelter> shelterList;

    public ShelterAdapter(List<Shelter> shelterList) {
        this.shelterList = shelterList;
    }

    //추가
    public void addItem(Shelter data){
        shelterList.add(data);
        //Log.d("data getNumber :  ", data.getNumber());
        notifyDataSetChanged();
    }

//    public void setItems(List<Shelter> shelterList){
//        shelterList = shelterList;
//        notifyDataSetChanged();
//    }


    @NonNull
    @Override
    public ShelterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.findpet.project01.R.layout.shelter_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShelterAdapter.ViewHolder holder, int position) {
        Shelter shelter = shelterList.get(position);
        holder.txnumber.setText(shelter.getNumber());
        holder.txregdate.setText(shelter.getRegdate());
        holder.txbreed.setText(shelter.getBreed());
        holder.txgender.setText(shelter.getGender());
        holder.txfindAddr.setText(shelter.getFindAddr());
        holder.txcharacter.setText(shelter.getCharacter());
        holder.txstatus.setText(shelter.getStatus());
        holder.txperiod.setText(shelter.getPeriod());
        holder.txetc.setText(shelter.getEtc());
        holder.txregnumber.setText(shelter.getRegnumber());
        Glide.with(holder.itemView.getContext()).load("https://www.animal.go.kr/"+shelter.getImageUrl()).into(holder.ivimage);

    }

    @Override
    public int getItemCount() {
        return shelterList == null ? 0 : shelterList.size();
    }

    public void listFilter(List<Shelter> filteredList){
        shelterList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txnumber, txregdate, txbreed, txgender, txfindAddr, txcharacter, txstatus, txperiod, txetc, txregnumber;
        ImageView ivimage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txnumber = itemView.findViewById(R.id.txnumber);
            txregdate = itemView.findViewById(R.id.txregdate);
            txbreed = itemView.findViewById(R.id.txbreed);
            txgender = itemView.findViewById(R.id.txgender);
            txfindAddr = itemView.findViewById(R.id.txfindAddr);
            txcharacter = itemView.findViewById(R.id.txcharacter);
            txstatus = itemView.findViewById(R.id.txstatus);
            txperiod = itemView.findViewById(R.id.txperiod);
            txetc = itemView.findViewById(R.id.txetc);
            txregnumber = itemView.findViewById(R.id.txregnumber);
            ivimage = itemView.findViewById(R.id.ivimage);
        }

    }

}
