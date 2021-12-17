package com.agile.AuthApp;

import java.io.IOException;
import java.util.Optional;

public class AuthService {

    UserAccountsProvider userAccountsProvider = new UserAccountsProvider();
    private String invalidUserName = "Login failed. Invalid username";
    private String invalidPassword = "Login failed. Invalid password";
    
    public User authenticateUser(String userName, String password) throws IOException{
        UsersAccount usersAccount = userAccountsProvider.getUserAccounts();  
        Optional<Credential> credential = usersAccount.Credentials.stream().filter(x -> x.UserName.equals(userName)).findFirst();
        
        if(credential.isPresent()){
            if(credential.get().Password.equals(password)){
                return new User(true, null);
            }
            else{
                return new User(false, invalidPassword);
            }
        }
        else{
            return new User(false, invalidUserName);
        }
    }
}
