package com.findpet.project01.account;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.findpet.project01.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Join extends AppCompatActivity {

    private EditText Id, password, pwcheck, name, tel, nickname;
    private Button btnjoin, idcheck, nickcheck;
    private TextView pwcheckresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Id = findViewById(R.id.joinusername);
        password = findViewById(R.id.joinpw);
        pwcheck = findViewById(R.id.joinpwcheck);
        name = findViewById(R.id.joinname);
        tel = findViewById(R.id.joinHP);
        nickname = findViewById(R.id.joinnickname);

        btnjoin = findViewById(R.id.btnjoin);
        idcheck = findViewById(R.id.btnIdcheck);
        nickcheck = findViewById(R.id.btnNickcheck);

        pwcheckresult = findViewById(R.id.pwcheckresult);


        //아이디 중복검사
        idcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemberService memberService = Client.getInstance().getMemberService();
                Call<Integer> call = memberService.findusername(Id.getText().toString());
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.body() == 0) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Join.this);
                            builder.setMessage("사용 가능한 이메일입니다");
                            builder.setPositiveButton("닫기", null);
                            builder.show();
                            idcheck.setVisibility(View.INVISIBLE);
                            Id.setEnabled(false);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Join.this);
                            builder.setMessage("사용 불가능한 이메일입니다");
                            builder.setPositiveButton("닫기", null);
                            builder.show();
                            Id.setText("");
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });

            }
        });
        //페스워드 중복 검사
       pwcheck.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void afterTextChanged(Editable editable) {
               //뷰에 보이게 설정
               pwcheckresult.setVisibility(View.VISIBLE);
               if(password.getText().toString().equals(pwcheck.getText().toString())) {
                   pwcheckresult.setText("비밀번호가 일치합니다");
               } else {
                   pwcheckresult.setText("비밀번호가 틀립니다.");
               }
           }
       });

       //닉네임 체크
       nickcheck.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               MemberService memberService = Client.getInstance().getMemberService();
               Call<Integer> call = memberService.findnick(nickname.getText().toString());
               call.enqueue(new Callback<Integer>() {
                   @Override
                   public void onResponse(Call<Integer> call, Response<Integer> response) {
                      if(response.body().equals(0)) {
                          AlertDialog.Builder builder = new AlertDialog.Builder(Join.this);
                          builder.setMessage("사용 가능한 별명입니다.");
                          builder.setPositiveButton("닫기", null);
                          builder.show();
                      } else {
                          AlertDialog.Builder builder = new AlertDialog.Builder(Join.this);
                          builder.setMessage("이미 사용중인 별명입니다.");
                          builder.setPositiveButton("닫기", null);
                          builder.show();
                          nickname.requestFocus();
                      }
                   }

                   @Override
                   public void onFailure(Call<Integer> call, Throwable t) {

                   }
               });
           }
       });


        //회원가입
        btnjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Id.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Join.this);
                    builder.setMessage("이메일을 입력해주세요");
                    builder.setPositiveButton("닫기", null);
                    builder.show();
                    Id.requestFocus();
                }
                if(password.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Join.this);
                    builder.setMessage("비밀번호를 입력해주세요");
                    builder.setPositiveButton("닫기", null);
                    builder.show();
                    password.requestFocus();
                }
                if(pwcheck.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Join.this);
                    builder.setMessage("비밀번호를 입력해주세요");
                    builder.setPositiveButton("닫기", null);
                    builder.show();
                    pwcheck.requestFocus();
                }
                if(tel.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Join.this);
                    builder.setMessage("전화번호를 입력해주세요");
                    builder.setPositiveButton("닫기", null);
                    builder.show();
                    tel.requestFocus();
                }
                if(name.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Join.this);
                    builder.setMessage("이름을 입력해주세요");
                    builder.setPositiveButton("닫기", null);
                    builder.show();
                    name.requestFocus();
                }
                if(nickname.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Join.this);
                    builder.setMessage("닉네임을 입력해주세요");
                    builder.setPositiveButton("닫기", null);
                    builder.show();
                    nickname.requestFocus();
                }
                Member member = new Member();
                member.setUsername(Id.getText().toString());
                member.setPassword(password.getText().toString());
                member.setName(name.getText().toString());
                member.setTel(tel.getText().toString());
                member.setNickname(nickname.getText().toString());
                Log.d("Member", member+"");

                MemberService memberService = Client.getInstance().getMemberService();
                Call<Member> call = memberService.join(member);
                call.enqueue(new Callback<Member>() {
                    @Override
                    public void onResponse(Call<Member> call, Response<Member> response) {
                        Log.d("onResponse", response.toString());
                        Intent intent = new Intent(Join.this, Login.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Member> call, Throwable t) {
                        Log.d("onFailure", call+"");
                    }
                });
            }
        });
    }
}