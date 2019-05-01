package com.example.dell.zyfypt112njm.iface;

import com.example.dell.zyfypt112njm.Listener.TcaseListener;

public interface TcaseIface {
    void getResultList(String mod,//类型
                       int page,//页数
                       String sessionID,//形参
                       TcaseListener listener);
}
