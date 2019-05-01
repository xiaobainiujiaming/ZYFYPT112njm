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
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.zyfypt112njm.Activity.LoginActivity;
import com.example.dell.zyfypt112njm.Adapter.CVideoAdapter;
import com.example.dell.zyfypt112njm.Listener.CollectListListener;
import com.example.dell.zyfypt112njm.R;
import com.example.dell.zyfypt112njm.bean.CollectBean;
import com.example.dell.zyfypt112njm.bean.VideoBean;
import com.example.dell.zyfypt112njm.model.CVideoModel;

import java.util.List;

public class CFragment3 extends Fragment{
    private TextView tvinfo;
    private View view=null;
    private Context context;

    private RecyclerView rv32;
    private LinearLayoutManager layoutManager;//显示布局效果
    private CVideoAdapter adapter;//适配器
    private List<CollectBean<VideoBean>> list=null;//数据源


    // private SharedPreferences sp;
    // private String sessionID="";

    private int page=1;// 代表页数，并初始化为1，代表第1页。
    private int lastVisibleItemPosition;//最后一条可见条目的位置

    CollectListListener<VideoBean> listListener=new CollectListListener<VideoBean>() {
        @Override
        public void onResponse(List<CollectBean<VideoBean>> beanlist) {
            if(page==1)
            {
                list=beanlist;
            }
            else {
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
        context=getActivity();
        //动态加载Fragment1的布局文件
        view=inflater.inflate(R.layout.fragment32,container,false);
        //sp=context.getSharedPreferences("login",MODE_PRIVATE);

        // readSP();

        System.out.println("----onCreateView page="+page);

        //实例化ArticleModel，调用方法获取网络数据
        CVideoModel cvideoModel=new CVideoModel();
       cvideoModel.getResultList("video",page, LoginActivity.sessionID,listListener);

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
        rv32=(RecyclerView)view.findViewById(R.id.rv32);
        //创建默认的线性布局
        layoutManager=new LinearLayoutManager(context);
        //设置布局管理器
        rv32.setLayoutManager(layoutManager);
        //固定每个item高度，提高性能
        rv32.setHasFixedSize(true);
        //创建Adaper
        adapter =new CVideoAdapter(context);
        adapter.setList(list);
        //绑定RecyclerView和adapter
        rv32.setAdapter(adapter);
        rv32.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == list.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    CVideoModel cvideoModel=new CVideoModel();
                    cvideoModel.getResultList("video",page,LoginActivity.sessionID,listListener);
                    System.out.println("----onScrollStateChanged  page="+page);
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition=layoutManager.findLastVisibleItemPosition();//滚动结束后将赋值为可见条目中最后一条位置
                //lastVisibleItemPosition = list.size() - 1;
            }
        });

    }
}
