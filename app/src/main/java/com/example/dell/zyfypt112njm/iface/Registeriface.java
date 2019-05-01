package com.example.dell.zyfypt112njm.iface;

import com.example.dell.zyfypt112njm.Listener.RegisterListener;

public interface Registeriface {
    void  getRegisterResult(String username,
                            String password,
                            String tel,
                            RegisterListener registerListener);
}
