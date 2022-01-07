package com.agile.authapp;

import java.io.IOException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class UserAccountService {
   
    private IValidator userNameValidator = new UserNameValidator();
    private IValidator passwordValidator =  new PasswordValidator();

    private static final String INVALIDUSERNAMEMESSAGE = "Username you have enterd is not valid";
    private static final String INVALIDUPASSWORDMESSAGE = "Password you have enterd is not valid";


    private UserAccountsProvider userAccountsProvider = new UserAccountsProvider();

    public UserAccountCreationStatus createAccount(String userName, String password) throws JsonIOException, JsonSyntaxException, IOException{
        
        var isValidUserName = userNameValidator.isValid(userName);
        var isValidPassword = passwordValidator.isValid(password);
        if(!isValidUserName || !isValidPassword){
            String invalidUserName = isValidUserName ? null: INVALIDUSERNAMEMESSAGE;
            String invalidPassword = isValidPassword ? null: INVALIDUPASSWORDMESSAGE;
            return new UserAccountCreationStatus(false, invalidUserName, invalidPassword);
            
        }
        else{
            return userAccountsProvider.saveUserAccount(userName, password);
        }
    }
}
