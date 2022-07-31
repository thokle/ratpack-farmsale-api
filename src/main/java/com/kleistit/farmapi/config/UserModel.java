package com.kleistit.farmapi.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by thokle on 27/12/2016.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserModel {

    private String email;
    private String password;

private  String username;
    public String getEmail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
