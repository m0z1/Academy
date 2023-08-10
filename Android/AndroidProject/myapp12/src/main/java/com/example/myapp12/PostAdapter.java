package com.example.myapp12;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {


    private ArrayList<Post> postList;

    public PostAdapter(ArrayList<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_post,parent,false); // 형상화
        PostViewHolder postViewHolder = new PostViewHolder(view);

        return postViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostViewHolder holder, int position) {
        Post post = postList.get(position);

        holder.txuserId.setText(Integer.toString(post.getUserId()));
       holder.txId.setText(Integer.toString(post.getId()));
       holder.txbody.setText(post.getBody());
       holder.txTitle.setText(post.getTitle());





    }

    @Override
    public int getItemCount() {
        return postList == null ? 0 : postList.size();
    }

    class PostViewHolder extends  RecyclerView.ViewHolder {

        TextView txId , txTitle, txuserId, txbody;
        ImageView imageView;
        View itemView;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
          this.txId = itemView.findViewById(R.id.txId2);
          this.txuserId = itemView.findViewById(R.id.txuserId);
          this.txTitle = itemView.findViewById(R.id.txTitle2);
          this.txbody = itemView.findViewById(R.id.txBody);

        }
    }
}
