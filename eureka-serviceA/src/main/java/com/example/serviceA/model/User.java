package com.example.serviceA.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 2185397452395998210L;

    private long id;
    private String username;

    public User(){}
    public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
