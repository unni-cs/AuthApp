package com.agile.AuthApp;

public class UserAccountCreationStatus {
    boolean IsAccountCreationSuccess;
    String UserNameValidationMessage;
    String PasswordValidationMessage;
    public UserAccountCreationStatus(boolean IsAccountCreationSuccess,
                                    String UserNameValidationMessage,
                                    String PasswordValidationMessage) {
        this.IsAccountCreationSuccess = IsAccountCreationSuccess;
        this.UserNameValidationMessage = UserNameValidationMessage;
        this.PasswordValidationMessage = PasswordValidationMessage;        
    }    
}
