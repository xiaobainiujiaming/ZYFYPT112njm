package com.example.dell.zyfypt112njm.bean;

public class FocusResultBean<T> {
    private String id;
    private String idolid;
    private String fwtime;
    private Object listorder;
    private int userid;
    private String vstate;
    private T bean;
//自行生成get/set方法

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdolid() {
        return idolid;
    }

    public void setIdolid(String idolid) {
        this.idolid = idolid;
    }

    public String getFwtime() {
        return fwtime;
    }

    public void setFwtime(String fwtime) {
        this.fwtime = fwtime;
    }

    public Object getListorder() {
        return listorder;
    }

    public void setListorder(Object listorder) {
        this.listorder = listorder;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getVstate() {
        return vstate;
    }

    public void setVstate(String vstate) {
        this.vstate = vstate;
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }
}
