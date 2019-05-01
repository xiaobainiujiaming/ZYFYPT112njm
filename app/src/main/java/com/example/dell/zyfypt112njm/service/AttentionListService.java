package com.example.dell.zyfypt112njm.service;

import com.example.dell.zyfypt112njm.bean.ArticleBean;
import com.example.dell.zyfypt112njm.bean.FocusResultBean;
import com.example.dell.zyfypt112njm.bean.ProjectBean;
import com.example.dell.zyfypt112njm.bean.TcaseBean;
import com.example.dell.zyfypt112njm.bean.TwareBean;
import com.example.dell.zyfypt112njm.bean.UserBean;
import com.example.dell.zyfypt112njm.bean.VideoBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AttentionListService {
    @GET("api.php/lists/mod/{mod}")
    Call<List<ArticleBean>> getArticleList(@Path("mod")String mod, @Query("page") int page, @Header("SessionID") String sessionID, @Query("userid") String userid);

    @GET("api.php/lists/mod/{mod}")
    Call<List<ProjectBean>> getProjectList(@Path("mod")String mod, @Query("page") int page, @Header("SessionID") String sessionID, @Query("userid") String userid);

    @GET("api.php/lists/mod/{mod}")
    Call<List<TcaseBean>> getTcaseList(@Path("mod")String mod, @Query("page") int page, @Header("SessionID") String sessionID, @Query("userid") String userid);

    @GET("api.php/lists/mod/{mod}")
    Call<List<TwareBean>> getTwareList(@Path("mod")String mod, @Query("page") int page, @Header("SessionID") String sessionID, @Query("userid") String userid);

    @GET("api.php/lists/mod/{mod}")
    Call<List<VideoBean>> getVideoList(@Path("mod")String mod, @Query("page") int page, @Header("SessionID") String sessionID, @Query("userid") String userid);

    @GET("api.php/listmyfocus/mod/{mod}focus")
    Call<List<FocusResultBean<UserBean>>> getFocusUserList(@Path("mod") String mod, @Query("page") int page, @Header("SessionID") String sessionID);

}
