package com.example.dell.zyfypt112njm.service;
//api.php/reg/username/zhengjunsheng/password/123456/tel/13889618807/roleid/2/email/1222@qq.com

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {



    @GET("api.php/reg/username/{username}/password/{password}/tel/{tel}")
    Call<String> register(@Path("username") String username,
                          @Path("password") String password,
                          @Path("tel") String tel);
}
