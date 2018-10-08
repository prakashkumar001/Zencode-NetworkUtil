package com.zencode.guru.network.request;

public class Login {
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * username : deepika1@gmail.com
     * password : 123456
     */

    private String username;
    private String password;

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
}
