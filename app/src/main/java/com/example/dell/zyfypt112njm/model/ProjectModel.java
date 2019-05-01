package com.example.dell.zyfypt112njm.model;

import com.example.dell.zyfypt112njm.Listener.ProjectListener;
import com.example.dell.zyfypt112njm.bean.ProjectBean;
import com.example.dell.zyfypt112njm.iface.ProjectIface;
import com.example.dell.zyfypt112njm.service.ProjectService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectModel implements ProjectIface {
    Retrofit retrofit;
    private static final String BASE_URI=
            "http://amicool.neusoft.edu.cn/";
    public ProjectModel(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    @Override
    public void getResultList(String mod, int page, String sessionID, final ProjectListener Listener) {
        ProjectService projectService=retrofit.create( ProjectService.class);
        Call<List<ProjectBean>> call= projectService.getResult(mod,page,sessionID);
        System.out.println("---mod="+mod+"page="+page+"SessionId="+sessionID);
        call.enqueue(new Callback<List< ProjectBean>>() {
            @Override
            public void onResponse(Call<List< ProjectBean>> call, Response<List< ProjectBean>> response) {
                Listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List< ProjectBean>> call, Throwable t) {
                Listener.onFail("error");
                System.out.println("------error"+t.toString());
            }
        });

    }
}
