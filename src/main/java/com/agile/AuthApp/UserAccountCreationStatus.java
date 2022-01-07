package com.agile.authapp;

public class UserAccountCreationStatus {
    boolean isAccountCreationSuccess;
    String userNameValidationMessage;
    String passwordValidationMessage;
    public UserAccountCreationStatus(boolean isAccountCreationSuccess,
                                    String userNameValidationMessage,
                                    String passwordValidationMessage) {
        this.isAccountCreationSuccess = isAccountCreationSuccess;
        this.userNameValidationMessage = userNameValidationMessage;
        this.passwordValidationMessage = passwordValidationMessage;        
    }    
}
