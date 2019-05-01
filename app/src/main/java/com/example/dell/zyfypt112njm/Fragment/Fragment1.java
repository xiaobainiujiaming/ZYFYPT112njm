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
import com.example.dell.zyfypt112njm.Adapter.MyAdapter;
import com.example.dell.zyfypt112njm.R;
import com.example.dell.zyfypt112njm.bean.ArticleBean;
import com.example.dell.zyfypt112njm.Listener.ArticleListener;
import com.example.dell.zyfypt112njm.model.ArticleModel;

import java.util.List;

public class Fragment1 extends Fragment{
   // private TextView tvinfo;
    private RecyclerView rv;
    private int page=1;
    private int lastVisibleItemPosition;
    private LinearLayoutManager layoutManager;
    private MyAdapter articleAdapter;
    List<ArticleBean> list;
   /* private  String SessionID,username,password;
    private SharedPreferences sp;*/
    ArticleListener articleListener=new ArticleListener() {
        @Override
        public void onResponse(List<ArticleBean> beanList) {
            if(page==1)
            {
                list=beanList;
            }
            else {
                list.removeAll(beanList);
                list.addAll(beanList);
            }
            articleAdapter.setList(list);//传给adapter
            articleAdapter.notifyDataSetChanged();//通知更新
        }

        @Override
        public void onFail(String msg) {
            //Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container,false);

        rv=(RecyclerView) view.findViewById(R.id.rv);
        //创建默认的线性布局管理器
        layoutManager=new LinearLayoutManager(getActivity());
        /*sp = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        readSP();*/
        //设置布局管理器
        rv.setLayoutManager(layoutManager);
        //创建并设置Adapter
        //list=getData();
        articleAdapter=new MyAdapter(getActivity());
        articleAdapter.setList(list);
        rv.setAdapter(articleAdapter);

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&lastVisibleItemPosition + 1 == list.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    ArticleModel articleModel = new ArticleModel();
                    articleModel.getResultList("article",page, LoginActivity.sessionID,articleListener);
                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition=layoutManager.findLastVisibleItemPosition();//滚动结束后将赋值为可见条目中最后一条位置
            }
        });
        ArticleModel model=new ArticleModel();
        model.getResultList("article",page,LoginActivity.sessionID,articleListener);
        return view;
    }

   /* private void readSP(){
        username=sp.getString("name",null);
        password=sp.getString("pass",null);
        SessionID=sp.getString("sessionID",false);

        }*/


}
