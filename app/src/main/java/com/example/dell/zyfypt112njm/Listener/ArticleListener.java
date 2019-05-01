package com.example.dell.zyfypt112njm.Listener;

import com.example.dell.zyfypt112njm.bean.ArticleBean;

import java.util.List;

//获取文章的返回  讲作用
public interface ArticleListener {
    //成功
    void onResponse(List<ArticleBean> beanList);
    //失败
    void onFail(String msg);
}