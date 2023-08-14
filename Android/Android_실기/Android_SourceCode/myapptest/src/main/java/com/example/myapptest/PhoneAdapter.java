package com.example.myapptest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder> {

    private final ArrayList<Phone> phoneList;

    public PhoneAdapter(ArrayList<Phone> phoneList) {
        this.phoneList = phoneList;
    }


    //추가
    public  void addItem(Phone phone){
        phoneList.add(phone);
        notifyDataSetChanged();
    }
    //삭제

    public void deleteItem(int position){
        phoneList.remove(position);
        notifyDataSetChanged();
    }
    //수정
    public void updateItem(Phone phone, int pos){
        Phone p = phoneList.get(pos);
        p.setName(phone.getName());
        p.setTel(phone.getTel());
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.phone_list,parent,false);
        PhoneViewHolder phoneViewHolder = new PhoneViewHolder(view);

        return phoneViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {
        Phone phone = phoneList.get(position);
        holder.name.setText(phone.getName());
        holder.Tel.setText(phone.getTel());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogview =  view.inflate(view.getContext(),R.layout.item_phone,null);

                EditText etName = dialogview.findViewById(R.id.edtName);
                EditText etTel = dialogview.findViewById(R.id.edtTel);

                etName.setText(phone.getName());
                etTel.setText(phone.getTel());

                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("연락처 수정");
                dlg.setView(dialogview);
                //수정
                dlg.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Phone p = new Phone(etName.getText().toString(),etTel.getText().toString());
                        PhoneService phoneService = PhoneClient.getInstance().getPhoneService();

                        Call<Phone> call = phoneService.update(phone.getId(),p);

                        call.enqueue(new Callback<Phone>() {
                            @Override
                            public void onResponse(Call<Phone> call, Response<Phone> response) {
                                updateItem(response.body(),holder.getAdapterPosition());
                            }

                            @Override
                            public void onFailure(Call<Phone> call, Throwable t) {

                            }
                        });
                    }
                });

                // 삭제
                dlg.setNegativeButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PhoneService phoneService = PhoneClient.getInstance().getPhoneService();
                        Call<Void> call = phoneService.del(phone.getId());
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                deleteItem(holder.getAdapterPosition());
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });
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



    class PhoneViewHolder extends RecyclerView.ViewHolder {

        EditText edtName, edtTel;
        TextView name, Tel;

        public PhoneViewHolder(@NonNull View itemView) {
            super(itemView);
            edtName = itemView.findViewById(R.id.edtName);
            edtTel = itemView.findViewById(R.id.edtTel);
            name = itemView.findViewById(R.id.name);
            Tel = itemView.findViewById(R.id.phone);


        }
    }
}
