package com.agile.AuthApp;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class UserAccountFileWriter {
    FileWriter fileWriter;
   
    public void SaveToFile(String path, UsersAccount usersAccount) throws IOException{
        fileWriter = new FileWriter(path, false);
        Gson gson = new Gson();
        gson.toJson(usersAccount, UsersAccount.class, fileWriter);
        fileWriter.close();
    }
}
