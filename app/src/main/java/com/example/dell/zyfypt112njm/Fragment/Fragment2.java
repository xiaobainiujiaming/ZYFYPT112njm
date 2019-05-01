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
import com.example.dell.zyfypt112njm.R;
import com.example.dell.zyfypt112njm.Adapter.TwareAdapter;
import com.example.dell.zyfypt112njm.bean.TwareBean;
import com.example.dell.zyfypt112njm.Listener.TwareListener;
import com.example.dell.zyfypt112njm.model.TwareModel;

import java.util.List;

public class Fragment2 extends Fragment{
    //private TextView textfriend;
    private RecyclerView rv1;
    private int page=1;
    private int lastVisibleItemPosition;
    private LinearLayoutManager layoutManager;
    private TwareAdapter twareAdapter;
    List<TwareBean> list;
    TwareListener twareListener=new TwareListener() {
        @Override
        public void onResponse(List<TwareBean> beanList) {
            //list = beanList;
            if(page==1)
            {
                list=beanList;
            }
            else {
                list.removeAll(beanList);
                list.addAll(beanList);
            }
            twareAdapter.setList(list);
            twareAdapter.notifyDataSetChanged();
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
        View view = inflater.inflate(R.layout.fragment2,container,false);
        rv1=(RecyclerView) view.findViewById(R.id.rv1);
        //创建默认的线性布局管理器
        layoutManager=new LinearLayoutManager(getActivity());
        //设置布局管理器
        rv1.setLayoutManager(layoutManager);
        //创建并设置Adapter
        //list=getData();
        twareAdapter=new TwareAdapter(getActivity());
        twareAdapter.setList(list);
        rv1.setAdapter(twareAdapter);

        rv1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&lastVisibleItemPosition + 1 == list.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    TwareModel articleModel = new TwareModel();
                    articleModel.getResultList("tware",page, LoginActivity.sessionID,twareListener);
                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition=layoutManager.findLastVisibleItemPosition();//滚动结束后将赋值为可见条目中最后一条位置
            }
        });

        TwareModel model=new TwareModel();
        model.getResultList("tware",1, LoginActivity.sessionID,  twareListener);
        return view;
    }
}
