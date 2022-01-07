package com.agile.authapp;

public class User {
    
    private boolean isAuthenticated;
    private String errorMessage;

    public User(boolean isAuthenticated, String errorMessage) {
        this.isAuthenticated = isAuthenticated;
        this.errorMessage = errorMessage;
    }

    public boolean isAuthenticated(){
        return this.isAuthenticated;
    }

    public String errorMessage(){
        return this.errorMessage;
    }
}
