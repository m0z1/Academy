package com.findpet.project01.Board.missingBoard;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.findpet.project01.R;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MissyouAdapter extends RecyclerView.Adapter<MissyouAdapter.MissyouViewHolder> {

    private List<MissingBoard> missingBoardList;

    public interface  OnItemClickListener{
        void OnItemClick(int pos);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    String baseUrl = "http://10.100.102.44:8899";

    public MissyouAdapter(List<MissingBoard> missingBoardList) {
        this.missingBoardList = missingBoardList;
    }

    //추가
    public void addItem(MissingBoard missingBoard){
        missingBoardList.add(missingBoard);
        notifyDataSetChanged();
    }

    //삭제
    public void deleteItem(Long missingId){
        missingBoardList.remove(missingId);
        notifyDataSetChanged();
    }

    public void removeAllItem(List<MissingBoard> missingBoardList) {
        missingBoardList.removeAll(missingBoardList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MissyouViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(com.findpet.project01.R.layout.list_item, null);
        return new MissyouViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MissyouViewHolder holder, int position) {
        MissingBoard missingBoard = missingBoardList.get(position);

        holder.petcharacter.setText(missingBoard.getPetcharacter());
        holder.petgender.setText(missingBoard.getPetgender());
        if(missingBoard.getImgFileList().size() != 0) {
            Glide.with(holder.itemView.getContext())
                    .load(baseUrl+missingBoard.getImgFileList().get(0).getImgUrl())
                    .override(500,400)
                    .into(holder.petImage);
            Log.d("url", missingBoard.getImgFileList().get(0).getImgUrl());
        } else if(missingBoard.getImgFileList().size() == 0) {

        }
        holder.findaddr.setText(missingBoard.getMissingaddr());
        holder.breed.setText(missingBoard.getBreed());
        holder.petCategory.setText(missingBoard.getPetcategory());
        Long missingId = missingBoard.getMissingId();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MissyouBoardDetail.class);
                intent.putExtra("missingId", missingId);
                view.getContext().startActivity(intent);
            }
        });

        //날짜 포맷 변경
              /*  Calendar cal = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = simpleDateFormat.format(missingBoard.getRegdate());*/

                //holder.txregdate.setText(date);

//                holder.ivimage.setImageResource(missingBoard.getImgFileList().get(0).getImgUrl());
//        BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
//                Call<MissingBoard> call = boardInterface.view2(missingBoard.getMissingId());
//                call.enqueue(new Callback<MissingBoard>() {
//                    @Override
//                    public void onResponse(Call<MissingBoard> call, Response<MissingBoard> response) {
//                        Glide.with(holder.itemView.getContext())
//                                .load(basUrl+response.body().getImgFileList().get(0).getImgUrl())
//                                .override(500,400)
//                                .into(holder.ivimage);
//                    }

//                    @Override
//                    public void onFailure(Call<MissingBoard> call, Throwable t) {
//
//                    }
//                });


    }

    @Override
    public int getItemCount() {
        return missingBoardList == null? 0 : missingBoardList.size();
    }


    class MissyouViewHolder extends RecyclerView.ViewHolder {
        ImageView petImage;
        TextView breed, petgender, findaddr, petcharacter, petCategory;
        public MissyouViewHolder(@NonNull View itemView) {
            super(itemView);
            petImage = itemView.findViewById(R.id.petImage);
            breed = itemView.findViewById(R.id.Breed);
            petgender = itemView.findViewById(R.id.petGender);
            findaddr = itemView.findViewById(R.id.findAddr);
            petcharacter = itemView.findViewById(R.id.petCharacter);
            petCategory = itemView.findViewById(R.id.petCategory);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.OnItemClick(getAdapterPosition());
                }
            });
        }
    }
}
