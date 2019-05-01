package com.example.dell.zyfypt112njm.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.zyfypt112njm.R;

public class Fragment3 extends Fragment{
    private View view=null;
    private Context context;
    private PagerSlidingTabStrip pagersliding;
    private ViewPager viewpager;
    private Fragment31 fragment31=null;
    private Fragment32 fragment32=null;
    //获取当前屏幕的密度
    private DisplayMetrics dm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context=getActivity();
        //动态加载Fragment3的布局文件
        view=inflater.inflate(R.layout.fragment3,container,false);
        init();
        //返回动态生成的view
        return view;
    }
    private void init() {
        dm = getResources().getDisplayMetrics();//获取屏幕密度
        viewpager=(ViewPager)view.findViewById(R.id.viewPager);
        viewpager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        pagersliding=(PagerSlidingTabStrip) view.findViewById(R.id.pagerslidingtabstrip);
        pagersliding.setViewPager(viewpager);
        setpagerstyle();//设置PagerSlidingTabStrip显示效果
    }


    private void setpagerstyle() {
        pagersliding.setShouldExpand(true); // 设置Tab是自动填充满屏幕的
        pagersliding.setDividerColor(Color.TRANSPARENT); // 设置Tab的分割线是透明的
        // 设置Tab底部线的高度
        pagersliding.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        // 设置Tab Indicator的高度
        pagersliding.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, dm));
        // 设置Tab标题文字的大小
        pagersliding.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, dm));
        pagersliding.setIndicatorColor(Color.parseColor("#45c01a"));// 设置Tab Indicator的颜色
        // 设置选中Tab文字的颜色 (这是自定义的一个方法)
        pagersliding.setSelectedTextColor(Color.parseColor("#45c01a"));
        pagersliding.setTabBackground(0); // 取消点击Tab时的背景色
    }
    //自定义ViewPagerAdapter子类
    private class MyPagerAdapter extends FragmentPagerAdapter {
        private String[] titles={"最新","专题"};//显示在二级导航上的标题文字
        public MyPagerAdapter(FragmentManager fm)    {  super(fm);    }
        @Override
        public CharSequence getPageTitle(int position) {return titles[position]; }
        @Override
        public int getCount() {  return titles.length;  }
        @Override
        public Fragment getItem(int position) { //根据位置返回具体某个导航上对应的Fragment
            switch (position)
            {   case 0:
                if(fragment31==null)  {  fragment31=new Fragment31();}
                return fragment31;
                case 1: if(fragment32==null) {  fragment32=new Fragment32();  }
                    return fragment32;
                default: return null;
            }
        }
    }
}
