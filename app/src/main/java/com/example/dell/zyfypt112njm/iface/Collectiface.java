package com.example.dell.zyfypt112njm.iface;

import com.example.dell.zyfypt112njm.Listener.CollectListener;

public interface Collectiface {
    void collect(String mod,String id,String sessionid,CollectListener listener);
    void uncollect(String mod,String id,String sessionid,CollectListener listener);
    void exist(String mod,String id,String sessionid,CollectListener listener);
}
