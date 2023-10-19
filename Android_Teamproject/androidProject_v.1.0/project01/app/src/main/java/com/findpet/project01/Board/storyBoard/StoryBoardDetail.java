package com.findpet.project01.Board.storyBoard;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.findpet.project01.Board.BoardClient;
import com.findpet.project01.Board.BoardInterface;
import com.findpet.project01.Main;
import com.findpet.project01.R;
import com.findpet.project01.account.Client;
import com.findpet.project01.account.Member;
import com.findpet.project01.account.MemberService;
import com.findpet.project01.comment.CommentClient;
import com.findpet.project01.comment.CommentService;
import com.findpet.project01.databinding.ActivityStoryBoardDetailBinding;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoryBoardDetail extends AppCompatActivity {

    private ActivityStoryBoardDetailBinding binding;

    List<StoryBoard> storyBoardList;

    TextView txusername, txtel, txname;

    StoryAdapter storyAdapter;

    String tel;

    String basUrl = "http://10.100.102.45:8899";

    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_board_detail);

        binding = ActivityStoryBoardDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        storyAdapter = new StoryAdapter(storyBoardList);

        TextView membername = findViewById(R.id.memberName);

        SharedPreferences sharedPreferences = getSharedPreferences("autoLogin", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        MemberService memberService = Client.getInstance().getMemberService();
        Call<Member> call = memberService.findmember(username);
        call.enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                Member member = response.body();
                if(member.getName()!=null) {
                    name = member.getName().toString();
                } else {
                    name = "";
                }
                if(!name.equals("")){
                    membername.setText(name + " 님 환영합니다");
                }
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {

            }
        });

        //이미지 클릭 -> 홈으로 이동
        binding.StoryImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
            }
        });

        //뒤로가기
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //Intent로 storyId값 받아오기
        Intent intentCmt = getIntent();
        Long storyId = intentCmt.getLongExtra("storyId", 0);
        Log.i("intent 값", storyId+"");


        //상세 내용 받아오기
        BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
        Call<StoryBoard> call1 = boardInterface.view3(storyId);
        call1.enqueue(new Callback<StoryBoard>() {
            @Override
            public void onResponse(Call<StoryBoard> call, Response<StoryBoard> response) {
                StoryBoard storyBoard = response.body();

                binding.txwriterId.setText(1+"");
                //발견자에 유저네임 뿌리기 ----> 다시해야 함
                MemberService memberService = Client.getInstance().getMemberService();
                Call<Member> call2 = memberService.findmember(binding.txwriterId.getText().toString());    //멤버 아이디 줘야함 (글 작성할 때, member 객체에 아이디 넣어주기)
                call2.enqueue(new Callback<Member>() {
                    @Override
                    public void onResponse(Call<Member> call, Response<Member> response) {
                        Member member = response.body();
                        binding.txwriterId.setText(member.getUsername());
                        //findId에 스타일 적용
                        binding.txwriterId.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                    }

                    @Override
                    public void onFailure(Call<Member> call, Throwable t) {

                    }
                });
                
                //작성자에 dialogView띄우기
                binding.txwriterId.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        View diaglogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.member_dialog,null);
                        AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                        dlg.setTitle("연락처 상세보기");
                        dlg.setView(diaglogView);

                        //storyId값을 사용해서 member 정보 가져와서 뿌리기 ---> 이것도 다시하기
                        MemberService memberService = Client.getInstance().getMemberService();
                        //Call<Member> call1 = memberService.findById(Long.parseLong(binding.txwriterId.getText().toString()));
                        Call<Member> call1 = memberService.findmember((1+""));  //StoryBoard의 memberId 받아와서 줘야함 (글 작성할 때 member객체에 입력)

                        call1.enqueue(new Callback<Member>() {
                            @Override
                            public void onResponse(Call<Member> call, Response<Member> response) {
                                Member member = response.body();
                                txusername = diaglogView.findViewById(R.id.txusername);
                                txtel = diaglogView.findViewById(R.id.txtel);
                                txname = diaglogView.findViewById(R.id.txname);


                                txname.setText(member.getName());
                                txusername.setText(member.getUsername());
                                txtel.setText(member.getTel());
                                tel = member.getTel();
                            }

                            @Override
                            public void onFailure(Call<Member> call, Throwable t) {

                            }
                        });

                        dlg.setPositiveButton("연락하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(tel != null){
                                    Uri uri = Uri.parse("tel:"+tel);
                                    Log.i("다이얼 번호:", ""+uri);
                                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                                    view.getContext().startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "연락처가 등록되어있지 않습니다. 댓글을 남겨보세요!", Toast.LENGTH_LONG).show();
                                    dialogInterface.cancel();
                                }
                            }
                        });
                        dlg.setNegativeButton("취소", null);
                        dlg.show();
                    }
                });
                //binding.txwriterId.setText(Long.toString(storyBoard.getMember_id()));
                binding.txwriterId.setText(Long.toString(1));
                binding.txcontent.setText(storyBoard.getContent());


                //날짜 포맷 변경
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = simpleDateFormat.format(storyBoard.getRegdate());

                binding.txregdate.setText(date);

                //이미지 받아오기
                Glide.with(getApplicationContext())
                        .load(basUrl+response.body().getImgFileList().get(0).getImgUrl())
                        .override(500,400)
                        .into(binding.img1);
                Glide.with(getApplicationContext())
                        .load(basUrl+response.body().getImgFileList().get(1).getImgUrl())
                        .override(500,400)
                        .into(binding.img2);
                Glide.with(getApplicationContext())
                        .load(basUrl+response.body().getImgFileList().get(2).getImgUrl())
                        .override(500,400)
                        .into(binding.img3);
            }

            @Override
            public void onFailure(Call<StoryBoard> call, Throwable t) {

            }
        });

        //화면 시작 시 댓글 수 불러오기
        //댓글 개수 가져오기
        CommentService commentService1 = CommentClient.getInstance().getCommentService();
        Call<Integer> callcmt = commentService1.count3(storyId);
        callcmt.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                binding.btnCmt.setText("댓글("+response.body()+")");
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });


        //Intent Launcher
        //1. 댓글창에서 돌아갈 때 댓글 수 늘려주기
        //2. 수정 후 내용 뿌려주기
        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent intent = result.getData();
                        if(result.getResultCode()== Activity.RESULT_OK){
                            Integer cmtcnt;
                            cmtcnt = intent.getIntExtra("cmtcnt", 0);
                            binding.btnCmt.setText("댓글("+cmtcnt+")");
                            return;
                        } else if(result.getResultCode()==Activity.RESULT_FIRST_USER) {
                            StoryBoard storyBoard = (StoryBoard) intent.getSerializableExtra("storyBoard");
                            binding.txTitle.setText(storyBoard.getTitle());
                            binding.txcontent.setText(storyBoard.getContent());
                        }
                    }
                });



        //댓글창 이동 (Intent)
        binding.btnCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCmt = new Intent(getApplicationContext(), StoryCommentList.class);
                intentCmt.putExtra("storyId", storyId);
                launcher.launch(intentCmt);
            }
        });

        //로그인 정보와 작성자가 같다면 아래 버튼 생성
        //게시글 수정
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentUpdate = new Intent(getApplicationContext(), StoryBoardUpdate.class);
                intentUpdate.putExtra("storyId", storyId);
                launcher.launch(intentUpdate);
            }
        });


        //게시글 삭제
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(StoryBoardDetail.this);
                dlg.setTitle("정말 삭제하시겠습니까?");
                dlg.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BoardInterface boardInterface1 = BoardClient.getInstance().getBoardInterface();
                        Call<Void> call = boardInterface1.deleteById3(storyId);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                Toast.makeText(getApplicationContext(), "삭제 완료!", Toast.LENGTH_SHORT).show();
                                storyAdapter.deleteItem(storyId);
                                finish();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });
                    }
                });
                dlg.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();   //다이얼로그 창 닫기
                    }
                });
                dlg.show();
            }
        });




    }
}