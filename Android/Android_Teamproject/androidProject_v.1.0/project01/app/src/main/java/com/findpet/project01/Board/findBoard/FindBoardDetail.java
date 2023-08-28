package com.findpet.project01.Board.findBoard;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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
import com.findpet.project01.databinding.ActivityFindBoardDetailBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindBoardDetail extends AppCompatActivity {
    private ActivityFindBoardDetailBinding binding;

    ArrayList<FindBoard> findBoardList;

    TextView txusername, txtel, txname;

    FindAdapter findAdapter;

    String tel;

    String baseUrl = "http://10.100.102.45:8899";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_board_detail);

        binding = ActivityFindBoardDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        findAdapter = new FindAdapter(findBoardList);


        //뒤로가기
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //이미지 클릭 -> 홈으로
        binding.missing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
            }
        });

        //Intent로 findId값 받아오기
        Intent intentCmt = getIntent();
        Long findId = intentCmt.getLongExtra("findId", 0);
        Log.i("intent 값", findId+"");

        //상세 내용 받아오기
        BoardInterface boardInterface = BoardClient.getInstance().getBoardInterface();
        Call<FindBoard> call1 = boardInterface.view(findId);
        call1.enqueue(new Callback<FindBoard>() {
            @Override
            public void onResponse(Call<FindBoard> call, Response<FindBoard> response) {
                FindBoard findBoard = response.body();

                binding.txfinderId.setText(1+"");
                //발견자에 유저네임 뿌리기 ----> 다시해야 함
                MemberService memberService = Client.getInstance().getMemberService();
                Call<Member> call2 = memberService.findmember(binding.txfinderId.getText().toString());    //멤버 아이디(username)줘야함 (글 작성할 때, member 객체에 아이디 넣어주기)
                call2.enqueue(new Callback<Member>() {
                    @Override
                    public void onResponse(Call<Member> call, Response<Member> response) {
                        Member member = response.body();
                        binding.txfinderId.setText(member.getUsername());
                        //findId에 스타일 적용
                        binding.txfinderId.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                    }

                    @Override
                    public void onFailure(Call<Member> call, Throwable t) {

                    }
                });

                //발견자에 dialogView 띄우기
                binding.txfinderId.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        View diaglogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.member_dialog,null);
                        AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                        dlg.setTitle("연락처 상세보기");
                        dlg.setView(diaglogView);

                        //finderId값을 사용해서 member 정보 가져와서 뿌리기 ---> 이것도 다시하기
                        MemberService memberService = Client.getInstance().getMemberService();
                        //Call<Member> call1 = memberService.findById(Long.parseLong(binding.txfinderId.getText().toString()));
                        Call<Member> call1 = memberService.findmember((2+""));  //FindBoard의 memberId 받아와서 줘야함 (글 작성할 때 member객체에 입력)

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
                                if(tel != null) {
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

                binding.txbreed.setText(findBoard.getBreed());
                binding.txpetgender.setText(findBoard.getPetgender());
                binding.txpetcharacter.setText(findBoard.getPetcharacter());
                binding.txfindAddr.setText(findBoard.getFindaddr());
                binding.txPetBreedTitle.setText(findBoard.getBreed());
                binding.txcontent.setText(findBoard.getContent());
                binding.txpetage.setText(findBoard.getPetage());
                binding.txPetName.setText(findBoard.getPetname());

                //날짜 포맷 변경
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = simpleDateFormat.format(findBoard.getRegdate());

                binding.txregdate.setText(date);

                if(response.body().getImgFileList()!=null) {
                    Log.d("imgFileList.size", "size : " + response.body().getImgFileList().size());
                    for(int i = 0; i < response.body().getImgFileList().size(); i++) {
                        List<ImageView> viewList = Arrays.asList(binding.img1, binding.img2, binding.img3);

                        Glide.with(getApplicationContext())
                                .load(baseUrl + response.body().getImgFileList().get(i).getImgUrl())
                                .override(500)
                                .into(viewList.get(i));
                    }
                }
                /*//이미지 3가지 전부 null이 아닌 경우에
                if(response.body().getImgFileList().get(0).getImgUrl() != null && response.body().getImgFileList().get(1).getImgUrl() !=null
                        && response.body().getImgFileList().get(2).getImgUrl() !=null){
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
                } else {
                    binding.img1.setVisibility(View.GONE);
                    binding.img2.setVisibility(View.GONE);
                    binding.img3.setVisibility(View.GONE);
                }*/
            }


            @Override
            public void onFailure(Call<FindBoard> call, Throwable t) {

            }
        });

        //화면 시작 시 댓글 수 불러오기
        //댓글 개수 가져오기
        CommentService commentService1 = CommentClient.getInstance().getCommentService();
        Call<Integer> callcmt = commentService1.count(findId);
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
                            FindBoard findBoard = (FindBoard) intent.getSerializableExtra("findBoard");
                            binding.txfindAddr.setText(findBoard.getFindaddr());
                            binding.txcontent.setText(findBoard.getContent());
                            binding.txbreed.setText(findBoard.getBreed());
                            binding.txpetgender.setText(findBoard.getPetgender());
                            binding.txpetage.setText(findBoard.getPetage());
                            binding.txpetcharacter.setText(findBoard.getPetcharacter());
                            binding.txPetName.setText(findBoard.getPetname());
                            binding.txPetBreedTitle.setText(findBoard.getBreed());
                        }
                    }
                });



        //댓글창 이동 (Intent)
        binding.btnCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCmt = new Intent(getApplicationContext(), FindCommentList.class);
                intentCmt.putExtra("findId", findId);
                launcher.launch(intentCmt);
                //startActivityForResult(intentCmt, 1);
            }
        });

        //로그인 정보와 작성자가 같다면 아래 버튼 생성
        //게시글 수정
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentUpdate = new Intent(getApplicationContext(), FindBoardUpdate.class);
                intentUpdate.putExtra("findId", findId);
                launcher.launch(intentUpdate);
            }
        });


        //게시글 삭제
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(FindBoardDetail.this);
                dlg.setTitle("정말 삭제하시겠습니까?");
                dlg.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BoardInterface boardInterface1 = BoardClient.getInstance().getBoardInterface();
                        Call<Void> call = boardInterface1.deleteById1(findId);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                Toast.makeText(getApplicationContext(), "삭제 완료!", Toast.LENGTH_SHORT).show();
                                findAdapter.deleteItem(findId);
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