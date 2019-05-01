package com.example.dell.zyfypt112njm.Listener;

import com.example.dell.zyfypt112njm.bean.LoginBean;

public interface LoginListener {
    public void onResponse(LoginBean loginBean);
    public void onFail(String msg);
}
