package com.example.dell.zyfypt112njm.Listener;

import com.example.dell.zyfypt112njm.bean.TwareBean;

import java.util.List;

public interface TwareListener {
    //成功
    void onResponse(List<TwareBean> beanList);
    //失败
    void onFail(String msg);
}
