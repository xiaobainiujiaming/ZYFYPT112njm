package com.example.dell.zyfypt112njm.iface;

import com.example.dell.zyfypt112njm.Listener.AttentionListener;

public interface Attentioniface {
    void focus(String mod, String id,  String sessionID, AttentionListener listener);

    void exists(String mod, String id,  String sessionID,AttentionListener listener);

    void unfocus(String mod,String id, String sessionID,AttentionListener listener);
}
