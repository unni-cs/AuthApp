package com.agile.authapp;

import java.io.IOException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class UserAccountService {

    private IValidator userNameValidator;
    private IValidator passwordValidator;
    private UserAccountsProvider userAccountsProvider;

    public UserAccountService(IValidator userNameValidator, IValidator passwordValidator, UserAccountsProvider userAccountsProvider){
        this.userNameValidator = userNameValidator;
        this.passwordValidator = passwordValidator;
        this.userAccountsProvider = userAccountsProvider;
    }    
    
    public UserAccountCreationStatus createAccount(String userName, String password) throws JsonIOException, JsonSyntaxException, IOException{
        
        var isValidUserName = userNameValidator.isValid(userName);
        var isValidPassword = passwordValidator.isValid(password);
        if(!isValidUserName || !isValidPassword){
            return new UserAccountCreationStatus(isValidUserName, isValidPassword);            
        }
        else{
            return userAccountsProvider.saveUserAccount(userName, password);
        }
    }
}
