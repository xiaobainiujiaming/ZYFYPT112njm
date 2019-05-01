package com.example.dell.zyfypt112njm.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dell.zyfypt112njm.Activity.LoginActivity;
import com.example.dell.zyfypt112njm.Adapter.VideoSpecialAdapter;
import com.example.dell.zyfypt112njm.Listener.VideoSpecialListener;
import com.example.dell.zyfypt112njm.R;
import com.example.dell.zyfypt112njm.bean.VideoSpecialBean;
import com.example.dell.zyfypt112njm.model.VideoSpecialModel;

import java.util.List;


public class Fragment32 extends Fragment {
    public Fragment32(){

    }
    private RecyclerView rv32;
    private int page=1;
    private int lastVisibleItemPosition;
    private LinearLayoutManager layoutManager;
    private VideoSpecialAdapter videoSpecialAdapter;
    List<VideoSpecialBean> list;
    VideoSpecialListener videoSpecialListener=new VideoSpecialListener() {
        @Override
        public void onResponse(List<VideoSpecialBean> beanList) {
            //list = beanList;
            if(page==1)
            {
                list=beanList;
            }
            else {
                list.removeAll(beanList);
                list.addAll(beanList);
            }
            videoSpecialAdapter.setList(list);
            videoSpecialAdapter.notifyDataSetChanged();
        }

        @Override
        public void onFail(String msg) {
            // Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment32,container,false);
      //  System.out.println("((((((((((((((((((((((((((((222222");
        rv32=(RecyclerView) view.findViewById(R.id.rv32);
        //创建默认的线性布局管理器
        layoutManager=new LinearLayoutManager(getActivity());
       // sp = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        //readSP();
        //设置布局管理器
        rv32.setLayoutManager(layoutManager);
        //创建并设置Adapter
        //list=getData();
        videoSpecialAdapter=new VideoSpecialAdapter(getActivity());
        videoSpecialAdapter.setList(list);
        rv32.setAdapter(videoSpecialAdapter);

        rv32.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&lastVisibleItemPosition + 1 == list.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    VideoSpecialModel videoSpecialModel = new VideoSpecialModel();
                    videoSpecialModel.getResultList("video",page, LoginActivity.sessionID,videoSpecialListener);
                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition=layoutManager.findLastVisibleItemPosition();//滚动结束后将赋值为可见条目中最后一条位置
            }
        });
        VideoSpecialModel model=new VideoSpecialModel();
        model.getResultList("video",page,LoginActivity.sessionID,videoSpecialListener);
        return view;

    }
}
