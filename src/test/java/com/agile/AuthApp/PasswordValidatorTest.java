package com.agile.authapp;

import junit.framework.TestCase;

public class PasswordValidatorTest extends TestCase{
    
    Validator validator = new PasswordValidator();

    public void test_WhenGivenProperPassword_ShouldReturnTrue(){
        //Given
        String password = "Ab345678";

        //When
        boolean actual = validator.isValid(password);

        //Then
        assertTrue(actual);
    }

    public void test_WhenPasswordIsAlphanumeric_ShouldReturnFalse(){
        //Given
        String password = "1234abc";

        //When
        boolean actual = validator.isValid(password);

        //Then
        assertFalse(actual);
    }

    public void test_WhenPasswordIsNull_ShouldReturnFalse(){
        //Given
        String password = null;

        //When
        boolean actual = validator.isValid(password);

        //Then
        assertFalse(actual);
    }

    public void test_WhenPasswordHavingSpace_ShouldReturnFalse(){
        //Given
        String password = "1123 ";

        //When
        boolean actual = validator.isValid(password);

        //Then
        assertFalse(actual);
    }

    public void test_WhenPasswordHavingSpecialChars_ShouldReturnFalse(){
        //Given
        String password = "1123$#";

        //When
        boolean actual = validator.isValid(password);

        //Then
        assertFalse(actual);
    }

    public void test_WhenPasswordNotHavingMinLength_ShouldReturnFalse(){
        //Given
        String password = "1123";

        //When
        boolean actual = validator.isValid(password);

        //Then
        assertFalse(actual);
    }

    public void test_WhenPasswordHavingTwoAlphabets_ShouldReturnTrue(){
        //Given
        String password = "112131aB2";

        //When
        boolean actual = validator.isValid(password);

        //Then
        assertTrue(actual);
    }

    public void test_WhenPasswordHavingOneAlphabet_ShouldReturnFalse(){
        //Given
        String password = "112a34312";

        //When
        boolean actual = validator.isValid(password);

        //Then
        assertFalse(actual);
    }

    public void test_WhenPasswordHavingOneNumber_ShouldReturnFalse(){
        //Given
        String password = "1aaaaavbbbbbb";

        //When
        boolean actual = validator.isValid(password);

        //Then
        assertFalse(actual);
    }

    public void test_WhenPasswordHavingLength7_ShouldReturnFalse(){
        //Given
        String password = "11aa11b";

        //When
        boolean actual = validator.isValid(password);

        //Then
        assertFalse(actual);
    }
}
