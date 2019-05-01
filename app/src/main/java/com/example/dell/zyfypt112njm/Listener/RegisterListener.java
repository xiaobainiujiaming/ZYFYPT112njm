package com.example.dell.zyfypt112njm.Listener;

public interface RegisterListener {
    //成功返回信息
    void onResponse(String registermgs);
    //失败返回错误字符串
    void onFail(String mgs);
}
