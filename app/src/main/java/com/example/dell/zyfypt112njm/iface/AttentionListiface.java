package com.example.dell.zyfypt112njm.iface;

import com.example.dell.zyfypt112njm.Listener.AttentionListListener;


public interface AttentionListiface<T> {
    void getResultList(String mod,//模块
                       int page,//页数
                       String sessionID,
                       int userid,
                       AttentionListListener<T> listener

    );
}
