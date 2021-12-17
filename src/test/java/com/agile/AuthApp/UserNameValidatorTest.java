package com.agile.AuthApp;

import junit.framework.TestCase;

public class UserNameValidatorTest extends TestCase{
    
    Validator validator = new UserNameValidator();

    public void test_WithProperUserName_ShouldReturnTrue(){
        //Given
        String userName = "username";

        //When
        boolean actual = validator.isValid(userName);

        //Then
        assertTrue(actual);      
    }

    public void test_WithProperUserNameMixedUpperAndLowerCase_ShouldReturnTrue(){
        //Given
        String userName = "UserNamE";

        //When
        boolean actual = validator.isValid(userName);

        //Then
        assertTrue(actual);        
    }

    public void test_WhenUserNameIsEmpty_ShouldReturnFalse(){
        //Given
        String userName = "";

        //When
        boolean actual = validator.isValid(userName);

        //Then
        assertFalse(actual);  
    }

    public void test_WhenUserNameNull_ShouldReturnFalse(){
        //Given
        String userName = null;

        //When
        boolean actual = validator.isValid(userName);

        //Then
        assertFalse(actual);  
    }

    public void test_WhenUserNameIsMoreThan11Chars_ShouldReturnFalse(){
        //Given
        String userName = "abcdefghijklmnopqrst";

        //When
        boolean actual = validator.isValid(userName);

        //Then
        assertFalse(actual);   
    }

    public void test_WhenUserNameAlphaNumeric_ShouldReturnFalse(){
        //Given
        String userName = "abc123";

        //When
        boolean actual = validator.isValid(userName);

        //Then
        assertFalse(actual);  
    }

    public void test_WhenUserNameHavingSpecialChars_ShouldReturnFalse(){
        //Given
        String userName = "abc@123$";

        //When
        boolean actual = validator.isValid(userName);

        //Then
        assertFalse(actual);  
    }

    public void test_WhenUserNameHavingSpace_ShouldReturnFalse(){
        //Given
        String userName = "    ";

        //When
        boolean actual = validator.isValid(userName);

        //Then
        assertFalse(actual);  
    }
}
