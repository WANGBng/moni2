package com.bwie.wang.mvp.view.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bwie.wang.mvp.view.R;
import com.bwie.wang.mvp.view.fragment.FragmentHome_Page;
import com.bwie.wang.mvp.view.fragment.FragmentMine;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioGroup group;
    private RadioButton home_page,mine;
    private ViewPager viewPager;
    private List<Fragment> fList;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        group = findViewById(R.id.group);
        home_page = findViewById(R.id.home_page);
        mine = findViewById(R.id.mine);
        viewPager = findViewById(R.id.vp);

        setMainListener();
    }

    private void setMainListener() {
        fList = new ArrayList<>();
        fList.add(new FragmentHome_Page());
        fList.add(new FragmentMine());
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fList.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fList.get(position);
            }
        });

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.home_page:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.mine:
                        viewPager.setCurrentItem(1);
                        break;
                }
            }
        });
    }
}
