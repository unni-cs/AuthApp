package com.agile.authapp;

public class UserAccountCreationStatus {

    private static final String INVALIDUSERNAMEMESSAGE = "Username you have enterd is not valid";
    private static final String INVALIDPASSWORDMESSAGE = "Password you have enterd is not valid";

    boolean isAccountCreationSuccess;
    String userNameValidationMessage;
    String passwordValidationMessage;
    public UserAccountCreationStatus(boolean isValidUserName, boolean isValidPassword) {

        this.isAccountCreationSuccess = isValidUserName && isValidPassword;

        if(!isValidUserName){
            this.userNameValidationMessage = INVALIDUSERNAMEMESSAGE;
        }
        if(!isValidPassword){
            this.passwordValidationMessage = INVALIDPASSWORDMESSAGE;  
        }
    }    
}
