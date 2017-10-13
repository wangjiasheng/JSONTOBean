package com.wjs.lib.xml.ben;

import java.util.List;

public class Auto {
    private String path;
    private List<Login> login;

    public void setLogn(List<Login> login) {
        this.login = login;
    }
    public List<Login> getLogn() {
        return login;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "path='" + path + '\'' +
                ", login=" + login +
                '}';
    }
}
