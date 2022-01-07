package com.agile.authapp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import junit.framework.TestCase;

public class UserAccountsProviderTest extends TestCase{
    
    public void test_GetUserAccounts_ShouldReturnUserAccounts() throws IOException{
        //Given
        UserAccountsProvider userAccountsProvider = mock(UserAccountsProvider.class);
        File file = mock(File.class);
        userAccountsProvider.file = file;        
        String resourceFilePath = "testresources/UserAccounts.json";
        File resourceFile = new File(resourceFilePath);        
        when(file.getAbsolutePath()).thenReturn(resourceFile.getAbsolutePath());  
        when(userAccountsProvider.getUserAccounts()).thenCallRealMethod();

        //When
        UsersAccount usersAccount = userAccountsProvider.getUserAccounts();

        //Then
        assertNotNull(usersAccount);
        assertNotNull(usersAccount.credentials());
        assertTrue(usersAccount.credentials().size()>0);
        assertEquals("testuser", usersAccount.credentials().get(0).userName());
        assertEquals("9898abc1234", usersAccount.credentials().get(0).password());
    }

    public void test_SaveUserAccount_ShouldSaveAccount() throws JsonIOException, JsonSyntaxException, IOException {
        //Given
        String userName = "abcd";
        String password = "123abc21cc";

        //Mock the provider
        UserAccountsProvider userAccountsProvider = mock(UserAccountsProvider.class);
        File file = mock(File.class);
        UserAccountFileWriter fileWriter = mock(UserAccountFileWriter.class);
        
        //Set the value to provider
        userAccountsProvider.file = file;     
        userAccountsProvider.fileWriter = fileWriter;   
        String resourceFilePath = "testresources/UserAccounts.json";
        File resourceFile = new File(resourceFilePath);        
        when(file.getAbsolutePath()).thenReturn(resourceFile.getAbsolutePath());       
        
        UsersAccount accountToVerify = new UsersAccount();
        Credential user = new Credential(userName,password);
        accountToVerify.addCredential(user);
        
        doAnswer( invocation -> { //stub the fileWriter method
           String path =  invocation.getArgument(0);
           UsersAccount accouts =  invocation.getArgument(1);
           assertEquals(resourceFile.getAbsolutePath(), path);
           assertEquals(accountToVerify, accouts);
           return null;
        }).when(fileWriter).saveToFile(resourceFile.getAbsolutePath(), accountToVerify);

        when(userAccountsProvider.saveUserAccount(userName, password)).thenCallRealMethod();        

        //When
        UserAccountCreationStatus accountSaved =  userAccountsProvider.saveUserAccount(userName, password);
        
        //Then
        assertNotNull(accountSaved);
        assertTrue(accountSaved.isAccountCreationSuccess);
        verify(fileWriter, times(1)).saveToFile(any(String.class), any(UsersAccount.class));
    }
}
