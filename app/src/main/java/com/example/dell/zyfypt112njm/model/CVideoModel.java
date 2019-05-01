package com.example.dell.zyfypt112njm.model;

import com.example.dell.zyfypt112njm.Listener.CollectListListener;
import com.example.dell.zyfypt112njm.bean.CollectBean;
import com.example.dell.zyfypt112njm.bean.VideoBean;
import com.example.dell.zyfypt112njm.iface.CollectListiface;
import com.example.dell.zyfypt112njm.service.CollectListService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CVideoModel implements CollectListiface<VideoBean>{
    private Retrofit retrofit;
    private String BASEURL
            ="http://amicool.neusoft.edu.cn/";
    //构造函数
    public CVideoModel()
    {   //使用Retrofit----1
        retrofit=new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    @Override
    public void getResultList(String mod, int page, String sessionID, final CollectListListener<VideoBean> listener) {
        //使用Retrofit----2
        CollectListService service
                =retrofit.create(CollectListService.class);
        Call<List<CollectBean<VideoBean>>> call
                =service.getCVideoList(mod,page,sessionID);
        //使用Retrofit----3
        call.enqueue(new Callback<List<CollectBean<VideoBean>>>() {
            @Override
            public void onResponse(Call<List<CollectBean<VideoBean>>> call, Response<List<CollectBean<VideoBean>>> response) {
                if(response.isSuccessful() && response!=null)
                {  listener.onResponse(response.body());
                }
                else
                {   listener.onFail("onresponse fail");

                }
            }
            @Override
            public void onFailure(Call<List<CollectBean<VideoBean>>> call, Throwable t) {
                listener.onFail(t.toString());
            }
        });
    }
}
