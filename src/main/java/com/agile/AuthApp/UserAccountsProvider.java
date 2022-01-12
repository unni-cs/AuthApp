package com.agile.authapp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class UserAccountsProvider {   
    private static final String RESOURCEFILEPATH = "resources/UserAccounts.json";
    File file = new File(RESOURCEFILEPATH);
    UserAccountFileWriter fileWriter = new UserAccountFileWriter();

    public UsersAccount getUserAccounts() throws IOException {
        String path = file.getAbsolutePath();
        String fileContent = new String(Files.readAllBytes(Paths.get(path)));        
        return new Gson().fromJson(fileContent, UsersAccount.class);    
    }

    public UserAccountCreationStatus saveUserAccount(String userName, String password) throws JsonIOException, JsonSyntaxException, IOException {
        String absolutePath = file.getAbsolutePath();
        Path filePath = Paths.get(absolutePath);
        String fileContent = new String(Files.readAllBytes(filePath));        
        UsersAccount usersAccount = new Gson().fromJson(fileContent, UsersAccount.class);    
        
        Credential userCredential = new Credential(userName, password);
        usersAccount.addCredential(userCredential);        
        fileWriter.saveToFile(absolutePath, usersAccount);
        return new UserAccountCreationStatus(true,true);
    }
}
