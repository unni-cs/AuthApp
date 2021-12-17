package com.agile.AuthApp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class UserAccountsProvider {   
    String resourceFilePath = "resources/UserAccounts.json";
    File file = new File(resourceFilePath);
    UserAccountFileWriter fileWriter = new UserAccountFileWriter();

    public UsersAccount getUserAccounts() throws IOException {
        String path = file.getAbsolutePath();
        String fileContent = new String(Files.readAllBytes(Paths.get(path)));        
        UsersAccount usersAccount = new Gson().fromJson(fileContent, UsersAccount.class);    
        return usersAccount;
    }

    public UserAccountCreationStatus saveUserAccount(String userName, String password) throws JsonIOException, JsonSyntaxException, IOException {
        String absolutePath = file.getAbsolutePath();
        Path filePath = Paths.get(absolutePath);
        String fileContent = new String(Files.readAllBytes(filePath));        
        UsersAccount usersAccount = new Gson().fromJson(fileContent, UsersAccount.class);    
        
        Credential userCredential = new Credential();
        userCredential.UserName = userName;
        userCredential.Password = password;
        usersAccount.Credentials.add(userCredential);        
        fileWriter.SaveToFile(absolutePath, usersAccount);
        return new UserAccountCreationStatus(true, null, null);
    }
}
