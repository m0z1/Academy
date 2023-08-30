package com.findpet.project01.Board.shelter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    private ArrayList<Shelter> shelterList;

    public ShelterAdapter(ArrayList<Shelter> shelterList) {
        this.shelterList = shelterList;
    }

    //추가
    public void addItem(Shelter data){
        shelterList.add(data);
        //Log.d("data getNumber :  ", data.getNumber());
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ShelterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shelter_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShelterAdapter.ViewHolder holder, int position) {
        Shelter shelter = shelterList.get(position);
        holder.txnumber.setText(shelter.getNoticeNo());
        holder.txregdate.setText(shelter.getHappenDt());
        holder.txbreed.setText(shelter.getKindCd());
        holder.txgender.setText(shelter.getSexCd());
        holder.txfindAddr.setText(shelter.getHappenPlace());
        holder.txcharacter.setText(shelter.getSpecialMark());
        holder.txstatus.setText(shelter.getProcessState());
        holder.noticeSdt.setText(shelter.getNoticeSdt());
        holder.noticeEdt.setText(shelter.getNoticeEdt());

        Glide.with(holder.itemView.getContext()).load(shelter.getImage()).into(holder.ivimage);
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShelterBoardDetail.class);
                intent.putExtra("shelter",shelter);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return shelterList == null ? 0 : shelterList.size();
    }

    public void listFilter(ArrayList<Shelter> filteredList){
        shelterList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txnumber, txregdate, txbreed, txgender, txfindAddr, txcharacter, txstatus, noticeSdt, noticeEdt, txetc, txregnumber;
        ImageView ivimage;
        Button btnDetail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txnumber = itemView.findViewById(R.id.txnumber);
            txregdate = itemView.findViewById(R.id.txregdate);
            txbreed = itemView.findViewById(R.id.txbreed);
            txgender = itemView.findViewById(R.id.txgender);
            txfindAddr = itemView.findViewById(R.id.txfindAddr);
            txcharacter = itemView.findViewById(R.id.txcharacter);
            txstatus = itemView.findViewById(R.id.txstatus);
            noticeSdt = itemView.findViewById(R.id.noticeSdt);
            noticeEdt = itemView.findViewById(R.id.noticeEdt);
            ivimage = itemView.findViewById(R.id.ivimage);
            btnDetail = itemView.findViewById(R.id.btnDetail);
        }

    }

}
