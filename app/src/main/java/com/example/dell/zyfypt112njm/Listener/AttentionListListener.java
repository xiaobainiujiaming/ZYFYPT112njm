package com.example.dell.zyfypt112njm.Listener;

import java.util.List;

public interface AttentionListListener<T> {
    void onResponse(List<T> beanlist);
    void onFail(String msg);
}
