package com.example.dell.zyfypt112njm.model;

import com.example.dell.zyfypt112njm.Listener.VideoListener;

import com.example.dell.zyfypt112njm.bean.VideoBean;
import com.example.dell.zyfypt112njm.iface.VideoIface;
import com.example.dell.zyfypt112njm.service.VideoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoModel implements VideoIface{

    Retrofit retrofit;
    private static final String BASE_URI=
            "http://amicool.neusoft.edu.cn/";
    public  VideoModel(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    @Override
    public void getResultList(String mod, int page, String sessionID, final VideoListener Listener) {
        VideoService  videoService=retrofit.create( VideoService.class);
        Call<List<VideoBean>> call=videoService.getResult(mod,page,sessionID);
        System.out.println("---mod="+mod+"page="+page+"SessionId="+sessionID);
        call.enqueue(new Callback<List< VideoBean>>() {
            @Override
            public void onResponse(Call<List< VideoBean>> call, Response<List< VideoBean>> response) {
                Listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List< VideoBean>> call, Throwable t) {
                Listener.onFail("error");
                System.out.println("------error"+t.toString());
            }
        });

    }
}
