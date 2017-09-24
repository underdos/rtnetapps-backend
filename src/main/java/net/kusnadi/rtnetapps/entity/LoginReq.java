package net.kusnadi.rtnetapps.entity;

import javax.validation.constraints.NotNull;

/**
 * Created by root on 12/09/17.
 */


public class LoginReq {
    @NotNull
    private String username;
    @NotNull
    private String password;

    public LoginReq(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginReq(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginReq{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}