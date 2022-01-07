package com.agile.authapp;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import junit.framework.TestCase;

public class AuthServiceTest extends TestCase{
    
    AuthService authService = new AuthService();    

    public void test_WithRightCredentials_ShouldAuthenticateUser() throws IOException{
        //Given
        String userName = "testuser";
        String password = "666";
        setTheMock();

        //When        
        User actual = authService.authenticateUser(userName, password);

        //Then
        assertTrue(actual.isAuthenticated());
    }

    public void test_WithWrongUserName_ShouldReturnErrorMessage() throws IOException{
        //Given
        String userName = "userName1";
        String password = "12345";
        setTheMock();

        //When
        User actual = authService.authenticateUser(userName, password);

        //Then
        assertFalse(actual.isAuthenticated());
        assertEquals("Login failed. Invalid username" ,actual.errorMessage());
    }

    public void test_WithWrongPassword_ShouldReturnErrorMessage() throws IOException{
        //Given
        String userName = "testuser";
        String password = "123";
        setTheMock();

        //When
        User actual = authService.authenticateUser(userName, password);

        //Then
        assertFalse(actual.isAuthenticated());
        assertEquals("Login failed. Invalid password" ,actual.errorMessage());
    }

    public void test_WithWrongUsernameAndPassword_ShouldReturnInvalidUserNameMessage() throws IOException{
        //Given
        String userName = "user";
        String password = "123";
        setTheMock();

        //When
        User actual = authService.authenticateUser(userName, password);

        //Then
        assertFalse(actual.isAuthenticated());
        assertEquals("Login failed. Invalid username" ,actual.errorMessage());
    }

    private UsersAccount getTestData(){
        UsersAccount usersAccount = new UsersAccount();
        Credential credential = new Credential("testuser" ,"666");
        usersAccount.addCredential(credential);
        return usersAccount;
    }

    private void setTheMock() throws IOException{
        UserAccountsProvider userAccountsProvider = mock(UserAccountsProvider.class);
        authService.userAccountsProvider = userAccountsProvider;
        when(authService.userAccountsProvider.getUserAccounts()).thenReturn(getTestData());
    }
}
