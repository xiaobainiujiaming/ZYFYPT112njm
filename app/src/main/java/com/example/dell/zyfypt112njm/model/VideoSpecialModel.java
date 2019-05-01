package com.example.dell.zyfypt112njm.model;

import com.example.dell.zyfypt112njm.Listener.VideoSpecialListener;
import com.example.dell.zyfypt112njm.bean.VideoSpecialBean;
import com.example.dell.zyfypt112njm.iface.VideoSpecialIface;
import com.example.dell.zyfypt112njm.service.VideoSpecialService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoSpecialModel implements VideoSpecialIface{

    Retrofit retrofit;
    private static final String BASE_URI=
            "http://amicool.neusoft.edu.cn/";
    public VideoSpecialModel(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    @Override
    public void getResultList(String mod, int page, String sessionID, final VideoSpecialListener Listener) {
        VideoSpecialService videoSpecialService=retrofit.create(VideoSpecialService.class);
        Call<List<VideoSpecialBean>> call=videoSpecialService.getResult(mod,page,sessionID);
        System.out.println("---mod="+mod+"page="+page+"SessionId="+sessionID);
        call.enqueue(new Callback<List<VideoSpecialBean>>() {
            @Override
            public void onResponse(Call<List<VideoSpecialBean>> call, Response<List<VideoSpecialBean>> response) {
                Listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<VideoSpecialBean>> call, Throwable t) {
                Listener.onFail("error");
                System.out.println("------error"+t.toString());
            }
        });

    }

}
