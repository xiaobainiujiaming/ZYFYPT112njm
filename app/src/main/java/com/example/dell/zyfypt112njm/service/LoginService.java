package com.example.dell.zyfypt112njm.service;

import com.example.dell.zyfypt112njm.bean.LoginBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LoginService {//定义一个登陆接口
    @GET("api.php/login/username/{username}/password/{password}")
    Call<LoginBean> login(@Path("username") String username,
                          @Path("password") String password);
}
