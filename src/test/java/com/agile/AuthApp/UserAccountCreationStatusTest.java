package com.agile.authapp;

import junit.framework.TestCase;

public class UserAccountCreationStatusTest extends TestCase{
    
    public void test_WhenValidUserNameAndPassword_ShouldCreateAccount(){
        //Given
        Boolean isValidUserName = true;
        Boolean isValidPassword = true;

        //When
        UserAccountCreationStatus accountCreationStatus = new UserAccountCreationStatus(isValidUserName, isValidPassword);

        //Then
        assertNotNull(accountCreationStatus);
        assertTrue(accountCreationStatus.isAccountCreationSuccess);
        assertNull(accountCreationStatus.userNameValidationMessage);
        assertNull(accountCreationStatus.passwordValidationMessage);
    }

    public void test_WhenInValidUserName_ShouldNotCreateAccount(){
        //Given
        Boolean isValidUserName = false;
        Boolean isValidPassword = true;

        //When
        UserAccountCreationStatus accountCreationStatus = new UserAccountCreationStatus(isValidUserName, isValidPassword);

        //Then
        assertNotNull(accountCreationStatus);
        assertFalse(accountCreationStatus.isAccountCreationSuccess);
        assertNotNull(accountCreationStatus.userNameValidationMessage);
        assertNull(accountCreationStatus.passwordValidationMessage);
    }

    public void test_WhenInValidPassword_ShouldNotCreateAccount(){
        //Given
        Boolean isValidUserName = true;
        Boolean isValidPassword = false;

        //When
        UserAccountCreationStatus accountCreationStatus = new UserAccountCreationStatus(isValidUserName, isValidPassword);

        //Then
        assertNotNull(accountCreationStatus);
        assertFalse(accountCreationStatus.isAccountCreationSuccess);
        assertNull(accountCreationStatus.userNameValidationMessage);
        assertNotNull(accountCreationStatus.passwordValidationMessage);
    }
}
