package com.findpet.project01.account;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.findpet.project01.R;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.AccessTokenInfo;
import com.kakao.sdk.user.model.User;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberInfo extends AppCompatActivity {
    private ImageView Home;
    private TextView memberName;
    private EditText membername, memberusername, memberpw, memberpwcheck, membernickname, memberHP;
    private Button memberupdate, memberdelete, logout, btnnickcheck;
    private String pw = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_info);

        membername = findViewById(R.id.membername);
        memberusername = findViewById(R.id.memberusername);
        memberpw = findViewById(R.id.memberpw);
        memberpwcheck = findViewById(R.id.memberpwcheck);
        membernickname = findViewById(R.id.membernickname);
        memberHP = findViewById(R.id.memberHP);
        logout = findViewById(R.id.logout);
        memberdelete = findViewById(R.id.memberdelete);
        memberupdate = findViewById(R.id.memberupdate);
        btnnickcheck = findViewById(R.id.btnNickcheck);
        Home = findViewById(R.id.Home);
        memberName = findViewById(R.id.memberName);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemberInfo.this, Math.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("autoLogin", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");

        //일반 유저로 로그인 했을 때 회원정보 조회
        if(username != null && password != null){
            Log.d("username", username);
            MemberService memberService = Client.getInstance().getMemberService();
            Call<Member> call = memberService.findmember(username);
            call.enqueue(new Callback<Member>() {
                @Override
                public void onResponse(Call<Member> call, Response<Member> response) {
                    Log.d("member", response.body().toString());
                    Member member = response.body();
                    membername.setText(member.getName());
                    memberusername.setText(member.getUsername());
                    memberHP.setText(member.getTel());
                    membernickname.setText(member.getNickname());
                    memberName.setText(member.getName().toString() + "님 안녕하세요");
                }

                @Override
                public void onFailure(Call<Member> call, Throwable t) {

                }
            });
        } else {
            //카카오로 로그인 했을 때 회원정보 조회
            UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                @Override
                public Unit invoke(User user, Throwable throwable) {
                    if(user.getKakaoAccount().getEmail() != null){
                        memberusername.setText(user.getKakaoAccount().getEmail());
                    } else {
                        memberusername.setText("");
                    }
                    if(user.getKakaoAccount().getName() != null) {
                        membername.setText(user.getKakaoAccount().getName());
                    } else {
                        membername.setText("");
                    }
                    if(user.getKakaoAccount().getPhoneNumber() != null) {
                        memberHP.setText(user.getKakaoAccount().getPhoneNumber());
                    } else {
                        memberHP.setText("");
                    }
                    if(user.getKakaoAccount().getProfile().getNickname() != null) {
                        membernickname.setText(user.getKakaoAccount().getProfile().getNickname());
                    } else {
                        membernickname.setText(user.getKakaoAccount().getName());
                    }
                    return null;
                }
            });
        }

        //닉네임 변경 시 중복 체크
        btnnickcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemberService memberService = Client.getInstance().getMemberService();
                Call<Integer> call = memberService.findnick(membernickname.getText().toString());
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.body().equals(0)) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MemberInfo.this);
                            builder.setMessage("사용 가능한 별명입니다.");
                            builder.setPositiveButton("닫기", null);
                            builder.show();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MemberInfo.this);
                            builder.setMessage("이미 사용중인 별명입니다.");
                            builder.setPositiveButton("닫기", null);
                            builder.show();
                            membernickname.requestFocus();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
            }
        });

        //회원정보 수정
        memberupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username1 = memberusername.getText().toString();
                String name = membername.getText().toString();
                if(!memberpw.getText().toString().equals("")) {
                    pw = memberpw.getText().toString();
                } else if(memberpw.getText().toString().equals("")) {
                    pw = password;
                }

                String tel = memberHP.getText().toString();
                String nickname = membernickname.getText().toString();

                Member member = new Member(username1, pw, name, tel, nickname);

                MemberService memberService = Client.getInstance().getMemberService();
                Call<Member> call = memberService.update(username, member);
                call.enqueue(new Callback<Member>() {
                    @Override
                    public void onResponse(Call<Member> call, Response<Member> response) {
                        Log.d("member", response.body().toString());
                        Intent intent = new Intent(MemberInfo.this, MemberInfo.class);
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    }

                    @Override
                    public void onFailure(Call<Member> call, Throwable t) {

                    }
                });
            }
        });
        //로그아웃
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("autoLogin", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Intent intent = new Intent(MemberInfo.this, Login.class);
                if(username != null && password != null) {
                    editor.clear();
                    editor.commit();
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                } else if(username !=null && password == null){
                    UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                        @Override
                        public Unit invoke(Throwable throwable) {
                           editor.clear();
                            editor.commit();
                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            return null;
                        }
                    });
                }
            }
        });
        //회원 탈퇴
        memberdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("autoLogin", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Intent intent = new Intent(MemberInfo.this, Login.class);
                MemberService memberService = Client.getInstance().getMemberService();
                //카카오 로그인 유저
                if(username != null && password == null) {
                    Call<Member> call = memberService.delete(username);
                    call.enqueue(new Callback<Member>() {
                        @Override
                        public void onResponse(Call<Member> call, Response<Member> response) {
                            UserApiClient.getInstance().unlink(new Function1<Throwable, Unit>() {
                                @Override
                                public Unit invoke(Throwable throwable) {
                                    return null;
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<Member> call, Throwable t) {
                            Log.d("실패", "실패");
                        }
                    });
                    editor.clear();
                    editor.commit();
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                } else if (username != null && password != null) {
                    Call<Member> call = memberService.delete(username);
                    call.enqueue(new Callback<Member>() {
                        @Override
                        public void onResponse(Call<Member> call, Response<Member> response) {

                        }

                        @Override
                        public void onFailure(Call<Member> call, Throwable t) {

                        }
                    });
                    editor.clear();
                    editor.commit();
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
            }
        });
    }
}