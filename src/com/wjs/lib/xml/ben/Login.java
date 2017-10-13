package com.wjs.lib.xml.ben;

import java.util.List;

public class Login {
    private String url;
    private String usernamekey;
    private String passwordkey;
    private String cookiekey;
    private String tokenkey;
    private String create;
    private String username;
    private String password;
    private List<Json> json;
    public void setJson(List<Json> json) {
        this.json = json;
    }
    public List<Json> getJson() {
        return json;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsernamekey() {
        return usernamekey;
    }

    public void setUsernamekey(String usernamekey) {
        this.usernamekey = usernamekey;
    }

    public String getPasswordkey() {
        return passwordkey;
    }

    public void setPasswordkey(String passwordkey) {
        this.passwordkey = passwordkey;
    }

    public String getCookiekey() {
        return cookiekey;
    }

    public void setCookiekey(String cookiekey) {
        this.cookiekey = cookiekey;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwrod) {
        this.password = passwrod;
    }

    public String getTokenkey() {
        return tokenkey;
    }

    public void setTokenkey(String tokenkey) {
        this.tokenkey = tokenkey;
    }

    @Override
    public String toString() {
        return "Login{" +
                "url='" + url + '\'' +
                ", usernamekey='" + usernamekey + '\'' +
                ", passwordkey='" + passwordkey + '\'' +
                ", cookiekey='" + cookiekey + '\'' +
                ", tokenkey='" + tokenkey + '\'' +
                ", create='" + create + '\'' +
                ", username='" + username + '\'' +
                ", passwrod='" + password + '\'' +
                ", json=" + json +
                '}';
    }
}
