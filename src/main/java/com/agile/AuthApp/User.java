package com.agile.authapp;

public class User {
    
    public boolean IsAuthenticated;
    public String ErrorMessage;

    public User(boolean isAuthenticated, String errorMessage) {
        IsAuthenticated = isAuthenticated;
        ErrorMessage = errorMessage;
    }
}
