package com.example.dell.zyfypt112njm.model;

import com.example.dell.zyfypt112njm.bean.ArticleBean;
import com.example.dell.zyfypt112njm.iface.ArticleIface;
import com.example.dell.zyfypt112njm.Listener.ArticleListener;
import com.example.dell.zyfypt112njm.service.ArticleService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleModel implements ArticleIface {

    ArticleService articleService;
    Retrofit retrofit;
    private static final String BASE_URI=
            "http://amicool.neusoft.edu.cn/";
    public ArticleModel(){
        retrofit=new Retrofit.Builder().baseUrl(BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    @Override
    public void getResultList(String mod, int page, String sessionID, final ArticleListener listener) {
        ArticleService articleService=retrofit.create(ArticleService.class);
        Call<List<ArticleBean>> call=articleService.getResult(mod,page,sessionID);
        System.out.println("---mod="+mod+"page="+page+"SessionId="+sessionID);
        call.enqueue(new Callback<List<ArticleBean>>() {
            @Override
            public void onResponse(Call<List<ArticleBean>> call, Response<List<ArticleBean>> response) {
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<ArticleBean>> call, Throwable t) {
                listener.onFail("error");
                System.out.println("------error"+t.toString());
            }
        });

    }
}
