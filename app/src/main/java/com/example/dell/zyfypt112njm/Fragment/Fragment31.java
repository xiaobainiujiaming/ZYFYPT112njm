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
import com.example.dell.zyfypt112njm.Adapter.VideoAdapter;
import com.example.dell.zyfypt112njm.Listener.VideoListener;
import com.example.dell.zyfypt112njm.R;
import com.example.dell.zyfypt112njm.bean.VideoBean;
import com.example.dell.zyfypt112njm.model.VideoModel;

import java.util.List;

public class Fragment31 extends Fragment {
    public Fragment31(){

    }

    private RecyclerView rv31;
    private int page=1;
    private int lastVisibleItemPosition;
    private LinearLayoutManager layoutManager;
    private VideoAdapter videoAdapter;
    List<VideoBean> list;
    VideoListener videoListener=new VideoListener() {
        @Override
        public void onResponse(List<VideoBean> beanList) {
            //list = beanList;
            if(page==1)
            {
               // System.out.println("(((((((((((((((((((((((((((((1111111");
                list=beanList;
            }
            else {
                list.removeAll(beanList);
                list.addAll(beanList);
            }
            videoAdapter.setList(list);
            videoAdapter.notifyDataSetChanged();
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
        View view =  inflater.inflate(R.layout.fragment31,container,false);
        //System.out.println("(((((((((((((((((((((11");
        rv31=(RecyclerView) view.findViewById(R.id.rv31);
        //创建默认的线性布局管理器
        layoutManager=new LinearLayoutManager(getActivity());
        /*sp = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        readSP();*/
        //设置布局管理器
        rv31.setLayoutManager(layoutManager);
        //创建并设置Adapter
        //list=getData();
        videoAdapter=new VideoAdapter(getActivity());
        videoAdapter.setList(list);
        rv31.setAdapter(videoAdapter);

        rv31.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&lastVisibleItemPosition + 1 == list.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    VideoModel videoModel = new VideoModel();
                    videoModel.getResultList("video",page, LoginActivity.sessionID,videoListener);

                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition=layoutManager.findLastVisibleItemPosition();//滚动结束后将赋值为可见条目中最后一条位置
            }
        });
        VideoModel model=new VideoModel();
        model.getResultList("video",page,LoginActivity.sessionID,videoListener);
        return view;
    }
}
