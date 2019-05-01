package com.example.dell.zyfypt112njm.Listener;

import com.example.dell.zyfypt112njm.bean.CollectBean;

import java.util.List;

public interface CollectListListener<T> {
        void onResponse(List<CollectBean<T>> beanlist);
        void onFail(String msg);

}
