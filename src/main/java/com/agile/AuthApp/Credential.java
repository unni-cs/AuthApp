package com.agile.authapp;

public class Credential {
    private String userName;
    private String password;

    public Credential(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public String userName(){
       return this.userName;
    }

    public String password(){
        return this.password;
    }
}
