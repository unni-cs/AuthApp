package com.agile.authapp;

import java.util.ArrayList;
import java.util.List;

public class UsersAccount {
    private List<Credential> credentials;  

    public UsersAccount(){
        credentials = new ArrayList<>();
    }
    
    public List<Credential> credentials(){
        return this.credentials;
    }

    public void addCredential(Credential credential){
        credentials.add(credential);
    }
}



