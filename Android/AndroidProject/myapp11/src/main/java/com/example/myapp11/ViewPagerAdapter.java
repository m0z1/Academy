package com.example.myapp11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolderPage> {
    private ArrayList<DataPage> listData;

    public ViewPagerAdapter(ArrayList<DataPage> list) {
        this.listData =list;

    }


    @NonNull
    @Override
    public ViewPagerAdapter.ViewHolderPage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_viewpager,parent,false);
        return new ViewHolderPage(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter.ViewHolderPage holder, int position) {
        holder.tvTitle.setText(listData.get(position).getTitle());
        holder.rl_layout.setBackgroundColor(listData.get(position).getColor());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ViewHolderPage extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private RelativeLayout rl_layout;

        public ViewHolderPage(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            rl_layout = itemView.findViewById(R.id.rl_layout);

        }
    }
}
