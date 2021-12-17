package com.agile.AuthApp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
        assertNotNull(usersAccount.Credentials);
        assertTrue(usersAccount.Credentials.size()>0);
        assertEquals("testuser", usersAccount.Credentials.get(0).UserName);
        assertEquals("9898abc1234", usersAccount.Credentials.get(0).Password);
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
        Credential user1 = new Credential(){{ UserName = "testuser"; Password = "9898"; }};
        Credential user2 = new Credential(){{ UserName = userName; Password = password; }};
        accountToVerify.Credentials = new ArrayList<Credential>();
        accountToVerify.Credentials.add(user1);
        accountToVerify.Credentials.add(user2);
        
        doAnswer( invocation -> { //stub the fileWriter method
           String path =  invocation.getArgument(0);
           UsersAccount accouts =  invocation.getArgument(1);
           assertEquals(resourceFile.getAbsolutePath(), path);
           assertEquals(accountToVerify, accouts);
           return null;
        }).when(fileWriter).SaveToFile(resourceFile.getAbsolutePath(), accountToVerify);

        when(userAccountsProvider.saveUserAccount(userName, password)).thenCallRealMethod();        

        //When
        UserAccountCreationStatus accountSaved =  userAccountsProvider.saveUserAccount(userName, password);
        
        //Then
        assertNotNull(accountSaved);
        assertTrue(accountSaved.IsAccountCreationSuccess);
        verify(fileWriter, times(1)).SaveToFile(any(String.class), any(UsersAccount.class));
    }
}
