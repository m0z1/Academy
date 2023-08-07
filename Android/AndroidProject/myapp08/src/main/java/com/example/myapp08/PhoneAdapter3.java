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

public class PhoneAdapter3
        extends RecyclerView.Adapter<PhoneAdapter3.PhoneViewHoloder> {
    private ArrayList<Phone3> phoneList;
    //추가
    public PhoneAdapter3(ArrayList<Phone3> phoneList) {
        this.phoneList = phoneList;
    }
    //삭제
    public void removeItem(int position){
        phoneList.remove(position);
        notifyDataSetChanged();
    }
    public void addItem(Phone3 phone3){
        phoneList.add(phone3);
    }

    public void updateItem(Phone3 phone3, int position){
        Phone3 p = phoneList.get(position);
        p.setName(phone3.getName());
        p.setTel(phone3.getTel());
    }


    @NonNull
    @Override
    public PhoneAdapter3.PhoneViewHoloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_phone3,parent,false);
        PhoneViewHoloder phoneViewHoloder = new PhoneViewHoloder(view);
        return phoneViewHoloder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneAdapter3.PhoneViewHoloder holder,
                                 int position) {
        Phone3 phone3 =phoneList.get(position);
        holder.txtName.setText(phone3.getName());
        holder.txtTel.setText(phone3.getTel());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = view.inflate(view.getContext(),
                        R.layout.layout_add3,null);

                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("수정");
                dlg.setView(dialogView);
                dlg.setPositiveButton("수정", null);
                dlg.setNegativeButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("<<<삭제", "삭제");
                        removeItem(holder.getAdapterPosition());
                    }
                });
                dlg.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return phoneList == null ? 0 : phoneList.size();
    }

    public class PhoneViewHoloder extends  RecyclerView.ViewHolder {
        TextView txtName, txtTel;
        public PhoneViewHoloder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtTel= itemView.findViewById(R.id.txtTel);

        }
    }
}

