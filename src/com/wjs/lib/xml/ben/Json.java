package com.wjs.lib.xml.ben;

import java.util.Map;

public class Json {
    private String describe;
    private String url;
    private String method;
    private String name;
    private String create;
    private Map<String,String> Ext;
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public void setExt(Map<String,String> ext) {
        Ext = ext;
    }

    public Map<String,String> getExt() {
        return Ext;
    }

    @Override
    public String toString() {
        return "Json{" +
                "describe='" + describe + '\'' +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", name='" + name + '\'' +
                ", create='" + create + '\'' +
                ", Ext='" + Ext + '\'' +
                '}';
    }
}
