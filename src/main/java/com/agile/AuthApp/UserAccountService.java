package com.agile.AuthApp;

import java.io.IOException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class UserAccountService {
   
    private Validator userNameValidator = new UserNameValidator();
    private Validator passwordValidator =  new PasswordValidator();

    private String invalidUserNameMessage = "Username you have enterd is not valid";
    private String invalidUPasswordMessage = "Password you have enterd is not valid";


    private UserAccountsProvider userAccountsProvider = new UserAccountsProvider();

    public UserAccountCreationStatus createAccount(String userName, String password) throws JsonIOException, JsonSyntaxException, IOException{
        
        var isValidUserName = userNameValidator.isValid(userName);
        var isValidPassword = passwordValidator.isValid(password);
        if(!isValidUserName || !isValidPassword){
            String invalidUserName = isValidUserName ? null: invalidUserNameMessage;
            String invalidPassword = isValidPassword ? null: invalidUPasswordMessage;
            return new UserAccountCreationStatus(false, invalidUserName, invalidPassword);
            
        }
        else{
            return userAccountsProvider.saveUserAccount(userName, password);
        }
    }
}
