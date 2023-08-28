package com.findpet.project01.account;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.findpet.project01.Main;
import com.findpet.project01.R;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.AccessTokenInfo;
import com.kakao.sdk.user.model.User;

import java.security.Security;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.LoginException;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {


    private ImageButton kakaologin;
    private EditText edtId, edtPw;
    private Button btnLogin, btnJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        kakaologin = findViewById(R.id.kakaologin);
        edtId = findViewById(R.id.username);
        edtPw = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        btnJoin = findViewById(R.id.btnJoin);


        SharedPreferences sharedPreferences = getSharedPreferences("autoLogin", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");

        //자동 로그인(SharedPreferences 사용 "autoLogin" 파일 안에 저장된 정보 가져옴)
       if(username.equals("")) { // 저장된 정보가 없으면 로그인 필요

        } else if(username != null && password != null){ // 저장된 로그인이 있으면 값을 가져와 로그인 시켜줌
           login(username, password);
       }

       //회원가입으로 이동
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Join.class);
                startActivity(intent);
            }
        });
        //로그인 실행 버튼
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtId.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "아이디를 입력하세요", Toast.LENGTH_LONG).show();
                    edtId.requestFocus();
                } else if (edtPw.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요", Toast.LENGTH_LONG).show();
                    edtPw.requestFocus();
                } else {
                    String username = edtId.getText().toString();
                    String password = edtPw.getText().toString();

                login(username, password);

            }
        }
        });


        // 카카오 자동로그인

        UserApiClient.getInstance().accessTokenInfo(new Function2<AccessTokenInfo, Throwable, Unit>() {
            @Override
            public Unit invoke(AccessTokenInfo accessTokenInfo, Throwable throwable) {
                if (accessTokenInfo != null) {
                    //카카오에서 발행한 토큰의 값의 id값과 유저의 카카오의 고유id를 비교
                    //일치하면 로그인 화면 전환없이 메인으로 이동
                    if(username.equals(accessTokenInfo.getId().toString())) {
                        Intent intent = new Intent(Login.this, Main.class);
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    }
                    Log.d("kakakak", accessTokenInfo+"");

                }return null;
            }
        });


        //카카오 로그인 api 버튼
        kakaologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //기기에 카카오톡 어플이 깔려있는지 확인
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(Login.this)) {
                    //깔려있으면 어플로 로그인
                    UserApiClient.getInstance().loginWithKakaoTalk(Login.this, callback);
                } else {
                    //없으면 웹으로 카카오 아이디를 통해 로그인
                    UserApiClient.getInstance().loginWithKakaoAccount(Login.this, callback);
                }
            }
        });

    }

    //카카오 로그인 로직
    Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
        @Override
        public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
            if(oAuthToken != null){
                Log.d("123123", oAuthToken +"");
                //카카오에서 제공하는 유저 정보 가져오는 방법
                UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                    @Override
                    public Unit invoke(User user, Throwable throwable) {
                        //로그인 성공시 유저의 카카오 고유id를 autoLogin 파일에 저장 추후에 자동 로그인에 사용
                        SharedPreferences sharedPreferences = getSharedPreferences("autoLogin", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", user.getId().toString());
                        editor.commit();
                        MemberService memberService = Client.getInstance().getMemberService();
                        Call<Integer> call = memberService.findusername(user.getId().toString());
                        call.enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, @Nullable Response<Integer> response) {

                                Log.d("kakaologin", response.toString());

                                if(response.body() == 0) {
                                    //db에 저장
                                    memberjoin();
                                    Intent intent = new Intent(Login.this, Main.class);
                                    startActivity(intent);

                                } else {
                                    Intent intent = new Intent(Login.this, Main.class);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {
                                Log.d("error1", "eeee");
                            }
                        });
                        return null;
                    }
                });
            } else {

            }
            return null;
        }
    };

    //로그인 로직
    private void login(String username, String password) {

        MemberService memberService = Client.getInstance().getMemberService();
        Call<Integer> call = memberService.login(username, password);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.d("login", response.body()+"");

                if (response.body().equals(1)) { //로그인 성공
                    //일반유저 자동 로그인 설정
                    SharedPreferences autoLogin = getSharedPreferences("autoLogin", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = autoLogin.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.commit();
                    Intent intent = new Intent(Login.this, Main.class);
                    startActivity(intent);
                } else if(response.body().equals(0)){ // 로그인 실패(아이디나 비번 틀렷을 경우)
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setTitle("로그인 실패!");
                    builder.setMessage("아이디나 비밀번호를 확인하세요");
                    builder.setPositiveButton("닫기", null);
                    builder.show();
                    edtId.setText("");
                    edtPw.setText("");
                    edtId.requestFocus();
                } else { //로그인 실패 (회원이 아닌 경우)
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Login.this);
                    builder1.setTitle("로그인 실패!");
                    builder1.setMessage("회원이 아닙니다");
                    builder1.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Login.this, Join.class);
                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        }
                    });
                    builder1.show();
                }

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.d("eeeee", "eeeeeeeee");
            }
        });
    }

    //카카오 로그인 시 db 저장 로직
    private void memberjoin() {
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {

                Member member = new Member();
                if (user.getKakaoAccount().getEmail() != null) {
                    member.setUsername(user.getKakaoAccount().getEmail());
                } else {
                    member.setUsername(user.getId().toString());
                }
                //보안때문에 카카오유저의 비밀번호는 저장 안함
                member.setPassword("");
                if (user.getKakaoAccount().getName() != null) {
                    member.setName(user.getKakaoAccount().getName());
                } else {
                    member.setName(user.getKakaoAccount().getProfile().getNickname());
                }
                if (user.getKakaoAccount().getPhoneNumber() != null) {
                    member.setTel(user.getKakaoAccount().getPhoneNumber());
                } else {
                    member.setTel("");
                }
                if(user.getKakaoAccount().getProfile().getNickname() != null) {
                    member.setNickname(user.getKakaoAccount().getProfile().getNickname());
                } else {
                    member.setNickname(user.getKakaoAccount().getName());
                }
                MemberService memberService = Client.getInstance().getMemberService();
                Call<Member> call1 = memberService.join(member);
                call1.enqueue(new Callback<Member>() {
                    @Override
                    public void onResponse(Call<Member> call, Response<Member> response) {

                    }

                    @Override
                    public void onFailure(Call<Member> call, Throwable t) {

                    }
                });
                return null;
            }
        });
    }

}