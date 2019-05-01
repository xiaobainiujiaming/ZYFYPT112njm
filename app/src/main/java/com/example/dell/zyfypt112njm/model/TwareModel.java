package com.example.dell.zyfypt112njm.model;


import com.example.dell.zyfypt112njm.bean.TwareBean;
import com.example.dell.zyfypt112njm.iface.TwareIface;
import com.example.dell.zyfypt112njm.Listener.TwareListener;
import com.example.dell.zyfypt112njm.service.TwareService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TwareModel implements TwareIface{

    Retrofit retrofit;
    private static final String BASE_URI=
            "http://amicool.neusoft.edu.cn/";
    public TwareModel(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    @Override
    public void getResultList(String mod, int page, String sessionID, final TwareListener Listener) {
        TwareService twareService=retrofit.create(TwareService.class);
        Call<List<TwareBean>> call=twareService.getResult(mod,page,sessionID);
        System.out.println("---mod="+mod+"page="+page+"SessionId="+sessionID);
        call.enqueue(new Callback<List<TwareBean>>() {
            @Override
            public void onResponse(Call<List<TwareBean>> call, Response<List<TwareBean>> response) {
                Listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<TwareBean>> call, Throwable t) {
                Listener.onFail("error");
                System.out.println("------error"+t.toString());
            }
        });

    }
}
