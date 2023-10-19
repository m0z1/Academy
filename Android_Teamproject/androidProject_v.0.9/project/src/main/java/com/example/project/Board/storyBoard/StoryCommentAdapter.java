package com.example.project.Board.storyBoard;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.comment.Comment;
import com.example.project.comment.CommentClient;
import com.example.project.comment.CommentService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoryCommentAdapter extends RecyclerView.Adapter<StoryCommentAdapter.ViewHolderCmt> {

    List<Comment> commentList;

    public StoryCommentAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }

    //추가
    public void addItem(Comment comment){
        commentList.add(comment);
        notifyDataSetChanged();
    }

    //삭제
    public void deleteItem(int position){
        commentList.remove(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StoryCommentAdapter.ViewHolderCmt onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new ViewHolderCmt(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryCommentAdapter.ViewHolderCmt holder, int position) {
        Comment comment = commentList.get(position);
        //holder.txmemberid.setText(Long.toString(comment.getMember_id()));
        holder.txComment.setText(comment.getContent());

        //댓글 수정
        //로그인 정보와 댓글 작성자가 동일하면 버튼 활성화
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            TextView txmemberiddlg;
            EditText edCommentdlg;
            @Override
            public void onClick(View view) {
                View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.comment_dialog, null);
                txmemberiddlg = dialogView.findViewById(R.id.txmemberiddlg);
                edCommentdlg = dialogView.findViewById(R.id.edCommentdlg);
                //txmemberiddlg.setText(Long.toString(comment.getMember_id()));
                edCommentdlg.setText(comment.getContent());

                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("댓글 상세보기");
                dlg.setView(dialogView);

                dlg.setPositiveButton("수정하기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Comment comment1 = new Comment(comment.getCommentId(), edCommentdlg.getText().toString());
                        CommentService commentService = CommentClient.getInstance().getCommentService();
                        Call<Comment> call = commentService.update3(comment1);
                        call.enqueue(new Callback<Comment>() {
                            @Override
                            public void onResponse(Call<Comment> call, Response<Comment> response) {
                                Comment comment2 = response.body();
                                //edCommentdlg.setText(comment2.getContent());
                                holder.txComment.setText(comment2.getContent());
                                Toast.makeText(view.getContext(), "수정 완료", Toast.LENGTH_SHORT).show();
                                dialogInterface.cancel();
                            }

                            @Override
                            public void onFailure(Call<Comment> call, Throwable t) {
                                Toast.makeText(view.getContext(), "수정 실패", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                dlg.show();
            }
        });


        //댓글 삭제
        //로그인 정보와 댓글 작성자가 동일하면 버튼 활성화
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg2 = new AlertDialog.Builder(view.getContext());
                dlg2.setTitle("정말 삭제하시겠습니까?");
                dlg2.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CommentService commentService = CommentClient.getInstance().getCommentService();
                        Call<Void> call = commentService.deleteById3(comment.getCommentId());
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                Toast.makeText(view.getContext(), "삭제 완료", Toast.LENGTH_SHORT).show();
                                deleteItem(holder.getAdapterPosition());
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(view.getContext(), "삭제 실패", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                dlg2.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                dlg2.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return commentList==null?0:commentList.size();
    }



    public class ViewHolderCmt extends RecyclerView.ViewHolder{
        TextView txmemberid, txComment;
        Button btnUpdate, btnDelete;


        public ViewHolderCmt(@NonNull View itemView) {
            super(itemView);
            txmemberid = itemView.findViewById(R.id.txmemberid);
            txComment = itemView.findViewById(R.id.txComment);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            itemView = itemView;



        }
    }
}
