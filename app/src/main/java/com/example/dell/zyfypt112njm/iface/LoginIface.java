package com.example.dell.zyfypt112njm.iface;

import com.example.dell.zyfypt112njm.Listener.LoginListener;

public interface LoginIface {
    void getLogin(String username,
                  String password,
                  LoginListener loginListener);
}
