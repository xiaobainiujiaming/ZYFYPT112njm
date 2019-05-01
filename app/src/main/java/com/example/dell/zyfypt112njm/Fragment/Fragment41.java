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
import com.example.dell.zyfypt112njm.Adapter.ProjectAdapter;
import com.example.dell.zyfypt112njm.Listener.ProjectListener;
import com.example.dell.zyfypt112njm.R;
import com.example.dell.zyfypt112njm.bean.ProjectBean;
import com.example.dell.zyfypt112njm.model.ProjectModel;

import java.util.List;

public class Fragment41 extends Fragment {
    public Fragment41(){

    }
    private RecyclerView rv41;
    private int page=1;
    private int lastVisibleItemPosition;
    private LinearLayoutManager layoutManager;
    private ProjectAdapter projectAdapter;
    List<ProjectBean> list;
    ProjectListener projectListener=new ProjectListener() {
        @Override
        public void onResponse(List<ProjectBean> beanList) {
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
            projectAdapter.setList(list);
            projectAdapter.notifyDataSetChanged();
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
        View view =  inflater.inflate(R.layout.fragment41,container,false);
        //System.out.println("(((((((((((((((((((((11");
        rv41=(RecyclerView) view.findViewById(R.id.rv41);
        //创建默认的线性布局管理器
        layoutManager=new LinearLayoutManager(getActivity());
        /*sp = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        readSP();*/
        //设置布局管理器
        rv41.setLayoutManager(layoutManager);
        //创建并设置Adapter
        //list=getData();
        projectAdapter=new ProjectAdapter(getActivity());
        projectAdapter.setList(list);
        rv41.setAdapter(projectAdapter);

        rv41.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&lastVisibleItemPosition + 1 == list.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    ProjectModel projectModel = new ProjectModel();
                   projectModel.getResultList("project",page, LoginActivity.sessionID,projectListener);

                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition=layoutManager.findLastVisibleItemPosition();//滚动结束后将赋值为可见条目中最后一条位置
            }
        });
        ProjectModel model=new ProjectModel();
        model.getResultList("project",page,LoginActivity.sessionID,projectListener);
        return view;
    }
}
