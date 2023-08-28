package com.findpet.project01.Board.shelter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;


import com.findpet.project01.R;
import com.findpet.project01.databinding.ActivityShelterBoardListBinding;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShelterBoardList extends AppCompatActivity {

    private ActivityShelterBoardListBinding binding;

    String msg;
    final Bundle bundle = new Bundle();

    ShelterAdapter shelterAdapter;

    List<Shelter> shelterList, filteredList;


    int pageNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_board_list);

        binding = ActivityShelterBoardListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        //리사이클러뷰에 레이아웃매니저 연결
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        binding.recylerView.setLayoutManager(linearLayoutManager);

        //리사이클러뷰에 어댑터 연결
        filteredList = new ArrayList<>();
        shelterList = new ArrayList<>();
        shelterAdapter = new ShelterAdapter(shelterList);
        binding.recylerView.setAdapter(shelterAdapter);

        getData();

        binding.edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String searchText = binding.edSearch.getText().toString();
                filteredList.clear();
                if(searchText.equals("")){
                    shelterAdapter.listFilter(shelterList);
                } else {
                    for(int a=0; a<shelterList.size(); a++){
                        Log.i("shelterList 사이즈: ", ""+shelterList.size());
                        Log.i("filteredList 사이즈: ", ""+filteredList.size());
                        if(shelterList.get(a).getBreed().toLowerCase().contains(searchText.toLowerCase())){
                            filteredList.add(shelterList.get(a));
                            //Log.i("filteredList 사이즈: ", ""+filteredList.size());
                        }
//                        else if(filteredList.size()==0 && !searchText.equals("")){
//                            Log.i("filteredList 사이즈: ", ""+filteredList.size());
//                            Toast.makeText(getApplicationContext(), "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
                    }
                    shelterAdapter.listFilter(filteredList);
                }
            }
        });
    }

    private void getData() {
        ShelterJsoup jsoupAsyncTask = new ShelterJsoup();
        jsoupAsyncTask.execute();
    }

    //비동기 웹에서 크롤링
    private class ShelterJsoup extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                for (pageNum = 1; pageNum < 16; pageNum++) {
                    String url = "https://www.animal.go.kr/front/awtis/public/publicList.do?menuNo=1000000055&&page=" + pageNum;
                    //Log.i("현재 주소", url + "");
                    Log.i("페이지 번호", pageNum + "");
                    Document doc = Jsoup.connect(url).get();
                    Elements number = doc.select("div.boardList>ul.list>li>div.txt>dl:eq(0)>dd");
                    Elements regdate = doc.select("div.boardList>ul.list>li>div.txt>dl:eq(1)>dd");
                    Elements breed = doc.select("div.boardList>ul.list>li>div.txt>dl:eq(2)>dd");
                    Elements gender = doc.select("div.boardList>ul.list>li>div.txt>dl:eq(3)>dd");
                    Elements findAddr = doc.select("div.boardList>ul.list>li>div.txt>dl:eq(4)>dd");
                    Elements character = doc.select("div.boardList>ul.list>li>div.txt>dl:eq(5)>dd");
                    Elements status = doc.select("div.boardList>ul.list>li>div.txt>dl:eq(6)>dd");
                    Elements period = doc.select("div.boardList>ul.list>li>div.txt>dl:eq(7)>dd");
                    Elements etc = doc.select("div.boardList>ul.list>li>div.txt>dl:eq(8)>dd");
                    Elements regnumber = doc.select("div.boardList>ul.list>li>div.txt>dl:eq(9)>dd");
                    Elements imageUrl = doc.select("div.boardList>ul.list>li>div.photo>div.thumbnail>a>img");
                    //Elements

                    //페이지 for문 돌 때마다 새로 ArryList 만들어서 뿌리기
                    List<String> numberList = new ArrayList<>();
                    List<String> regdateList = new ArrayList<>();
                    List<String> breedList = new ArrayList<>();
                    List<String> genderList = new ArrayList<>();
                    List<String> findAddrList = new ArrayList<>();
                    List<String> characterList = new ArrayList<>();
                    List<String> statusList = new ArrayList<>();
                    List<String> periodList = new ArrayList<>();
                    List<String> etcList = new ArrayList<>();
                    List<String> regnumberList = new ArrayList<>();
                    List<String> imageUrlList = new ArrayList<>();

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            for (Element element : number) {
                                numberList.add(element.text());
                            }
                            for (Element element : regdate) {
                                regdateList.add(element.text());
                            }
                            for (Element element : breed) {
                                breedList.add(element.text());
                            }
                            for (Element element : gender) {
                                genderList.add(element.text());
                            }
                            for (Element element : findAddr) {
                                findAddrList.add(element.text());
                            }
                            for (Element element : character) {
                                characterList.add(element.text());
                            }
                            for (Element element : status) {
                                statusList.add(element.text());
                            }
                            for (Element element : period) {
                                periodList.add(element.text());
                            }
                            for (Element element : etc) {
                                etcList.add(element.text());
                            }
                            for (Element element : regnumber) {
                                regnumberList.add(element.text());
                            }
                            for (Element element : imageUrl) {
                                imageUrlList.add(element.attr("src"));
                            }

                            for (int i = 0; i < 9; i++) {
                                Shelter data = new Shelter();
                                data.setNumber(numberList.get(i));
                                data.setRegdate(regdateList.get(i));
                                data.setBreed(breedList.get(i));
                                data.setGender(genderList.get(i));
                                data.setFindAddr(findAddrList.get(i));
                                data.setCharacter(characterList.get(i));
                                data.setStatus(statusList.get(i));
                                data.setPeriod(periodList.get(i));
                                data.setEtc(etcList.get(i));
                                data.setRegnumber(regnumberList.get(i));
                                data.setImageUrl(imageUrlList.get(i));
                                shelterAdapter.addItem(data);
                            }

                            //   shelterAdapter.notifyDataSetChanged();
                        }
                    });
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

/*    public void searchFilter(String searchText){
        filteredList.clear();
       if(searchText.equals("")){
            shelterAdapter.listFilter(shelterList);
        } else {
            for(int i=0; i< shelterList.size(); i++){
                if(shelterList.get(i).getBreed().toLowerCase().contains(searchText.toLowerCase())){
                    filteredList.add(shelterList.get(i));
                }
            }
            shelterAdapter.listFilter(filteredList);
            Log.i("리스트 사이즈: ", ""+shelterList.size());
//        }
//
//    }*/
}