package com.example.dell.zyfypt112njm.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dell.zyfypt112njm.Activity.LoginActivity;
import com.example.dell.zyfypt112njm.Adapter.ProjectAdapter;
import com.example.dell.zyfypt112njm.Listener.AttentionListListener;
import com.example.dell.zyfypt112njm.R;
import com.example.dell.zyfypt112njm.bean.ProjectBean;
import com.example.dell.zyfypt112njm.model.AProjectModel;

import java.util.List;

public class AFragment4 extends Fragment{
    private View view = null;
    private Context context;

    private RecyclerView rv41;
    private LinearLayoutManager layoutManager;//显示布局效果
    private ProjectAdapter adapter;//适配器
    private List<ProjectBean> list = null;//数据源
    private String userId;
    // private String sessionID = "";

    private int page = 1;// 代表页数，并初始化为1，代表第1页。
    private int lastVisibleItemPosition;//最后一条可见条目的位置

    AttentionListListener<ProjectBean> listListener = new AttentionListListener<ProjectBean>() {
        @Override
        public void onResponse(List<ProjectBean> beanlist) {
            if (page == 1) {
                list = beanlist;
            } else {
                list.removeAll(beanlist);
                list.addAll(beanlist);
            }
            adapter.setList(list);//传给adapter
            adapter.notifyDataSetChanged();//通知更新
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        //动态加载Fragment1的布局文件
        view = inflater.inflate(R.layout.fragment41, container, false);
        userId = getActivity().getIntent().getStringExtra("userId");
        //readSP();

        System.out.println("----onCreateView page=" + page);

        //实例化ArticleModel，调用方法获取网络数据
        AProjectModel aProjectModel = new AProjectModel();
        aProjectModel.getResultList("project", page,  LoginActivity.sessionID, userId,listListener);

        initRecyclerView();
        //返回动态生成的view
        return view;
    }

    //  private void readSP() {
    //  sessionID=sp.getString("sessionID",null);
//
    // }

    private void initRecyclerView() {
        //获取RecyclerView，设置属性，获取数据源，绑定
        rv41=(RecyclerView)view.findViewById(R.id.rv41);
        //创建默认的线性布局
        layoutManager=new LinearLayoutManager(context);
        //设置布局管理器
        rv41.setLayoutManager(layoutManager);
        //固定每个item高度，提高性能
        rv41.setHasFixedSize(true);
        //创建Adaper
        adapter =new ProjectAdapter(context);
        adapter.setList(list);
        //绑定RecyclerView和adapter
        rv41.setAdapter(adapter);
        rv41.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == list.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    AProjectModel aProjectModel = new AProjectModel();
                    aProjectModel.getResultList("project", page,  LoginActivity.sessionID, userId, listListener);
                    System.out.println("----onScrollStateChanged  page=" + page);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();//滚动结束后将赋值为可见条目中最后一条位置
                //lastVisibleItemPosition = list.size() - 1;
            }
        });

    }
}
