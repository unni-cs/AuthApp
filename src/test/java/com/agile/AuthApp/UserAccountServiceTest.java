package com.agile.authapp;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import junit.framework.TestCase;

public class UserAccountServiceTest extends TestCase{

    public void test_GivenValidUserNameAndPassword_ShouldCreateUserAccount() throws JsonIOException, JsonSyntaxException, IOException{
        //Given
        String userName = "abcabc";
        String password = "123abc123a";
        UserAccountCreationStatus expected = new UserAccountCreationStatus(true, true);

        UserNameValidator userNameValidator = mock(UserNameValidator.class);
        when(userNameValidator.isValid(userName)).thenReturn(true);

        PasswordValidator passwordValidator = mock(PasswordValidator.class);
        when(passwordValidator.isValid(password)).thenReturn(true);

        UserAccountsProvider userAccountsProvider = mock(UserAccountsProvider.class);
        when(userAccountsProvider.saveUserAccount(userName, password)).thenReturn(expected);

        UserAccountService userAccountService = new UserAccountService(userNameValidator, passwordValidator, userAccountsProvider);
        
        //When
        UserAccountCreationStatus actual = userAccountService.createAccount(userName, password);

        //Then
        assertTrue(actual.isAccountCreationSuccess);
        verify(userNameValidator, times(1)).isValid(userName);
        verify(passwordValidator, times(1)).isValid(password);
        verify(userAccountsProvider, times(1)).saveUserAccount(userName, password);
    }

    public void test_GivenInValidUserName_ShouldNotCreateUserAccount() throws JsonIOException, JsonSyntaxException, IOException{
        //Given
        String userName = "invalid";
        String password = "123abc123a";
        UserAccountCreationStatus expected = new UserAccountCreationStatus(false, true);

        UserNameValidator userNameValidator = mock(UserNameValidator.class);
        when(userNameValidator.isValid(userName)).thenReturn(false);

        PasswordValidator passwordValidator = mock(PasswordValidator.class);
        when(passwordValidator.isValid(password)).thenReturn(true);

        UserAccountsProvider userAccountsProvider = mock(UserAccountsProvider.class);
        when(userAccountsProvider.saveUserAccount(userName, password)).thenReturn(expected);

        UserAccountService userAccountService = new UserAccountService(userNameValidator, passwordValidator, userAccountsProvider);
        
        //When
        UserAccountCreationStatus actual = userAccountService.createAccount(userName, password);

        //Then
        assertFalse(actual.isAccountCreationSuccess);
        verify(userNameValidator, times(1)).isValid(userName);
        verify(passwordValidator, times(1)).isValid(password);
        verify(userAccountsProvider, never()).saveUserAccount(userName, password);
    }

    public void test_GivenInValidPassword_ShouldNotCreateUserAccount() throws JsonIOException, JsonSyntaxException, IOException{
        //Given
        String userName = "valid";
        String password = "invalid";
        UserAccountCreationStatus expected = new UserAccountCreationStatus(true, false);

        UserNameValidator userNameValidator = mock(UserNameValidator.class);
        when(userNameValidator.isValid(userName)).thenReturn(false);

        PasswordValidator passwordValidator = mock(PasswordValidator.class);
        when(passwordValidator.isValid(password)).thenReturn(true);

        UserAccountsProvider userAccountsProvider = mock(UserAccountsProvider.class);
        when(userAccountsProvider.saveUserAccount(userName, password)).thenReturn(expected);

        UserAccountService userAccountService = new UserAccountService(userNameValidator, passwordValidator, userAccountsProvider);
        
        //When
        UserAccountCreationStatus actual = userAccountService.createAccount(userName, password);

        //Then
        assertFalse(actual.isAccountCreationSuccess);
        verify(userNameValidator, times(1)).isValid(userName);
        verify(passwordValidator, times(1)).isValid(password);
        verify(userAccountsProvider, never()).saveUserAccount(userName, password);
    }
}
