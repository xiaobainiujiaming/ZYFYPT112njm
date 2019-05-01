package com.example.dell.zyfypt112njm.Listener;

import com.example.dell.zyfypt112njm.bean.ProjectBean;

import java.util.List;

public interface ProjectListener {
    //成功
    void onResponse(List<ProjectBean> beanList);
    //失败
    void onFail(String msg);
}
