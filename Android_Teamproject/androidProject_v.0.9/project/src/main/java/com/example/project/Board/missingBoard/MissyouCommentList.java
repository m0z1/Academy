package com.example.project.Board.missingBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.comment.Comment;
import com.example.project.Board.findBoard.FindCommentAdapter;
import com.example.project.comment.CommentClient;
import com.example.project.comment.CommentService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MissyouCommentList extends AppCompatActivity {

    EditText edComment;
    Button btnInsert, btnBack2;

    RecyclerView recyclerView;

    List<Comment> commentList;
    MissyouCommentAdapter missyouCommentAdapter;

    Intent intentCmt;

    Integer cmtcnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missyou_comment_list);

        edComment = findViewById(R.id.edComment);
        btnInsert = findViewById(R.id.btnInsert);
        recyclerView = findViewById(R.id.rvCmt);
        btnBack2 = findViewById(R.id.btnBack2);

        //리사이클러 뷰 어댑터 연결
        commentList = new ArrayList<>();
        missyouCommentAdapter = new MissyouCommentAdapter(commentList);
        recyclerView.setAdapter(missyouCommentAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MissyouCommentList.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        //intent 게시글 번호 받아오기
        intentCmt = getIntent();
        Long missingId = intentCmt.getLongExtra("missingId", 0);



        //댓글 리스트 받아오기(missyou)
        CommentService commentService = CommentClient.getInstance().getCommentService();
        Call<List<Comment>> call = commentService.findAllByMissingId(missingId);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                List<Comment> resource = response.body();
                for(Comment comment : resource) {
                    commentList.add(comment);
                }
                missyouCommentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.d("검색 실패", ""+commentList);
            }
        });


        //댓글 추가 (missyou)
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comment comment = new Comment();
                comment.setMember_id(Long.parseLong(1+""));
                comment.setContent(edComment.getText().toString());
                comment.setMissing_id(missingId);

                CommentService commentService1 = CommentClient.getInstance().getCommentService();
                Call<Comment> call1 = commentService1.insert2(comment, missingId);
                call1.enqueue(new Callback<Comment>() {
                    @Override
                    public void onResponse(Call<Comment> call, Response<Comment> response) {
                        edComment.setText("");
                        if(missingId.equals(comment.getMissing_id())){
                            missyouCommentAdapter.addItem(response.body());
                        }
                        Toast.makeText(getApplicationContext(), "댓글 작성 완료", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Comment> call, Throwable t) {

                    }
                });
            }
        });

        //뒤로가기 버튼 + Intent로 댓글 수 보내기
        btnBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //댓글 갯수
                CommentService commentService1 = CommentClient.getInstance().getCommentService();
                Call<Integer> call1 = commentService1.count2(missingId);
                call1.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        Intent intentCmt = new Intent(getApplicationContext(), MissyouBoardDetail.class);
                        intentCmt.putExtra("cmtcnt", response.body());
                        setResult(RESULT_OK, intentCmt);
                        finish();
                    }
                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                    }
                });
            }
        });








    }
}