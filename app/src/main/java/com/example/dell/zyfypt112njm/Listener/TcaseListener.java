package com.example.dell.zyfypt112njm.Listener;

import com.example.dell.zyfypt112njm.bean.TcaseBean;

import java.util.List;

public interface TcaseListener {
    //成功
    void onResponse(List<TcaseBean> beanList);
    //失败
    void onFail(String msg);
}
