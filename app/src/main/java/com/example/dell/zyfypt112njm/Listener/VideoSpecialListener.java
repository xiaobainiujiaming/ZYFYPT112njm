package com.example.dell.zyfypt112njm.Listener;

import com.example.dell.zyfypt112njm.bean.VideoSpecialBean;
import java.util.List;

public interface VideoSpecialListener {
    //成功
    void onResponse(List<VideoSpecialBean> beanList);
    //失败
    void onFail(String msg);
}
