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
import com.example.dell.zyfypt112njm.Adapter.TcaseAdapter;
import com.example.dell.zyfypt112njm.Listener.TcaseListener;
import com.example.dell.zyfypt112njm.R;
import com.example.dell.zyfypt112njm.bean.TcaseBean;
import com.example.dell.zyfypt112njm.model.TcaseModel;

import java.util.List;

public class Fragment42 extends Fragment {
    public Fragment42(){

    }
    private RecyclerView rv42;
    private int page=1;
    private int lastVisibleItemPosition;
    private LinearLayoutManager layoutManager;
    private TcaseAdapter tcaseAdapter;
    List<TcaseBean> list;
    TcaseListener tcaseListener=new TcaseListener() {
        @Override
        public void onResponse(List<TcaseBean> beanList) {
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
            tcaseAdapter.setList(list);
            tcaseAdapter.notifyDataSetChanged();
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
        View view =  inflater.inflate(R.layout.fragment42,container,false);
        //System.out.println("(((((((((((((((((((((11");
        rv42=(RecyclerView) view.findViewById(R.id.rv42);
        //创建默认的线性布局管理器
        layoutManager=new LinearLayoutManager(getActivity());
        /*sp = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        readSP();*/
        //设置布局管理器
        rv42.setLayoutManager(layoutManager);
        //创建并设置Adapter
        //list=getData();
        tcaseAdapter=new TcaseAdapter(getActivity());
        tcaseAdapter.setList(list);
        rv42.setAdapter(tcaseAdapter);

        rv42.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&lastVisibleItemPosition + 1 == list.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    TcaseModel tcaseModel = new TcaseModel();
                    tcaseModel.getResultList("tcase",page, LoginActivity.sessionID, tcaseListener);

                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition=layoutManager.findLastVisibleItemPosition();//滚动结束后将赋值为可见条目中最后一条位置
            }
        });
        TcaseModel model=new TcaseModel();
        model.getResultList("tcase",page,LoginActivity.sessionID,tcaseListener);
        return view;
    }
}
