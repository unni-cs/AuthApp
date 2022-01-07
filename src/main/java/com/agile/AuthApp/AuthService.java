package com.agile.authapp;

import java.io.IOException;
import java.util.Optional;

public class AuthService {

    UserAccountsProvider userAccountsProvider = new UserAccountsProvider();
    private static final String INVALIDUSERNAME = "Login failed. Invalid username";
    private static final String INVALIDPASSWORD = "Login failed. Invalid password";
    
    public User authenticateUser(String userName, String password) throws IOException{
        UsersAccount usersAccount = userAccountsProvider.getUserAccounts();  
        Optional<Credential> credential = usersAccount.credentials().stream().filter(x -> x.userName().equals(userName)).findFirst();
        
        if(credential.isPresent()){
            if(credential.get().password().equals(password)){
                return new User(true, null);
            }
            else{
                return new User(false, INVALIDPASSWORD);
            }
        }
        else{
            return new User(false, INVALIDUSERNAME);
        }
    }
}
