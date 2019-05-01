package com.example.dell.zyfypt112njm.Listener;

import com.example.dell.zyfypt112njm.bean.VideoBean;
import java.util.List;

public interface VideoListener {
    //成功
    void onResponse(List<VideoBean> beanList);
    //失败
    void onFail(String msg);
}
