package com.example.myapp08;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PhoneAdapter4 extends RecyclerView.Adapter<PhoneAdapter4.PhoneViewHolder> {
    private ArrayList<Phone4> phoneList = new ArrayList<>();

    public interface OnItemClickListener{
        void OnItemClick(int pos);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public PhoneAdapter4(ArrayList<Phone4> phoneList) {
        this.phoneList = phoneList;
    }


    public void addItem(Phone4 phone4){
        phoneList.add(phone4);
        notifyDataSetChanged();
    }

    public Phone4 getItem(int position){
        return phoneList.get(position);
    }

    @NonNull
    @Override
    public PhoneAdapter4.PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phone4,parent,false);
        PhoneViewHolder phoneViewHoloder = new PhoneViewHolder(view);

        return phoneViewHoloder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {
        Phone4 phone4 = phoneList.get(position);
        holder.txtName.setText(phone4.getName());
        holder.txtTel.setText(phone4.getTel());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = view.inflate(view.getContext(),
                        R.layout.layout_add4,null);


            }
        });
    }


    @Override
    public int getItemCount() {
        return phoneList == null ? 0 : phoneList.size();
    }

    public class PhoneViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtTel;
        public PhoneViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTel = itemView.findViewById(R.id.txtName);
            txtName = itemView.findViewById(R.id.txtTel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    onItemClickListener.OnItemClick(position);
                }
            });
        }
    }
}
