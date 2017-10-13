package com.wjs.lib.xml.ben;

public class Json {
    private String describe;
    private String url;
    private String method;
    private String name;
    private String create;

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

    @Override
    public String toString() {
        return "Json{" +
                "describe='" + describe + '\'' +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", name='" + name + '\'' +
                ", create='" + create + '\'' +
                '}';
    }
}
