package com.example.dell.zyfypt112njm.iface;

import com.example.dell.zyfypt112njm.Listener.CollectListListener;

public interface CollectListiface<T> {
    void getResultList(String mod,//模块
                       int page,//页数
                       String sessionID,
                       CollectListListener<T> listener
    );
}
