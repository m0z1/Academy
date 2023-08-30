package com.findpet.project01.Board.shelter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.findpet.project01.Board.findBoard.FindBoardList;
import com.findpet.project01.Board.missingBoard.MissyouBoardList;
import com.findpet.project01.Board.storyBoard.StoryBoardList;
import com.findpet.project01.Main;
import com.findpet.project01.R;
import com.findpet.project01.account.Client;
import com.findpet.project01.account.Member;
import com.findpet.project01.account.MemberInfo;
import com.findpet.project01.account.MemberService;
import com.findpet.project01.databinding.ActivityShelterBoardListBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShelterBoardList extends AppCompatActivity{

    private ActivityShelterBoardListBinding binding;


    ShelterAdapter shelterAdapter;

    ArrayList<Shelter> shelterList, filteredList;

    String name = "";


    private int pageNum;
    private String address1
            = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?"
            + "bgnde=20230825"
            + "&endde=20230831"
            + "&pageNo=";
    private String address2
            = "&numOfRows=10"
            + "&serviceKey=aRU%2FbtGFsBQ07Km%2F7ftuRIlm9IS%2FYqOmnj2wlwVBLz7dfog%2BRP8bCSybCocUU8syAD6DqYECTKtsJnFVohSVzw%3D%3D"
            + "&_type=json";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityShelterBoardListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        SharedPreferences sharedPreferences = getSharedPreferences("autoLogin", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        binding.member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShelterBoardList.this, MemberInfo.class);
                startActivity(intent);
            }
        });


        //리사이클러뷰에 레이아웃매니저 연결
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        binding.recylerView.setLayoutManager(linearLayoutManager);

        //리사이클러뷰에 어댑터 연결
        filteredList = new ArrayList<>();
        shelterList = new ArrayList<>();
        shelterAdapter = new ShelterAdapter(shelterList);
        binding.recylerView.setAdapter(shelterAdapter);

        //이미지 -> 홈으로
        ImageView shelterImg = findViewById(R.id.home);
        shelterImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
            }
        });

        // 발견자 게시판으로 가는 버튼
        binding.goTomissing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindBoardList.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        // 실종 주인 게시판으로 가는 버튼
        binding.goTomissyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MissyouBoardList.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        //스토리 게시판으로 가는 버튼
        binding.Story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StoryBoardList.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        //보호소 게시판으로 가는 버튼
        binding.protect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShelterBoardList.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        //품종 검색창
        binding.edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String searchText = binding.edSearch.getText().toString();
                filteredList = new ArrayList<>();
                //filteredList.clear();
                if (searchText.equals("")) {
                    shelterAdapter.listFilter(shelterList);
                    binding.searchresult.setVisibility(View.INVISIBLE);
                } else {
                    for (int iint = 0; iint < shelterList.size(); iint++) {
                        if (shelterList.get(iint).getKindCd().contains(searchText)) {
                            filteredList.add(shelterList.get(iint));
                            binding.searchresult.setVisibility(View.INVISIBLE);
                            Log.i("filteredList 사이즈: ", ""+filteredList.size());
                        }
                        else if(filteredList.size()==0){
                            Log.i("filteredList 사이즈: ", ""+filteredList.size());
                            binding.searchresult.setVisibility(View.VISIBLE);
                            binding.searchresult.setText("검색 결과가 없습니다.");
                            //Toast.makeText(getApplicationContext(), "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
                            //return;
                        }

                    }
                    shelterAdapter.listFilter(filteredList);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
               /* String searchText = binding.edSearch.getText().toString();
                filteredList = new ArrayList<>();
                //filteredList.clear();
                if (searchText.equals("")) {
                    shelterAdapter.listFilter(shelterList);
                } else {
                    for (int i = 0; i < shelterList.size(); i++) {
                        if (shelterList.get(i).getKindCd().contains(searchText)) {
                            filteredList.add(shelterList.get(i));
                            Log.i("filteredList 사이즈: ", ""+filteredList.size());
                        }
                        else if(filteredList.size()==0){
                            Log.i("filteredList 사이즈: ", ""+filteredList.size());
                            Toast.makeText(getApplicationContext(), "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        }
                    shelterAdapter.listFilter(filteredList);
                }*/
            }
        });



        //데이터 받아오기
        new Thread(){
            @Override
            public void run() {
                for(pageNum=1; pageNum<6; pageNum++){
                    List<String> noticeNoList = new ArrayList<>();
                    List<String> happenDtList = new ArrayList<>();
                    List<String> kindCdList = new ArrayList<>();
                    List<String> sexCdList = new ArrayList<>();
                    List<String> happenPlaceList = new ArrayList<>();
                    List<String> specialMarkList = new ArrayList<>();
                    List<String> processStateList = new ArrayList<>();
                    List<String> noticeSdtList = new ArrayList<>();
                    List<String> noticeEdtList = new ArrayList<>();
                    List<String> ImageList = new ArrayList<>();
                    List<String> colorCdList = new ArrayList<>();
                    List<String> ageList = new ArrayList<>();
                    List<String> weightList = new ArrayList<>();
                    List<String> neuterYnList = new ArrayList<>();
                    List<String> careNmList = new ArrayList<>();
                    List<String> careTelList = new ArrayList<>();
                    List<String> careAddrList = new ArrayList<>();
                    List<String> orgNmList = new ArrayList<>();
                    List<String> chargeNmList = new ArrayList<>();
                    List<String> officetelList = new ArrayList<>();


                    //shelterList.clear();
                    //shelterList = new ArrayList<>();
                    String urlAddress = address1+pageNum+address2;

                    try {
                        URL url = new URL(urlAddress);
                        InputStream is = url.openStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader reader = new BufferedReader(isr);
                        StringBuffer buffer = new StringBuffer();
                        String line = reader.readLine();
                        while (line != null){
                            buffer.append(line + "\n");
                            line = reader.readLine();
                        }
                        String jsonData = buffer.toString();

                        JSONObject obj = new JSONObject(jsonData);

                        JSONObject response = (JSONObject)obj.get("response");
                        JSONObject body = (JSONObject)response.get("body");
                        JSONObject items = (JSONObject)body.get("items");
                        JSONArray itemArr = (JSONArray)items.get("item");

                        for(int i=0; i<itemArr.length(); i++) {
                            Log.v("itemArr 사이즈", itemArr.length()+"");
                            JSONObject temp = itemArr.getJSONObject(i);
                            String noticeNo = temp.getString("noticeNo");
                            String happenDt = temp.getString("happenDt");
                            String kindCd = temp.getString("kindCd");
                            String sexCd = temp.getString("sexCd");
                            String happenPlace = temp.getString("happenPlace");
                            String specialMark = temp.getString("specialMark");
                            String processState = temp.getString("processState");
                            String noticeSdt = temp.getString("noticeSdt");
                            String noticeEdt = temp.getString("noticeEdt");
                            //String noticeComment = temp.getString("noticeComment");
                            String image = temp.getString("filename");
                            String colorCd = temp.getString("colorCd");
                            String age = temp.getString("age");
                            String weight = temp.getString("weight");
                            String neuterYn = temp.getString("neuterYn");
                            String careNm = temp.getString("careNm");
                            String careTel = temp.getString("careTel");
                            String careAddr = temp.getString("careAddr");
                            String orgNm = temp.getString("orgNm");
                            String chargeNm = temp.getString("chargeNm");
                            String officetel = temp.getString("officetel");



                            noticeNoList.add(noticeNo);
                            happenDtList.add(happenDt);
                            kindCdList.add(kindCd);
                            sexCdList.add(sexCd);
                            happenPlaceList.add(happenPlace);
                            specialMarkList.add(specialMark);
                            processStateList.add(processState);
                            noticeSdtList.add(noticeSdt);
                            noticeEdtList.add(noticeEdt);
                            ImageList.add(image);
                            colorCdList.add(colorCd);
                            ageList.add(age);
                            weightList.add(weight);
                            neuterYnList.add(neuterYn);
                            careNmList.add(careNm);
                            careTelList.add(careTel);
                            careAddrList.add(careAddr);
                            orgNmList.add(orgNm);
                            chargeNmList.add(chargeNm);
                            officetelList.add(officetel);
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for (int i = 0; i < 9; i++) {
                                    Shelter data = new Shelter();
                                    data.setNoticeNo(noticeNoList.get(i));
                                    data.setHappenDt(happenDtList.get(i));
                                    data.setKindCd(kindCdList.get(i));
                                    data.setSexCd(sexCdList.get(i));
                                    data.setHappenPlace(happenPlaceList.get(i));
                                    data.setSpecialMark(specialMarkList.get(i));
                                    data.setProcessState(processStateList.get(i));
                                    data.setNoticeSdt(noticeSdtList.get(i));
                                    data.setNoticeEdt(noticeEdtList.get(i));
                                    data.setImage(ImageList.get(i));
                                    data.setColorCd(colorCdList.get(i));
                                    data.setAge(ageList.get(i));
                                    data.setWeight(weightList.get(i));
                                    data.setNeuterYn(neuterYnList.get(i));
                                    data.setCareNm(careNmList.get(i));
                                    data.setCareTel(careTelList.get(i));
                                    data.setCareAddr(careAddrList.get(i));
                                    data.setOrgNm(orgNmList.get(i));
                                    data.setChargeNm(chargeNmList.get(i));
                                    data.setOfficetel(officetelList.get(i));

                                    shelterAdapter.addItem(data);
                                    shelterAdapter.notifyDataSetChanged();
                                }
                            }
                        });

                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }.start();

    }


    }



