package com.example.dell.zyfypt112njm.service;


import com.example.dell.zyfypt112njm.bean.TwareBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TwareService {
    @GET("api.php/lists/mod/{mod}")
    Call<List<TwareBean>> getResult(
            @Path("mod") String mod,
            @Query("page") int page,
            @Header("SessionID") String SessionID
    );
}
