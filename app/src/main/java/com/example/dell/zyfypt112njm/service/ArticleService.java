package com.example.dell.zyfypt112njm.service;
//http://amicool.neusoft.edu.cn/api.php/login/username/jianing/password/123456
import com.example.dell.zyfypt112njm.bean.ArticleBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ArticleService {
    @GET("api.php/lists/mod/{mod}")
    Call<List<ArticleBean>> getResult(
            @Path("mod") String mod,
            @Query("page") int page,
            @Header("SessionID") String SessionID
    );
}




