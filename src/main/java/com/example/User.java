package com.example;


/**
 * @author tanyaofei
 * @since 2024/4/18
 **/

public class User {

    private Long id;

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
