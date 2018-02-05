package com.example.login;


import lombok.Data;

@Data
public class UserInfo {

    private String key;

    private String userId;

    private String username;

    private String regDate;

    private String udtDate;

    private Boolean isAuth;
}
