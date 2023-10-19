package com.findpet.project01.Board.shelter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.findpet.project01.Main;
import com.findpet.project01.R;
import com.findpet.project01.account.Client;
import com.findpet.project01.account.Member;
import com.findpet.project01.account.MemberService;
import com.findpet.project01.databinding.ActivityShelterBoardDetailBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShelterBoardDetail extends AppCompatActivity {

    private ActivityShelterBoardDetailBinding binding;
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_board_detail);binding = ActivityShelterBoardDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        TextView membername = findViewById(R.id.memberName);

        SharedPreferences sharedPreferences = getSharedPreferences("autoLogin", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");



        //뒤로가기 버튼
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //이미지 -> 홈으로
        ImageView shelterImg = findViewById(R.id.ShelterImg);
        shelterImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
            }
        });

        //Intent로 받아오기
        Intent intent = getIntent();
        Shelter shelter = (Shelter) intent.getSerializableExtra("shelter");
        Glide.with(this).load(shelter.getImage()).into(binding.img1);
        binding.noticeNo.setText(shelter.getNoticeNo());
        binding.kindCd.setText(shelter.getKindCd());
        binding.colorCd.setText(shelter.getColorCd());
        binding.neuterYn.setText(shelter.getNeuterYn());
        binding.specialMark.setText(shelter.getSpecialMark());
        binding.happenDt.setText(shelter.getHappenDt());
        binding.noticeSdt.setText(shelter.getNoticeSdt());
        binding.noticeEdt.setText(shelter.getNoticeEdt());
        binding.happenPlace.setText(shelter.getHappenPlace());
        binding.happenDate.setText(shelter.getHappenDt());
        binding.sexCd.setText(shelter.getSexCd());

        binding.btnCareInfo.setOnClickListener(new View.OnClickListener() {
            TextView careNm, careAddr, careTel;
            @Override
            public void onClick(View view) {
                View dialogview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.careinfo_dialog, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("보호소 정보 상세보기");
                dlg.setView(dialogview);
                careNm = dialogview.findViewById(R.id.careNm);
                careAddr = dialogview.findViewById(R.id.careAddr);
                careTel = dialogview.findViewById(R.id.careTel);
                careNm.setText(shelter.getCareNm());
                careAddr.setText(shelter.getCareAddr());
                careTel.setText(shelter.getCareTel());
                Log.i("전화번호:", shelter.getCareTel());

                dlg.setPositiveButton("통화하기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Uri uri = Uri.parse("tel:"+shelter.getCareTel());
                        Intent intent1 = new Intent(Intent.ACTION_DIAL, uri);
                        view.getContext().startActivity(intent1);
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
    }
}