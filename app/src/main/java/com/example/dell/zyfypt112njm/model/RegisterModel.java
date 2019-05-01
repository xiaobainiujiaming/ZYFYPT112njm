package com.example.dell.zyfypt112njm.model;

import com.example.dell.zyfypt112njm.Listener.RegisterListener;
import com.example.dell.zyfypt112njm.iface.Registeriface;
import com.example.dell.zyfypt112njm.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegisterModel implements Registeriface {
    private Retrofit retrofit;
    private String BASEURL="http://amicool.neusoft.edu.cn/";
    //构造函数
    public RegisterModel(){
        //使用Retrofit------1
        retrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
    }
    @Override
    public void getRegisterResult(String username, String password, String tel,
                                  final RegisterListener registerListener) {
        //使用Retrofit------2
        UserService userService=retrofit.create(UserService.class);
        Call<String> call=userService.register(username, password, tel);
        //使用Retrofit------3
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response!=null)
                {
                    registerListener.onResponse(response.body());
                }
                else
                {
                    registerListener.onFail("onresponse fail");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                registerListener.onFail(t.toString());
            }
        });

    }
}
