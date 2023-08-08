package com.example.myapp09;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;



public class MainActivity extends AppCompatActivity implements ActionBar.TabListener{
    ActionBar.Tab tabsong, tabArtist , tabAlbum;

    MyTabFragment myfrag[] = new MyTabFragment[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        tabsong = bar.newTab();
        tabsong.setText("음악별");
        tabsong.setTabListener(this);
        bar.addTab(tabsong);

        tabArtist = bar.newTab();
        tabsong.setText("가수별");
        tabsong.setTabListener(this);
        bar.addTab(tabArtist);

        tabAlbum = bar.newTab();
        tabsong.setText("앨범별");
        tabsong.setTabListener(this);
        bar.addTab(tabAlbum);

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        MyTabFragment myTabFragment = null;
        if(myfrag[tab.getPosition()] == null){
          myTabFragment = new MyTabFragment();
            Bundle data = new Bundle();
            data.putString("tabName",tab.getText().toString());

            myTabFragment.setArguments(data);
            myfrag[tab.getPosition()] = myTabFragment;

        }else {
            myTabFragment = myfrag[tab.getPosition()];
        }
        ft.replace(android.R.id.content,myTabFragment);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
   public class MyTabFragment extends Fragment {
        String tabName;
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle data = getArguments();
            tabName = data.getString("tabName");
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );

            LinearLayout baseLayout = new LinearLayout(super.getActivity());
            baseLayout.setOrientation(LinearLayout.VERTICAL);
            baseLayout.setLayoutParams(params);
            if(tabName == "음악별"){
                baseLayout.setBackgroundColor(Color.RED);
            }
            if(tabName == "가수별"){
                baseLayout.setBackgroundColor(Color.GREEN);
            }
            if(tabName == "앨범별"){
                baseLayout.setBackgroundColor(Color.BLUE);
            }
            return super.onCreateView(inflater, container, savedInstanceState);

        }


    }



}