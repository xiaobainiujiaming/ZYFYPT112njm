package com.example.dell.zyfypt112njm.model;

import com.example.dell.zyfypt112njm.bean.LoginBean;
import com.example.dell.zyfypt112njm.iface.LoginIface;
import com.example.dell.zyfypt112njm.Listener.LoginListener;
import com.example.dell.zyfypt112njm.service.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginModel implements LoginIface{
    //1、创建retrofit对象
    private Retrofit retrofit;
    private LoginService loginService;
    private static final String BASE_URL="http://amicool.neusoft.edu.cn/";//静态常量
    //构造函数
    public LoginModel(){//使用retrofit：1
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//添加转换工厂
                .build();
    }

    @Override
    public void getLogin(String username, String password, final LoginListener loginListener) {
        //2、创建访问API的请求   使用retrofit：2
        loginService=retrofit.create(LoginService.class);
        Call<LoginBean> call=loginService.login(username, password);
        //3、发送请求  使用retrofit：3
        call.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {//有响应处理
                //4、处理结果   使用retrofit：4
                if(response.isSuccessful() && response.body()!=null){
                    loginListener.onResponse(response.body());
                }
                else {
                    loginListener.onFail("fail");
                }
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {//失败处理
                loginListener.onFail(t.toString());
            }
        });
    }
}
