package com.example.dell.zyfypt112njm.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.dell.zyfypt112njm.Adapter.UserAdapter;
import com.example.dell.zyfypt112njm.Listener.AttentionListListener;
import com.example.dell.zyfypt112njm.R;
import com.example.dell.zyfypt112njm.bean.FocusResultBean;
import com.example.dell.zyfypt112njm.bean.UserBean;
import com.example.dell.zyfypt112njm.model.AttentionListModel;

import java.util.ArrayList;
import java.util.List;


public class AttentActivity extends AppCompatActivity {

    private static final String TAG = "AttentActivity";
    private RecyclerView rv;
    private int page = 1;
    private UserAdapter adapter;
    private List<FocusResultBean<UserBean>> list = new ArrayList<>();
    private Context context;
    AttentionListListener<FocusResultBean<UserBean>> attentListener = new AttentionListListener<FocusResultBean<UserBean>>() {
        @Override
        public void onResponse(List<FocusResultBean<UserBean>> beanlist) {

            if (beanlist == null) {
                return;
            } else {

                list.removeAll(beanlist);
                list.addAll(beanlist);
            }
            adapter.setList(list);
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(context, "获取关注列表失败", Toast.LENGTH_SHORT).show();
            System.out.println("+++++++++++++++"+msg);
        }
    };
   // private String sessionID;
    private int lastVisibleItemPosition;//最后一条可见条目的位置
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment1);
       // readSP();
        context = this;
        rv = findViewById(R.id.rv);
        layoutManager = new LinearLayoutManager(context);
        rv.setLayoutManager(layoutManager);
        adapter = new UserAdapter(context);
        rv.setAdapter(adapter);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == list.size()) {
                    page += 1;
                    //再次实例化ArticleModel，调用方法获取网络数据，请求新一页数据
                    AttentionListModel attentionListModel = new AttentionListModel();
                    attentionListModel.getResultList("user", page,LoginActivity.sessionID, attentListener);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();//滚动结束后将赋值为可见条目中最后一条位置
            }
        });
        AttentionListModel attentionListModel = new AttentionListModel();
        attentionListModel.getResultList("user", 1,  LoginActivity.sessionID, attentListener);
    }
    //    private void readSP() {
//        SessionID=sp.getString("sessionID",null);
//    }
}
