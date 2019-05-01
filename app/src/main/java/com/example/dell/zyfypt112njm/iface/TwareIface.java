package com.example.dell.zyfypt112njm.iface;

import com.example.dell.zyfypt112njm.Listener.TwareListener;

public interface TwareIface {
    void getResultList(String mod,//类型
                       int page,//页数
                       String sessionID,//形参
                       TwareListener listener);
}
