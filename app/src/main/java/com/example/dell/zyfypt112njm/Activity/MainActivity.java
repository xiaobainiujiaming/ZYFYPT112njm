package com.example.dell.zyfypt112njm.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dell.zyfypt112njm.Fragment.Fragment1;
import com.example.dell.zyfypt112njm.Fragment.Fragment2;
import com.example.dell.zyfypt112njm.Fragment.Fragment3;
import com.example.dell.zyfypt112njm.Fragment.Fragment4;
import com.example.dell.zyfypt112njm.Fragment.Fragment5;
import com.example.dell.zyfypt112njm.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LinearLayout layout1,layout2,layout3,layout4,layout5;
    private ImageView img1,img2,img3,img4,img5;
    private ViewPager viewPager;

    List<Fragment> list;//Fragment list用于放置四个fragment
    FragmentPagerAdapter fragmentPagerAdapter;//用来呈现Fragment页面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();//界面获取调用
        initFragment();
        layout1.setOnClickListener(onClickListener);
        layout2.setOnClickListener(onClickListener);
        layout3.setOnClickListener(onClickListener);
        layout4.setOnClickListener(onClickListener);
        layout5.setOnClickListener(onClickListener);
    }
    private void  initFragment(){
        list=new ArrayList<Fragment>();//创建list
        list.add(new Fragment1());//添加Fragment1
        list.add(new Fragment2());//添加Fragment2
        list.add(new Fragment3());//添加Fragment3
        list.add(new Fragment4());//添加Fragment4
        list.add(new Fragment5());//添加Fragment5
        //创建配置器
        fragmentPagerAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        };
        //滑动
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                viewPager.setCurrentItem(i);
                resetImage();
                selctTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }
    private void init(){
        layout1=(LinearLayout) findViewById(R.id.layout1);
        layout2=(LinearLayout) findViewById(R.id.layout2);
        layout3=(LinearLayout) findViewById(R.id.layout3);
        layout4=(LinearLayout) findViewById(R.id.layout4);
        layout5=(LinearLayout) findViewById(R.id.layout5);
        img1=(ImageView)findViewById(R.id.imageView);
        img2=(ImageView)findViewById(R.id.imageView2);
        img3=(ImageView)findViewById(R.id.imageView3);
        img4=(ImageView)findViewById(R.id.imageView4);
        img5=(ImageView) findViewById(R.id.imageView5);
        viewPager=(ViewPager)findViewById(R.id.vp);
    }
    //在跳转前现将4个按钮设置为未选中状态
    private void resetImage(){
        img1.setImageResource(R.drawable.weixin_normal);
        img2.setImageResource(R.drawable.friend_normal);
        img3.setImageResource(R.drawable.address_normal);
        img4.setImageResource(R.drawable.settings_normal);
        img5.setImageResource(R.drawable.settings_normall);
    }
    //选中状态
    private void selctTab(int i){
        switch (i){
            case 0:img1.setImageResource(R.drawable.weixin_pressed);
            break;
            case 1: img2.setImageResource(R.drawable.friend_pressed);
            break;
            case 2: img3.setImageResource(R.drawable.address_pressed);
            break;
            case 3:img4.setImageResource(R.drawable.settings_pressed);
            break;
            case 4:img5.setImageResource(R.drawable.settings_pressedl);
            break;

        }
        viewPager.setCurrentItem(i);
    }
    //内部类，监听器  点击方式
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            resetImage();
            switch (v.getId()){
                case R.id.layout1:
                    selctTab(0);
                    break;
                case R.id.layout2:
                    selctTab(1);
                    break;
                case R.id.layout3:
                    selctTab(2);
                    break;
                case R.id.layout4:
                    selctTab(3);
                    break;
                case R.id.layout5:
                    selctTab(4);
                    break;
            }
        }
    };

}
