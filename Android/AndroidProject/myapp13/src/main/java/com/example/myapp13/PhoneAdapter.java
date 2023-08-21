package com.example.myapp13;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
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

public class PhoneAdapter
        extends RecyclerView.Adapter<PhoneAdapter.PhoneViewHoloder> {
    private final ArrayList<Phone> phoneList;
    //추가
    public PhoneAdapter(ArrayList<Phone> phoneList) {
        this.phoneList = phoneList;
    }
    //삭제
    public void removeItem(int position){
        phoneList.remove(position);
        notifyDataSetChanged();
    }
    public void addItem(Phone phone){
        phoneList.add(phone);
        notifyDataSetChanged();
    }

    public void updateItem(Phone phone, int position){
        Phone p = phoneList.get(position);
        p.setName(phone.getName());
        p.setTel(phone.getTel());
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PhoneAdapter.PhoneViewHoloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.phone_list,parent,false);
        PhoneViewHoloder phoneViewHoloder = new PhoneViewHoloder(view);
        return phoneViewHoloder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneAdapter.PhoneViewHoloder holder,
                                 int position) {
        Phone phone =phoneList.get(position);
        holder.txtName.setText(phone.getName());
        holder.txtTel.setText(phone.getTel());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = view.inflate(view.getContext(),
                        R.layout.layout_add,null);

                EditText etName = dialogView.findViewById(R.id.etName);
                EditText etPhone = dialogView.findViewById(R.id.etTel);

                etName.setText(phone.getName());
                etPhone.setText(phone.getTel());

                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("연락처 수정");
                dlg.setView(dialogView);
                dlg.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("etName" ,phone.getId()+"");
                        Phone phone1 = new Phone(etName.getText().toString(),etPhone.getText().toString());
                        PhoneService phoneService = PhoneClient.getInstance().getPhoneservice();
                      //  phone1.setId(phone.getId());
                     Call<Phone> call =  phoneService.update(phone.getId(),phone1);

               call.enqueue(new Callback<Phone>() {
                   @Override
                   public void onResponse(Call<Phone> call, Response<Phone> response) {
                       Log.d("responsebody" ,response.body().getName()+"");
                        updateItem(response.body(),holder.getAdapterPosition());
                   }

                   @Override
                   public void onFailure(Call<Phone> call, Throwable t) {

                   }
               });
                    }
                });
                dlg.setNegativeButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        PhoneService phoneService = PhoneClient.getInstance().getPhoneservice();
                        Call<Void> call = phoneService.deleteByid(phone.getId());
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                removeItem(holder.getAdapterPosition());
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

    public class PhoneViewHoloder extends  RecyclerView.ViewHolder {
        TextView txtName, txtTel;
        public PhoneViewHoloder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.name);
            txtTel= itemView.findViewById(R.id.phone);


        }
    }
}

