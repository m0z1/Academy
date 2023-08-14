package com.example.myapp14;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder>  {
    private final ArrayList<Phone> phoneList;



    public interface OnItemClickListener{
        void OnItemClick(int pos);
    }



    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public PhoneAdapter(ArrayList<Phone> phoneList) {
        this.phoneList = phoneList;

    }
    public void addItem(Phone phone){
        phoneList.add(phone);
        notifyDataSetChanged();
    }
    //안드로이드 추가
    public void removeItem(int position){
        phoneList.remove(position);
        notifyDataSetChanged();
    }
    // 안드로이드 삭제
    public void updateItem(Phone phone, int position){
        Phone p = phoneList.get(position);
        p.setName(phone.getName());
        p.setPhone(phone.getPhone());
    }
    // 안드로이드 업데이트


    @NonNull
    @Override
    public PhoneAdapter.PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.phone_list,parent,false);

      PhoneViewHolder phoneViewHolder = new PhoneViewHolder(view);
      return phoneViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull PhoneAdapter.PhoneViewHolder holder, int position) {
        Phone phone = phoneList.get(position);

        holder.name.setText(phone.getName());
        holder.phone.setText(phone.getPhone());




    }
    @Override
    public int getItemCount() {
        return phoneList == null ? 0 : phoneList.size();
    }


    public  class PhoneViewHolder extends RecyclerView.ViewHolder{
        EditText edtName , edtPhone;
        TextView name , phone;


        public PhoneViewHolder(@NonNull View itemView) {
            super(itemView);
            edtName = itemView.findViewById(R.id.etName);
            edtPhone = itemView.findViewById(R.id.etTel);
            name  = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);

         itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 onItemClickListener.OnItemClick(getAdapterPosition());
             }
         });

        }
    }
}
